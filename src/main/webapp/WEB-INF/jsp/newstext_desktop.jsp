<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${news.title}</title>    
    <link rel="shortcut icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/css/desktop.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <!--
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/kineticjs/kinetic-v5.0.1.min.js"></script>
    <script type="text/javascript" src="/js/news.js"></script>
    <script type="text/javascript" src="/js/desktop_news.js"></script>
    <script type="text/javascript" src="/js/results.js"></script>
    <script type="text/javascript" src="/js/newspaper.js"></script>
    <script type="text/javascript" src="/js/newsboard.js"></script>    
    <script src="js/stadium_map.js"></script>
    -->
    <script src="js/all_compressed.js"></script>
    <style type="text/css">
      div.news {
        padding: 20px;
      }

      div.image-news {
        float: left;        
        padding-right: 20px;
        padding-bottom: 20px;
        padding-top: 20px;
      }

      div.title-news {
        font: normal bold 20px "times new roman";
        text-shadow: 2px 2px 1px #CCC;
        padding-bottom: 10px;
      }

      div.date-news {
        font: normal normal 10px tahoma;
        color: #777;
        padding-bottom: 5px;
        float: left;
      }

      div.text-news {
        text-align: justify;
        font: normal normal 12px tahoma;
        line-height: 1.6em;
      }

      div.text-news strong {
        display: block;
      }

      div.image-news img {
        border: 1px solid #000;
        box-shadow: 2px 2px 2px #999;
      }

      div.link-news a {
        text-decoration: none;
        font: normal normal 10px tahoma;
      }
    </style>
  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <div id="logo"><a href="/"><img src="/img/main_title.png" alt="پایگاه خبری پرسپولیس" /></a></div>
        <div id="newspaper">
          <a id="newspaper_thumbnail" href="/rest/newspaper/pdf"></a>
        </div>
      </div>
      <div class="tabs-wrapper">
        <div class="tabs-link">
          <span class="tab-link active" data-tab="topTab" data-content="tab1">سرخط خبرها</span>
          <span class="tab-link" data-tab="topTab" data-content="tab2">بازی بعدی</span>
          <span class="tab-link" data-tab="topTab" data-content="tab3">محل بازی بعدی</span>
          <span class="tab-link" data-tab="topTab" data-content="tab4">بازی قبلی</span>
          <span class="tab-link" data-tab="topTab" data-content="tab5">جدول لیگ برتر</span>          
        </div>
        <div class="tabs-content">
          <div class="tab-content active" data-tab="topTab" id="tab1">
            <div id="newsBoard"></div>
            <script>
              $.ajax({
                url: "/rest/newsboard/json"
              }).done(function(data) {
                var newsBoard = new NewsBoard({
                  "container": "newsBoard",
                  "size": {"width": 850, "height": 100},
                  "newsList": data,
                  "maxTextWidth": 90,
                  "imageSize": {width: 130, height: 80},
                  "interval": 10000
                });
              });
            </script>
          </div>
          <div class="tab-content" data-tab="topTab" id="tab2"></div>
          <div class="tab-content" data-tab="topTab" id="tab3">
            <!-- MAP OF NEXT MATCH -->
            <div class="next_match_location">
              محل برگزاری بازی بعدی<br/><br/>
              <div id="map-canvas"></div>
            </div> 
          </div>
          <div class="tab-content" data-tab="topTab" id="tab4"></div>
          <div class="tab-content" data-tab="topTab" id="tab5">
            <!-- RANKING -->
            <div id="ranking"></div>
          </div>          

          <script>
            $("span.tab-link[data-tab='topTab']").click(function() {
              $("[data-tab='topTab']").removeClass("active");
              $(this).addClass("active");
              var tab = $(this).attr("data-content");
              $("#" + tab).addClass("active");

              if (tab === "tab3") {
                mapInitialize();
              }
            });
          </script>
        </div>        
      </div>

      <div class="news">
        <div class="date-news">${news.publishDate}</div>
        <div class="title-news">${news.title}</div>
        <c:if test="${news.image != null}">
          <div class="image-news">
            <img src="/rest/image/thumbnail/400/300?url=${news.image}" />
          </div>
        </c:if>
        <div class="text-news">${news.description}</div>
        <div class="link-news"><a href="${news.link}" target="_blank">منبع خبر</a></div>
      </div>
      <div style="clear: both"></div>

      <!-- Google adsense -->
      <div id="showAdvert1" style="visibility: hidden">SHOW</div>
      <div style="text-align: center; padding: 30px 0px" id="advert1">
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- FCPersepolisDesktopTopTabs -->
        <ins class="adsbygoogle"
             style="display:inline-block;width:728px;height:90px"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="3807537997"></ins>
        <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <script>

        document.onload = function() {
          /*
          var advert1 = $("#adver1");
          if (advert1 !== undefined) {
            advert1.css({visibility: "hidden"});
          }
          
          var showAdvert1 = $("#showAdver1");
          if (showAdvert1 !== undefined) {
            showAdvert1.css({visibility: "visible"});
            showAdvert1.click(function() {
              advert1.css({visibility: "visible"});
            });
          }*/
        };

      </script>
      <!-- -------------- -->
  </body>

  <!-- GOOGLE ANALYTICS -->
  <script>
    (function(i, s, o, g, r, a, m) {
      i['GoogleAnalyticsObject'] = r;
      i[r] = i[r] || function() {
        (i[r].q = i[r].q || []).push(arguments)
      }, i[r].l = 1 * new Date();
      a = s.createElement(o),
              m = s.getElementsByTagName(o)[0];
      a.async = 1;
      a.src = g;
      m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-48900732-2', 'fcpersepolis.info');
    ga('send', 'pageview');

  </script>
  <!-- ---------------- -->

</html>
