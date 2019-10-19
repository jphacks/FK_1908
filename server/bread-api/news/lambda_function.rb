require 'json'
require 'rss'

def lambda_handler(event:, context:)
  req_url = "http://news.goo.ne.jp/rss/topstories/gootop/index.rdf"
  rss = RSS::Parser.parse(req_url, false)
  news = rss.channel.items.collect do |item|
    {
      title: item.title,
      pubDay: item.pubDate,
    }
  end

  { statusCode: 200, data: { news: news } }
end
