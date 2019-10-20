require 'json'
require 'net/http'
require 'uri'
require 'rss'

def lambda_handler(event:, context:)
  area = event['area']
  raise 'area not allowed be blank' if area.empty?

  base_url = "http://api.openweathermap.org/data/2.5/forecast"
  req_url = base_url + "?lat=#{area["lat"]}&lon=#{area["lng"]}&APPID=#{ENV['OpenWeatherAPIKey']}"
  uri = URI.parse(req_url)

  http = Net::HTTP.new(uri.hostname, uri.port)
  http.use_ssl = false
  req = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(req)
  data = JSON.parse(response.body)
  id = data["list"][0]["weather"][0]["id"]

  { statusCode: 200, data: { weatherId: id } }
  rescue => e
  { statusCode: 400, error: e, event: event }
end
