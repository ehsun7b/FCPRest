var SIZE = 30;
var IMAGE_SIZE = {width: 100, height: 100};

$(function() {
  $("#pageOfficialNews").on("pageshow", function(event) {
    showMobileOfficialNews();
  });

  $("#pageArteshNews").on("pageshow", function(event) {
    showMobileArteshNews();
  });

  $("#pageVarzesh3News").on("pageshow", function(event) {
    showMobileVarzesh3News();
  });

  $("#pageIsnaNews").on("pageshow", function(event) {
    showMobileIsnaNews();
  });

  $("#pageIrnaNews").on("pageshow", function(event) {
    showMobileIrnaNews();
  });

  $("#pageKhabarOnlineNews").on("pageshow", function(event) {
    showMobileKhabarOnlineNews();
  });
});

function showMobileGeneralNews(data, board) {
  board.html("");
  console.log(data);
  var len = Math.min(data.length, SIZE);
  for (var i = 0; i < len; ++i) {
    var news = data[i];    
    board.append($("<li/>", {"class": "news", "data-icon": "false"}).append($("<a/>", {target: "_blank", href: "newstext?url=" + news.link + "&title=" + news.title}).append(news.title).append(news.image !== undefined ? $("<img/>", {src: "rest/image/thumbnail/" + IMAGE_SIZE.width + "/" + IMAGE_SIZE.height + "?url=" + encodeURIComponent(news.image)}) : $("<img/>", {src: "rest/image/link/" + IMAGE_SIZE.width + "/" + IMAGE_SIZE.height + "?url=" + encodeURIComponent(news.link)}))));
    board.listview('refresh', true);
  }
}

function showMobileOfficialNews() {
  $.mobile.loading('show');
  loadOfficialNews(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#official_news_content"));
  });
}

function showMobileArteshNews() {
  $.mobile.loading('show');
  loadArteshNews(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#artesh_news_content"));
  });
}

function showMobileVarzesh3News() {
  $.mobile.loading('show');
  loadVarzesh3News(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#varzesh3_news_content"));
  });
}

function showMobileIsnaNews() {
  $.mobile.loading('show');
  loadIsnaNews(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#isna_news_content"));
  });
}

function showMobileIrnaNews() {
  $.mobile.loading('show');
  loadIrnaNews(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#irna_news_content"));
  });
}

function showMobileKhabarOnlineNews() {
  $.mobile.loading('show');
  loadKhabarOnlineNews(function(data) {
    $.mobile.loading('hide');
    showMobileGeneralNews(data, $("#khabaronline_news_content"));
  });
}

