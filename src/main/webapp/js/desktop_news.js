var SIZE = 10;
var IMAGE_SIZE = {width: 80, height: 50};

function showGeneralNews(data, board) {
  board.html("");
  console.log(data);
  var len = Math.min(data.length, SIZE);
  for (var i = 0; i < len; ++i) {
    var news = data[i];
    var image = $("<img/>", {src: "rest/image/news/" + news.uniqueKey + "/" + IMAGE_SIZE.width + "/" + IMAGE_SIZE.height});
    board.append($("<div/>", {"class": "news"}).append($("<a/>", {href: "/news/" + news.uniqueKey}).append(news.title)).append(image));
  }
}

function showOfficialNews() {
  $("#official_news_content").html("<div style='padding: 40px;text-align: center'>LOADING</div>");
  loadOfficialNews(function(data) {
    console.log("show official news");
    showGeneralNews(data, $("#official_news_content"));
  });
}

function showArteshNews() {
  $("#artesh_news_content").html("<div style='padding: 40px;text-align: center'>LOADING</div>");
  loadArteshNews(function(data) {
    showGeneralNews(data, $("#artesh_news_content"));
  });
}

function showVarzesh3News() {
  $("#varzesh3_news_content").html("<div style='padding: 40px;text-align: center'>LOADING</div>");
  loadVarzesh3News(function(data) {
    showGeneralNews(data, $("#varzesh3_news_content"));
  });
}

function showIsnaNews() {
  $("#isna_news_content").html("<div style='padding: 40px;text-align: center'>LOADING</div>");
  loadIsnaNews(function(data) {
    showGeneralNews(data, $("#isna_news_content"));
  });
}

function showKhabarOnlineNews() {
  $("#khabaronline_news_content").html("<div style='padding: 40px;text-align: center'>LOADING</div>");
  loadKhabarOnlineNews(function(data) {
    showGeneralNews(data, $("#khabaronline_news_content"));
  });
}

