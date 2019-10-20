require 'json'
require 'net/http'
require 'uri'
require 'date'
require 'rss'
require 'openssl'
require 'aws-sdk-dynamodb'

def lambda_handler(event:, context:)
  threads = []
  results = {}
  mutex = Mutex::new

  origin = event['origin']
  dest = event['dest']

  plan = fetch_recent_plan
  gmap_result = fetch_duration(origin: origin, dest: dest, start_time: plan["start_time"].to_i)
  duration = gmap_result[:duration]
  start_loc = gmap_result[:start_loc]
  end_loc = gmap_result[:end_loc]

  threads << Thread.start do
    weather = fetch_weather(area: start_loc)
    mutex.synchronize { results[:origin_weather] = weather }
  end

  threads << Thread.start do
    weather = fetch_weather(area: end_loc)
    mutex.synchronize { results[:dest_weather] = weather }
  end

  threads.each(&:join)

  default_time = Time.at(plan["start_time"].to_i)

  alarm = default_time.to_time - duration["value"].to_i - prepare_map(weather_code: results[:origin_weather]) - prepare_map(weather_code: results[:dest_weather])
  alarm_data = { hours: alarm.hour, minutes: alarm.min, seconds: alarm.sec }
  { statusCode: 200, data: { alarm: alarm_data, default: default_time.to_time, duration: duration["value"].to_i }, origin: prepare_map(weather_code: results[:origin_weather]) }
  rescue => e
  { statusCode: 400, error: e.backtrace.join("\n"), event: event, results: results }
end

def credential
  cred = ENV['Credential']
end

def fetch_duration(origin:, dest:, start_time:)
  raise 'origin or dest are blank' if origin.to_s.empty? || dest.to_s.empty?

  req_url = "https://maps.googleapis.com/maps/api/directions/json?origin=#{origin}&destination=#{dest}&departure_time=#{start_time}&key=#{credential}"

  uri = URI.parse(req_url)

  http = Net::HTTP.new(uri.hostname, uri.port)
  http.use_ssl = true
  req = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(req)
  data = JSON.parse(response.body)
  duration = data["routes"][0]["legs"][0]["duration_in_traffic"]
  start_loc = data["routes"][0]["legs"][0]["start_location"]
  end_loc = data["routes"][0]["legs"][0]["end_location"]
  { duration: duration, start_loc: start_loc, end_loc: end_loc }
end

def fetch_weather(area:)
  raise 'area is blank' if area.empty?
  base_url = "http://api.openweathermap.org/data/2.5/forecast"
  req_url = base_url + "?lat=#{area["lat"]}&lon=#{area["lng"]}&APPID=#{ENV['OpenWeatherAPIKey']}"
  uri = URI.parse(req_url)

  http = Net::HTTP.new(uri.hostname, uri.port)
  http.use_ssl = false
  req = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(req)
  data = JSON.parse(response.body)
  data["list"][0]["weather"][0]["id"]
end

def prepare_map(weather_code:)
  return 60 * 0 if weather_code == 800
  return 60 * 5 if (800..899).to_a.include? weather_code # cloudy
  return 60 * 30 if (300..399).to_a.include? weather_code # drizzle
  return 60 * 30 if (500..599).to_a.include? weather_code # rain
  return 60 * 60 if (600..699).to_a.include? weather_code # snow
  return 60 * 60 if (200..299).to_a.include? weather_code # thunderstorm
  return 60 * 60 if (700..799).to_a.include? weather_code # atmosphere
  return 60 * 0 # default
end

def fetch_recent_plan
  tomorrow = DateTime.now.to_date.next_day
  start_date = tomorrow.strftime("%Y/%m/%d")

  dynamoDB = Aws::DynamoDB::Resource.new
  post_table = dynamoDB.table("Calender")
  resp = post_table.query({
            select: "ALL_ATTRIBUTES",
            limit: 1,
            consistent_read: false,
            key_condition_expression: "#SD = :sd",
            expression_attribute_names: {
              "#SD": "start_date",
            },
            expression_attribute_values: {
              ":sd": start_date,
            },
            scan_index_forward: false,
        })
  resp["items"].first
end
