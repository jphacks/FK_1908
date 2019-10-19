require 'json'
require 'net/http'
require 'uri'

def lambda_handler(event:, context:)
  origin = event['origin']
  dest = event['dest']
  raise if origin.to_s.empty? || dest.to_s.empty?

  req_url = "https://maps.googleapis.com/maps/api/directions/json?origin=#{origin}&destination=#{dest}&key=#{credential}"
  uri = URI.parse(req_url)

  http = Net::HTTP.new(uri.hostname, uri.port)
  http.use_ssl = true
  req = Net::HTTP::Get.new(uri.request_uri)
  response = http.request(req)
  response.body
  data = JSON.parse(response.body)

  { statusCode: 200, data: data["routes"][0]["legs"][0]["duration"] }
  rescue => e
  { statusCode: 400, error: 'origin or dest are blank', event: event }
end

def credential
  cred = ENV['Credential']
end
