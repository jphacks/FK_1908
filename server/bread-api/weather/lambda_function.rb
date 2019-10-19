require 'json'
require 'net/http'
require 'uri'
require 'rss'

def lambda_handler(event:, context:)
  req_url = "https://weather.goo.ne.jp/area/8210.rdf"
  rss = RSS::Parser.parse(req_url, false)
  weather = rss.channel.item.description

  { statusCode: 200, data: { weather: weather } }
  rescue => e
  { statusCode: 400, error: e, event: event }
end
