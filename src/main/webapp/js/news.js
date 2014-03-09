var SIZE = 10;

$(function() {
  showOfficialNews();
  showVarzesh3News();
  showArteshNews();
  showIsnaNews();
  showIrnaNews();
  showKhabarOnlineNews();
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

function loadIsnaNews(callback) {
  loadGeneralNews("/rest/isna/json", callback);
}

function loadIrnaNews(callback) {
  loadGeneralNews("/rest/irna/json", callback);
}

function loadKhabarOnlineNews(callback) {
  loadGeneralNews("/rest/khabaronline/json", callback);
}

function showGeneralNews(data, board, internal) {
  board.html("");
  console.log(data);
  var len = Math.min(data.length, SIZE);
  for (var i = 0; i < len; ++i) {
    var news = data[i];
    if (internal) {
      board.append($("<div/>", {"class": "news"}).append($("<a/>", {target: "_blank", href: "newstext?url=" + news.link + "&title=" + news.title}).append(news.title)));
    } else {
      board.append($("<div/>", {"class": "news"}).append($("<a/>", {target: "_blank", href: news.link}).append(news.title)));
    }
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