var statusUpdate;
var started = false;

$(function() {
  console.log("dome ready live");
  $("#btnStop").hide();
  $("#btnStart").hide();
  $("#btnUpdate").hide();
  update();
  statusUpdate = setInterval(update, 10000);
  setEventHandlers();
});

function update() {
  console.log("updating status...");
  $("div#status table").css("opacity", "0.1");
  $.ajax({
    url: "rest/live/json",
    type: "GET"
  }).done(function(data) {
    console.log(data);
    if (data.result) {
      console.log(data.result);
    }

    if (data.result) {
      started = true;
      data = data.result;

      if (data.homeTeam) {
        $("#homeTeam").html(data.homeTeam);
      }

      if (data.awayTeam) {
        $("#awayTeam").html(data.awayTeam);
      }

      if (data.homeScore) {
        $("#homeScore").html(data.homeScore.toString());
      }

      if (data.awayScore) {
        $("#awayScore").html(data.awayScore.toString());
      }

      if (data.time) {
        $("#time").html(data.time);
      }
    } else {
      started = false;

      $("#homeTeam").html("");
      $("#awayTeam").html("");
      $("#homeScore").html("");
      $("#awayScore").html(data.awayScore);
    }

    $("div#status table").css("opacity", "1");

    if (started) {
      $("#btnStop").show();
      $("#btnStart").hide();
      $("#btnUpdate").show();
    } else {
      $("#btnStop").hide();
      $("#btnStart").show();
      $("#btnUpdate").hide();
    }
  });
}

function setEventHandlers() {
  console.log("setting event handlers");
  $("#btnStart").click(function() {
    start();
  });
  $("#btnStop").click(function() {
    finish();
  });
  $("#btnUpdate").click(function() {
    updateIt();
  });
}

function start() {
  console.log("starting ...");
  var homeTeam = $("#txtHomeTeam").val();
  var awayTeam = $("#txtAwayTeam").val();
  var time = $("#txtTime").val();
  $.ajax({
    url: "rest/live/start/" + homeTeam + "/" + awayTeam + "/" + time,
    type: "GET"
  }).done(function(data) {
    console.log("started.");
    update();
  });
}

function finish() {
  console.log("finishing");
  $.ajax({
    url: "rest/live/finish",
    type: "GET"
  }).done(function(data) {
    console.log("finished.");
    update();
  });
}

function updateIt() {

}