var NEWSPAPER_THUMBNAIL_SIZE = {width: 138, height: 100};
var NEWSPAPERS_THUMBNAIL_SIZE = {width: 100, height: 60};
var img, pdf, layer;
var index = 0;

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
    $("#newspaper").css({"visibility": "visible"});
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
        $("#newspapers-thumbnails").fadeIn();
      });

      $("#" + board).mouseleave(function() {
        $("#newspapers-thumbnails").fadeOut();
      });
    }

    showAnimatedNewspapers("newspaper_thumbnail", data, NEWSPAPER_THUMBNAIL_SIZE);
  });
}


function showNewspaper(url) {
  window.open("/rest/image/?url=" + encodeURIComponent(url), '_blank');
}

function showAnimatedNewspapers(board, photos, size) {
  var images = [];
  var len = photos.length;
  for (var i = 0; i < len; ++i) {
    var img = new Image();
    images.push(img);
    photos[i] = "/rest/image/thumbnail/" + size.width + "/" + size.height + "?url=" + encodeURIComponent(photos[i])
  }

  var stage = new Kinetic.Stage({
    container: board,
    width: size.width,
    height: size.height
  });

  layer = new Kinetic.Layer();
  img = new Array(images.length);


  for (var i = 0; i < len; ++i) {
    images[i].onload = (function(j) {
      return function() {
        console.log("image " + j + " loaded: " + images[j].src);

        img[j] = new Kinetic.Image({
          x: 0,
          y: 0,
          image: images[j],
          width: size.width,
          height: size.height
        });

        layer.add(img[j]);
        layer.draw();
        
        img[j].on("mouseover", function(){
          pdf.moveToTop();
          layer.draw();
        });
        
        img[j].on("mouseout", function(){
          pdf.moveToBottom();
          layer.draw();
        });
        
      };
    }(i));

    images[i].src = photos[i];
    console.log("src: " + photos[i]);
  }

  pdf = new Kinetic.Text({
    x: 30,
    y: 26,
    text: 'PDF',
    stroke: "#000",
    strokeWidth: 2,
    fontSize: 40,
    fontFamily: 'Times New Roman',
    fontStyle: 'bold',
    fill: '#f00',
    align: 'center',
    shadowColor: 'black',
    shadowBlur: 2,
    shadowOffset: {x: 3, y: 3},
    shadowOpacity: 0.4,
    opacity: 0.6
  });
  
  pdf.on("mouseover", function() {
    pdf.moveToTop();
    layer.draw();
  });

  layer.add(pdf);
  stage.add(layer);
  layer.draw();

  setInterval(function() {
    //console.log(img);
    if (img[index] !== undefined) {
      img[index].moveToTop();
      layer.draw();
      if (index < img.length - 1) {
        index++;
      } else {
        index = 0;
      }
    }
  }, 3000);
}