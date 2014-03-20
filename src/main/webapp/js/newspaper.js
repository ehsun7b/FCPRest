var SIZE = {width: 200, height: 100};

$(function() {
  showRandomNewspaperPhoto("newspaper_thumbnail", SIZE);
});

function getRandomNewspaperPhoto(callback) {
  $.ajax({
    url: "/rest/newspaper/randomphoto",
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

function showRandomNewspaperPhoto(board, size) {
  getRandomNewspaperPhoto(function(url) {
    console.log("random photo: " + url);
    $("#" + board).html($("<img/>", {
      src: "/rest/image/thumbnail/" + size.width + "/" + size.height + "?url=" + encodeURIComponent(url),
      alt: "Download Newspaper PDF",
      title: "روزنامه‌های ورزشی"
    }));    
  });
}