$(function() {
  makeTabs();
  showRanking("ranking");
  //showMatchInfo("results");
  showMatchInfoTabs("tab2C", "tab4C");
});

function makeTabs() {
  $("#tabs li a:not(:first)").addClass("inactive");
  $(".tab_container").hide();
  $(".tab_container:first").show();

  $("#tabs li a").click(function() {
    var t = $(this).attr("id");

    if ($(this).hasClass("inactive")) {
      $("#tabs li a").addClass("inactive");
      $(this).removeClass("inactive");
      $(".tab_container").hide();
      $("#" + t + "C").fadeIn("slow");
    }

    if (t === "tab3") {      
      mapInitialize();
    }
  });
}

function showRanking(id) {
  getRanking(function(data) {
    console.log(data);
    var table = data.table;
    $("#" + id).html($("<table/>", {id: "tblRanking"}));

    var trHead = $("<tr/>");

    trHead.append($("<th/>").append("ردیف"));
    trHead.append($("<th/>").append("تیم"));
    trHead.append($("<th/>").append("بازی"));
    trHead.append($("<th/>").append("برد"));
    trHead.append($("<th/>").append("مساوی"));
    trHead.append($("<th/>").append("باخت"));
    trHead.append($("<th/>").append("گل زده"));
    trHead.append($("<th/>").append("گل خورده"));
    trHead.append($("<th/>").append("تفاضل گل"));
    trHead.append($("<th/>").append("امتیاز"));

    $("#tblRanking").html(trHead);

    var len = table.length;

    for (var i = 0; i < len; ++i) {
      var row = table[i];
      var tr = $("<tr/>");

      tr.append($("<td/>").append(i + 1));
      tr.append($("<td/>").append(row.team));
      tr.append($("<td/>").append(row.matches));
      tr.append($("<td/>").append(row.wins));
      tr.append($("<td/>").append(row.draws));
      tr.append($("<td/>").append(row.looses));
      tr.append($("<td/>").append(row.scoredGoals));
      tr.append($("<td/>").append(row.receivedGoals));
      tr.append($("<td/>").append(row.differentGoals));
      tr.append($("<td/>").append(row.points));

      $("#tblRanking").append(tr);
    }
  });
}

