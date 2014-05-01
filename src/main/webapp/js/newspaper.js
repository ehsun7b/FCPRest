var NEWSPAPER_THUMBNAIL_SIZE = {width: 70, height: 50};
var NEWSPAPERS_THUMBNAIL_SIZE = {width: 70, height: 50};
var img, layer;
var index = 0;

$(function() {
  //showAllNewspaperThumbnails("newspaper", NEWSPAPERS_THUMBNAIL_SIZE); 
});

function getNewspaperPhotos(callback) {
  $.ajax({
    url: "/rest/newspaper/newspapers",
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

function showAllNewspaperThumbnails(board, size) {
  getNewspaperPhotos(function(data) {
    var newspaperPhotos = [];
    var len = data.length;
    if (len > 0) {
      //var div = $("<div/>", {"id": "newspapers-thumbnails"});

      //$(div).hide();

      for (var i = 0; i < len; ++i) {
        var url = data[i].photoURL;
        newspaperPhotos.push(url);
        /*var img = $("<img/>", {
          "src": "/rest/image/thumbnail/" + size.width + "/" + size.height + "?url=" + url,
          "width": size.width, "height": size.height,
          "onclick": "showNewspaper('" + data[i].id + "')"
        });
        div.append(img);*/
      }

      //$("#" + board).append(div);

      /*$("#" + board).mouseenter(function() {
        $("#newspapers-thumbnails").fadeIn();
        $("#newspapers-thumbnails").css({"z-index": 1000});
      });*/

      /*$("#" + board).mouseleave(function() {
        $("#newspapers-thumbnails").fadeOut();
      });*/
    }

    showAnimatedNewspapers("newspaper_thumbnail", newspaperPhotos, NEWSPAPER_THUMBNAIL_SIZE);
    $("#newspaper").css({visibility: "visible"});
  });
}


function showNewspaper(id) {
  window.open("/newspapers/" + id, '_blank');
}

function showAnimatedNewspapers(board, photos, size) {
  var images = [];
  var len = photos.length;
  for (var i = 0; i < len; ++i) {
    var img = new Image();
    images.push(img);
    photos[i] = "/rest/image/thumbnail/" + size.width + "/" + size.height + "?url=" + encodeURIComponent(photos[i]);
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
        img[j].moveToBottom();
        layer.draw();                
        
      };
    }(i));

    images[i].src = photos[i];
    console.log("src: " + photos[i]);
  }

  
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