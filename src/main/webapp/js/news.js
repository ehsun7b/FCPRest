var SIZE = 10;

$(function() {
  showOfficialNews();
  showVarzesh3News();
  showArteshNews();
});

function loadGeneralNews(newsUrl, callback) {
  $.ajax({
    url: newsUrl,
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

function loadOfficialNews(callback) {
  loadGeneralNews("/rest/news/json", callback);
}

function loadArteshNews(callback) {
  loadGeneralNews("/rest/artesh/json", callback);
}

function loadVarzesh3News(callback) {
  loadGeneralNews("/rest/varzesh3/json", callback);
}

function showGeneralNews(data, board) {
  board.html("");
  console.log(data);
  var len = Math.min(data.length, SIZE);
  for (var i = 0; i < len; ++i) {
    var news = data[i];
    board.append($("<div/>", {"class": "news"}).append($("<a/>", {"href": "newstext?url=" + news.link + "&title=" + news.title}).append(news.title)));
  }
}

function showOfficialNews() {
  loadOfficialNews(function(data) {
    showGeneralNews(data, $("#official_news_content"));
  });
}

function showArteshNews() {
  loadArteshNews(function(data) {
    showGeneralNews(data, $("#artesh_news_content"));
  });
}

function showVarzesh3News() {
  loadVarzesh3News(function(data) {
    showGeneralNews(data, $("#varzesh3_news_content"));
  });
}



