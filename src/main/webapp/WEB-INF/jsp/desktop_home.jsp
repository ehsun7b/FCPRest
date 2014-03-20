<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FC PERSEPOLIS INFORMATION</title>    
    <link rel="stylesheet" type="text/css" href="/css/desktop.css"/>
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/news.js"></script>
    <script type="text/javascript" src="/js/desktop_news.js"></script>
    <script type="text/javascript" src="/js/results.js"></script>
    <script type="text/javascript" src="/js/newspaper.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script src="js/stadium_map.js"></script>
  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <div id="logo"><img src="/img/main_title.png" alt="پایگاه خبری پرسپولیس" /></div>
        <div id="newspaper">
          <a id="newspaper_thumbnail" href="/rest/newspaper/pdf"></a>
        </div>
      </div>      

      <div class="tab_wrapper">
        <ul id="tabs">
          <li><a id="tab1">بازی بعدی</a></li>
          <li><a id="tab3">محل بازی بعدی</a></li>
          <li><a id="tab2">بازی قبلی</a></li>
          <li><a id="tab4">جدول لیگ برتر</a></li>
        </ul>
      </div>

      <div class="tab_container" id="tab1C"></div>
      <div class="tab_container" id="tab2C"></div>
      <div class="tab_container" id="tab3C">
        <!-- MAP OF NEXT MATCH -->
        <div class="next_match_location">
          محل برگزاری بازی بعدی<br/><br/>
          <div id="map-canvas"></div>
        </div>      
      </div>
      <div class="tab_container" id="tab4C">
        <!-- RANKING -->
        <div id="ranking"></div>
      </div>

      <!-- OFFICIAL -->
      <div class="news_group" id="official_new_group">
        <div class="news_group_header">اخبار سایت رسمی باشگاه</div>
        <div class="news_group_content" id="official_news_content"></div>
      </div>                        

      <!-- ARTESH -->
      <div class="news_group" id="artesh_new_group">
        <div class="news_group_header">اخبار سایت ارتش سرخ</div>
        <div class="news_group_content" id="artesh_news_content"></div>
      </div>

      <!-- KHABAR ONLINE -->
      <div class="news_group" id="khabaronline_new_group">
        <div class="news_group_header">اخبار خبرآنلاین</div>
        <div class="news_group_content" id="khabaronline_news_content"></div>
      </div>

      <!-- VARZESH3 -->
      <div class="news_group" id="varzesh3_new_group">
        <div class="news_group_header">اخبار سایت ورزش۳</div>
        <div class="news_group_content" id="varzesh3_news_content"></div>
      </div>


      <!-- ISNA -->
      <div class="news_group" id="isna_new_group">
        <div class="news_group_header">اخبار ایسنا</div>
        <div class="news_group_content" id="isna_news_content"></div>
      </div>

      <!-- IRNA -->
      <div class="news_group" id="irna_new_group">
        <div class="news_group_header">اخبار ایرنا</div>
        <div class="news_group_content" id="irna_news_content"></div>
      </div>      

      <br/>
      <br/>
    </div>
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
