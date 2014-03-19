var SIZE = 10;
var IMAGE_SIZE = {width: 80, height: 80};

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
    var image = news.image !== undefined ? $("<img/>", {src: "rest/image/thumbnail/" + IMAGE_SIZE.width + "/" + IMAGE_SIZE.height + "?url=" + encodeURIComponent(news.image)})
            : $("<img/>", {src: "rest/image/link/" + IMAGE_SIZE.width + "/" + IMAGE_SIZE.height + "?url=" + encodeURIComponent(news.link)});
    board.append($("<div/>", {"class": "news"}).append($("<a/>", {target: "_blank", href: news.link}).append(news.title)).append(image));
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

