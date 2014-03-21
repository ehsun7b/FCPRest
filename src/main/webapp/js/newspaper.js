var NEWSPAPER_THUMBNAIL_SIZE = {width: 200, height: 100};
var NEWSPAPERS_THUMBNAIL_SIZE = {width: 100, height: 60};

$(function() {
  showRandomNewspaperPhoto("newspaper_thumbnail", NEWSPAPER_THUMBNAIL_SIZE);  
});

function getRandomNewspaperPhoto(callback) {
  $.ajax({
    url: "/rest/newspaper/randomphoto",
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

function getNewspaperPhotos(callback) {
  $.ajax({
    url: "/rest/newspaper/photos",
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
    
    showAllNewspaperThumbnails("newspaper", NEWSPAPERS_THUMBNAIL_SIZE);
  });
}

function showAllNewspaperThumbnails(board, size) {
  getNewspaperPhotos(function(data) {
    var len = data.length;
    if (len > 0) {
      var div = $("<div/>", {"id": "newspapers-thumbnails"});
      
      $(div).hide();
      
      for (var i = 0; i < len; ++i) {        
        var url = data[i];
        var img = $("<img/>", {
          "src": "/rest/image/thumbnail/" + size.width + "/" + size.height + "?url=" + url, 
          "width": size.width, "height": size.height,
          "onclick": "showNewspaper('" + data[i] + "')"
        });
        div.append(img);        
      }
      
      $("#" + board).append(div);
      
      $("#" + board).mouseenter(function() {
        $("#newspapers-thumbnails").show();
      });
      
      $("#" + board).mouseleave(function() {
        $("#newspapers-thumbnails").hide();
      });
    }
  });
}


function showNewspaper(url) {
  window.open("/rest/image/?url=" + encodeURIComponent(url),'_blank');
}