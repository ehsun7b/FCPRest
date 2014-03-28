var map;
//var point = {"latitude": 37.2643579, "longitude": 49.5886078}; // عضدی رشت
var point = {"latitude": 35.7245193, "longitude": 51.2716583}; // آزادی

$(function() {
  $("#btnDistance").click(function() {
    $("#distance").html("LOADING");
    showTravelingTime($("#distance"));
  });
});

function mapInitialize() {
  /*
   var statiumTitle = "ورزشگاه وطنی";
   var link = "https://www.google.com/maps/place/Vatani+Satadium/@36.4663282,52.8618202,18z/data=!4m2!3m1!1s0x3f855d25183473f1:0x566bd441a5a01a3b";   
   */

  /*
   var statiumTitle = "ورزشگاه عضدی"; // azodi rasht
   var link = "https://www.google.com/maps/place/Dr+Azody+Stadium/@37.2643579,49.5886078,16z/data=!4m7!1m4!3m3!1s0x3ff56225138d9bc9:0x41c268061848bd54!2zQXpvZGkgKNi52LbYr9uMKQ!3b1!3m1!1s0x0:0x52581d3a3ffa0f28";
   */

  var statiumTitle = "ورزشگاه آزادی"; // azodi rasht
  var link = "https://www.google.com/maps/place/Azadi+Stadium/@35.7245193,51.2716583,17z/data=!4m6!1m3!3m2!1s0x3f8dfc859879296d:0xc0312cba0be26197!2sAzadi+Sport+Complex!3m1!1s0x0:0x14589609944afeba";

  var stadium = new google.maps.LatLng(point.latitude, point.longitude);

  var mapOptions = {
    zoom: 14,
    center: stadium,
    draggable: true
  };

  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  var marker = new google.maps.Marker({
    position: stadium,
    map: map,
    title: statiumTitle
  });


  google.maps.event.addListener(map, 'click', function() {
    window.location.href = link;
  });

  /*
   google.maps.event.addListener(map, 'click', function() {
   map.setZoom(14);
   map.setCenter(marker.getPosition());
   });*/
}

//google.maps.event.addDomListener(window, 'load', initialize);


function showTravelingTime(board) {
  if (navigator.geolocation) {
    console.log("getting geo location");
    navigator.geolocation.getCurrentPosition(function(position) {
      console.log(position);
      var coords = position.coords;

      $.ajax({
        url: "/rest/direction/json/" + coords.latitude + "/" + coords.longitude + "/" + point.latitude + "/" + point.longitude + "/true",
        type: "get"
      }).done(function(data) {
        var meters = data.meters;
        if (meters > 0) {
          var km = meters / 1000;
          if (km > 0) {
            board.html(km + " کیلومتر");
          } else {
            board.html(meters + " متر");
          }
        }
      });

    }, function(error) {
      alert("error: " + error.code + " " + error.message);
    }, {maximumAge: 600000, timeout: 5000, enableHighAccuracy: true});
  } else {
    console.log("no geo location support");
  }
}