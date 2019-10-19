require 'json'
require 'net/http'
require 'uri'
require 'date'
require 'rss'

def lambda_handler(event:, context:)
  origin = event['origin']
  dest = event['dest']
  duration = fetch_duration(origin: origin, dest: dest)

  tomorrow = Time.now.localtime.to_date.next_day.to_time
  default_time = DateTime.new(tomorrow.year, tomorrow.month, tomorrow.day, tomorrow.hour, 9, 0, "+09:00")
  weather = fetch_weather(origin: origin, dest: dest)
  alarm = default_time.to_time - duration["value"].to_i - prepare_map(weather: weather)

  { statusCode: 200, data: { alarm: alarm } }
  rescue => e
  { statusCode: 400, error: e, event: event, duration: duration["value"] }
end

def credential
  cred = ENV['Credential']
end

def fetch_duration(origin:, dest:)
  raise 'origin or dest are blank' if origin.to_s.empty? || dest.to_s.empty?

  req_url = "https://maps.googleapis.com/maps/api/directions/json?origin=#{origin}&destination=#{dest}&key=#{credential}"
  uri = URI.parse(req_url)

  http = Net::HTTP.new(uri.hostname, uri.port)
  http.use_ssl = true
  req = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(req)
  response.body
  data = JSON.parse(response.body)
end

def fetch_weather(origin:, dest:)
  req_url = "https://weather.goo.ne.jp/area/8210.rdf"
  rss = RSS::Parser.parse(req_url, false)
  weather = rss.channel.item.description
end

def prepare_map(weather:)
  prepare_time = {
    "晴れ" => 0,
    "曇り" => 1 * 60 * 10,
    "雨" => 1 * 60 * 30,
  }[weather]
  prepare_time || 0
end