function getRanking(callback) {
  $.ajax({
    url: "/rest/ranking/json",
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

function showMatchInfoTabs(tab1, tab2) {
  getMatchInfo(function(data) {
    console.log(data);
    $("#" + tab2).html($("<table/>", {id: "tblPreMatchInfo"}));
    $("#" + tab1).html($("<table/>", {id: "tblNextMatchInfo"}));

    var tr1 = $("<tr/>");
    var tr2 = $("<tr/>");
    var tr3 = $("<tr/>");
    var tr4 = $("<tr/>");
    var tr5 = $("<tr/>");
    var tr6 = $("<tr/>");
    var tr7 = $("<tr/>");
    var tr8 = $("<tr/>");

    tr4.append($("<td/>", {id: "preMatchInfo", colspan: 2}).append(data.preMatch.matchDescription))

    tr1.append($("<td/>", {id: "preHomeTeam"}).append(data.preMatch.homeTeam));
    tr1.append($("<td/>", {id: "preAwayTeam"}).append(data.preMatch.guestTeam));

    tr2.append($("<td/>", {id: "preHomeLogo"}).append($("<img/>", {src: data.preMatch.homeTeamLogo})));
    tr2.append($("<td/>", {id: "preAwayLogo"}).append($("<img/>", {src: data.preMatch.guestTeamLogo})));

    tr3.append($("<td/>", {id: "preHomeScore"}).append(data.preMatch.homeTeamScore));
    tr3.append($("<td/>", {id: "preAwayScore"}).append(data.preMatch.guestTeamScore));

    /* - - - - */

    tr7.append($("<td/>", {id: "nextMatchInfo", colspan: 2}).append(data.nextMatch.matchDescription))

    tr5.append($("<td/>", {id: "nextHomeTeam"}).append(data.nextMatch.homeTeam));
    tr5.append($("<td/>", {id: "nextAwayTeam"}).append(data.nextMatch.guestTeam));

    tr6.append($("<td/>", {id: "nextHomeLogo"}).append($("<img/>", {src: data.nextMatch.homeTeamLogo})));
    tr6.append($("<td/>", {id: "nextAwayLogo"}).append($("<img/>", {src: data.nextMatch.guestTeamLogo})));

    $("#tblPreMatchInfo").append(tr1);
    $("#tblPreMatchInfo").append(tr2);
    $("#tblPreMatchInfo").append(tr3);
    $("#tblPreMatchInfo").append(tr4);
    $("#tblNextMatchInfo").append(tr5);
    $("#tblNextMatchInfo").append(tr6);
    $("#tblNextMatchInfo").append(tr7);

  });
}

function showMatchInfo(id) {
  getMatchInfo(function(data) {
    console.log(data);
    $("#" + id).html($("<table/>", {id: "tblMatchInfo"}));

    var tr1 = $("<tr/>");
    var tr2 = $("<tr/>");
    var tr3 = $("<tr/>");
    var tr4 = $("<tr/>");
    var tr5 = $("<tr/>");
    var tr6 = $("<tr/>");
    var tr7 = $("<tr/>");
    var tr8 = $("<tr/>");
    var tr9 = $("<tr/>");
    var tr10 = $("<tr/>");

    tr1.append($("<td/>", {id: "preMatchTitle", colspan: 2}).append("بازی قبلی"));
    tr5.append($("<td/>", {id: "preMatchInfo", colspan: 2}).append(data.preMatch.matchDescription))

    tr2.append($("<td/>", {id: "preHomeTeam"}).append(data.preMatch.homeTeam));
    tr2.append($("<td/>", {id: "preAwayTeam"}).append(data.preMatch.guestTeam));

    tr3.append($("<td/>", {id: "preHomeLogo"}).append($("<img/>", {src: data.preMatch.homeTeamLogo})));
    tr3.append($("<td/>", {id: "preAwayLogo"}).append($("<img/>", {src: data.preMatch.guestTeamLogo})));

    tr4.append($("<td/>", {id: "preHomeScore"}).append(data.preMatch.homeTeamScore));
    tr4.append($("<td/>", {id: "preAwayScore"}).append(data.preMatch.guestTeamScore));

    /* - - - - */

    tr6.append($("<td/>", {id: "nextMatchTitle", colspan: 2}).append("بازی بعدی"));
    tr9.append($("<td/>", {id: "nextMatchInfo", colspan: 2}).append(data.nextMatch.matchDescription))

    tr7.append($("<td/>", {id: "nextHomeTeam"}).append(data.nextMatch.homeTeam));
    tr7.append($("<td/>", {id: "nextAwayTeam"}).append(data.nextMatch.guestTeam));

    tr8.append($("<td/>", {id: "nextHomeLogo"}).append($("<img/>", {src: data.nextMatch.homeTeamLogo})));
    tr8.append($("<td/>", {id: "nextAwayLogo"}).append($("<img/>", {src: data.nextMatch.guestTeamLogo})));

    $("#tblMatchInfo").append(tr1);
    $("#tblMatchInfo").append(tr2);
    $("#tblMatchInfo").append(tr3);
    $("#tblMatchInfo").append(tr4);
    $("#tblMatchInfo").append(tr5);
    $("#tblMatchInfo").append(tr6);
    $("#tblMatchInfo").append(tr7);
    $("#tblMatchInfo").append(tr8);
    $("#tblMatchInfo").append(tr9);

  });
}

function getMatchInfo(callback) {
  $.ajax({
    url: "/rest/matchinfo/json",
    type: "GET"
  }).done(function(data) {
    callback(data);
  });
}

