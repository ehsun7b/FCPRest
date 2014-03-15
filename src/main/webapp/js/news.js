
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
