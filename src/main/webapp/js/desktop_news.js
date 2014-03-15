var SIZE = 10;

$(function() {
  showOfficialNews();
  showVarzesh3News();
  showArteshNews();
  showIsnaNews();
  showIrnaNews();
  showKhabarOnlineNews();
});

function showGeneralNews(data, board) {
  board.html("");
  console.log(data);
  var len = Math.min(data.length, SIZE);
  for (var i = 0; i < len; ++i) {
    var news = data[i];
    board.append($("<div/>", {"class": "news"}).append($("<a/>", {target: "_blank", href: news.link}).append(news.title)));
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

function showIsnaNews() {
  loadIsnaNews(function(data) {
    showGeneralNews(data, $("#isna_news_content"));
  });
}

function showIrnaNews() {
  loadIrnaNews(function(data) {
    showGeneralNews(data, $("#irna_news_content"));
  });
}

function showKhabarOnlineNews() {
  loadKhabarOnlineNews(function(data) {
    showGeneralNews(data, $("#khabaronline_news_content"));
  });
}

