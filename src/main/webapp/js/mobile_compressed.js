function initialize(){var e="ورزشگاه آزادی";var t="https://www.google.com/maps/place/Azadi+Stadium/@35.7245193,51.2716583,17z/data=!4m6!1m3!3m2!1s0x3f8dfc859879296d:0xc0312cba0be26197!2sAzadi+Sport+Complex!3m1!1s0x0:0x14589609944afeba";var n=new google.maps.LatLng(point.latitude,point.longitude);var r={zoom:12,center:n,draggable:false};map=new google.maps.Map(document.getElementById("map-canvas"),r);var i=new google.maps.Marker({position:n,map:map,title:e});google.maps.event.addListener(map,"click",function(){window.location.href=t})}function showTravelingTime(e){if(navigator.geolocation){console.log("getting geo location");navigator.geolocation.getCurrentPosition(function(t){console.log(t);var n=t.coords;$.ajax({url:"/rest/direction/json/"+n.latitude+"/"+n.longitude+"/"+point.latitude+"/"+point.longitude+"/true",type:"get"}).done(function(t){var n=t.meters;if(n>0){var r=n/1e3;if(r>0){e.html(r+" کیلومتر")}else{e.html(n+" متر")}}})},function(e){alert("error: "+e.code+" "+e.message)},{maximumAge:6e5,timeout:5e3,enableHighAccuracy:true})}else{console.log("no geo location support")}}function loadGeneralNews(e,t){$.ajax({url:e,type:"GET"}).done(function(e){t(e)})}function loadOfficialNews(e){loadGeneralNews("/rest/news/json",e)}function loadArteshNews(e){loadGeneralNews("/rest/artesh/json",e)}function loadVarzesh3News(e){loadGeneralNews("/rest/varzesh3/json",e)}function loadIsnaNews(e){loadGeneralNews("/rest/isna/json",e)}function loadIrnaNews(e){loadGeneralNews("/rest/irna/json",e)}function loadKhabarOnlineNews(e){loadGeneralNews("/rest/khabaronline/json",e)}function showRanking(e){getRanking(function(t){console.log(t);var n=t.table;$("#"+e).html($("<table/>",{id:"tblRanking"}));var r=$("<tr/>");r.append($("<th/>").append("ردیف"));r.append($("<th/>").append("تیم"));r.append($("<th/>").append("بازی"));r.append($("<th/>").append("امتیاز"));$("#tblRanking").html(r);var i=n.length;for(var s=0;s<i;++s){var o=n[s];var u=$("<tr/>");u.append($("<td/>").append(s+1));u.append($("<td/>").append(o.team));u.append($("<td/>").append(o.matches));u.append($("<td/>").append(o.points));$("#tblRanking").append(u)}})}function getRanking(e){$.ajax({url:"/rest/ranking/json",type:"GET"}).done(function(t){e(t)})}function showMatchInfo(e){getMatchInfo(function(t){console.log(t);$("#"+e).html($("<table/>",{id:"tblMatchInfo"}));var n=$("<tr/>");var r=$("<tr/>");var i=$("<tr/>");var s=$("<tr/>");var o=$("<tr/>");var u=$("<tr/>");var a=$("<tr/>");var f=$("<tr/>");var l=$("<tr/>");var c=$("<tr/>");n.append($("<td/>",{id:"preMatchTitle",colspan:2}).append("بازی قبلی"));o.append($("<td/>",{id:"preMatchInfo",colspan:2}).append(t.preMatch.matchDescription));r.append($("<td/>",{id:"preHomeTeam"}).append(t.preMatch.homeTeam));r.append($("<td/>",{id:"preAwayTeam"}).append(t.preMatch.guestTeam));i.append($("<td/>",{id:"preHomeLogo"}).append($("<img/>",{src:t.preMatch.homeTeamLogo})));i.append($("<td/>",{id:"preAwayLogo"}).append($("<img/>",{src:t.preMatch.guestTeamLogo})));s.append($("<td/>",{id:"preHomeScore"}).append(t.preMatch.homeTeamScore));s.append($("<td/>",{id:"preAwayScore"}).append(t.preMatch.guestTeamScore));u.append($("<td/>",{id:"nextMatchTitle",colspan:2}).append("بازی بعدی"));l.append($("<td/>",{id:"nextMatchInfo",colspan:2}).append(t.nextMatch.matchDescription));a.append($("<td/>",{id:"nextHomeTeam"}).append(t.nextMatch.homeTeam));a.append($("<td/>",{id:"nextAwayTeam"}).append(t.nextMatch.guestTeam));f.append($("<td/>",{id:"nextHomeLogo"}).append($("<img/>",{src:t.nextMatch.homeTeamLogo})));f.append($("<td/>",{id:"nextAwayLogo"}).append($("<img/>",{src:t.nextMatch.guestTeamLogo})));$("#tblMatchInfo").append(n);$("#tblMatchInfo").append(r);$("#tblMatchInfo").append(i);$("#tblMatchInfo").append(s);$("#tblMatchInfo").append(o);$("#tblMatchInfo").append(u);$("#tblMatchInfo").append(a);$("#tblMatchInfo").append(f);$("#tblMatchInfo").append(l)})}function getMatchInfo(e){$.ajax({url:"/rest/matchinfo/json",type:"GET"}).done(function(t){e(t)})}function showMobileGeneralNews(e,t){t.html("");console.log(e);var n=Math.min(e.length,SIZE);for(var r=0;r<n;++r){var i=e[r];var s=$("<img/>",{src:"/rest/image/news/"+i.uniqueKey+"/"+IMAGE_SIZE.width+"/"+IMAGE_SIZE.height});t.append($("<li/>",{"class":"news","data-icon":"false"}).append($("<a/>",{target:"_blank",href:"/news/"+i.uniqueKey}).append(i.title).append(s)));t.listview("refresh",true)}}function showMobileOfficialNews(){$.mobile.loading("show");loadOfficialNews(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#official_news_content"))})}function showMobileArteshNews(){$.mobile.loading("show");loadArteshNews(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#artesh_news_content"))})}function showMobileVarzesh3News(){$.mobile.loading("show");loadVarzesh3News(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#varzesh3_news_content"))})}function showMobileIsnaNews(){$.mobile.loading("show");loadIsnaNews(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#isna_news_content"))})}function showMobileIrnaNews(){$.mobile.loading("show");loadIrnaNews(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#irna_news_content"))})}function showMobileKhabarOnlineNews(){$.mobile.loading("show");loadKhabarOnlineNews(function(e){$.mobile.loading("hide");showMobileGeneralNews(e,$("#khabaronline_news_content"))})}var map;var point={latitude:35.7245193,longitude:51.2716583};$(function(){$("#btnDistance").click(function(){$("#distance").html("LOADING");showTravelingTime($("#distance"))});$("#pageMatchInfo").on("pageshow",function(){var e=$(document).width();if(e>0){var t=e*90/100;$("#map-canvas").css("width",t);$("#map-canvas").css("height",t)}initialize()})});$(function(){showRanking("ranking");showMatchInfo("results")});var SIZE=30;var IMAGE_SIZE={width:100,height:100};$(function(){$("#pageOfficialNews").on("pageshow",function(e){showMobileOfficialNews()});$("#pageArteshNews").on("pageshow",function(e){showMobileArteshNews()});$("#pageVarzesh3News").on("pageshow",function(e){showMobileVarzesh3News()});$("#pageIsnaNews").on("pageshow",function(e){showMobileIsnaNews()});$("#pageIrnaNews").on("pageshow",function(e){showMobileIrnaNews()});$("#pageKhabarOnlineNews").on("pageshow",function(e){showMobileKhabarOnlineNews()})})