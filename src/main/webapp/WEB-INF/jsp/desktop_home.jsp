<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>پایگاه خبری پرسپولیس</title>    
    <link rel="shortcut icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/css/desktop-min.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/kineticjs/kinetic-v5.0.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.flip.min.js"></script>

    <script type="text/javascript" src="/js/single-min.js"></script>

  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <%@include file="jspf/header.jspf" %>
      </div>            
      <div class="tabs-wrapper">
        <div class="tabs-link">
          <span class="tab-link active" data-tab="topTab" data-content="tab1">سرخط خبرها</span>
          <!--<span class="tab-link" data-tab="topTab" data-content="tab2">بازی بعدی</span>
          <span class="tab-link" data-tab="topTab" data-content="tab3">محل بازی بعدی</span>
          <span class="tab-link" data-tab="topTab" data-content="tab4">بازی قبلی</span>-->
          <span class="tab-link" data-tab="topTab" data-content="tab6">ویدئو‌های برگزیده</span>
          <span class="tab-link" data-tab="topTab" data-content="tab5">جدول لیگ برتر</span>
          <span class="tab-link" data-tab="topTab" data-content="tab7">جام جهانی</span>
        </div>
        <div class="tabs-content">
          <div class="tab-content active" data-tab="topTab" id="tab1">
            <div id="newsBoard">
              <c:forEach items="${hotNewsList}" var="hotNews" varStatus="loop">
                <div class="hotNews <c:if test="${loop.index > 0}">displayNone</c:if>">
                  <a href="/news/${hotNews.uniqueKey}">
                    <div class="hotNewsPhoto"><img src="/rest/image/news/${hotNews.uniqueKey}/250/200"/></div>
                    <div class="hotNewsTitle">${hotNews.title}</div>
                    <div class="hotNewsDescription">${hotNews.description}...</div>
                  </a>
                </div>
              </c:forEach>
            </div>
            <script>
              $(function() {                               
                hotNewsList = $("#newsBoard div.hotNews");
                hotNewsIndex = 0;
                scrollInterval = setInterval(function() {
                  if (hotNewsIndex < hotNewsList.length - 1) {
                    hotNewsIndex++;
                  } else {
                    hotNewsIndex = 0;
                  }

                  var color = colors[Math.floor((Math.random() * colors.length))];
                  $("#newsBoard").flip({
                    direction: 'lr',
                    content: "<div class='hotNews'>" + $(hotNewsList[hotNewsIndex]).html() + "</div>",
                    color: $("#newsBoard").css("background-color"),
                    speed: 300,
                    onBefore: function() {
                      
                    },
                    onEnd: function() {
                      
                    }
                  });
                }, 12000);
              });
            </script>
          </div>
          <!--<div class="tab-content" data-tab="topTab" id="tab2"></div>
          <div class="tab-content" data-tab="topTab" id="tab3">
          <!-- MAP OF NEXT MATCH --><!--
          <div class="next_match_location">
            محل برگزاری بازی بعدی<br/><br/>
            <div id="map-canvas"></div>
          </div> 
        </div>
        <div class="tab-content" data-tab="topTab" id="tab4"></div>-->
          <div class="tab-content" data-tab="topTab" id="tab6">
            <c:forEach items="${videos}" var="video">
              <div class="video">
                <a href="/video/${video.id}">
                  <img class="videoImage" src="/rest/video/image/${video.image}"/>
                  <span class="videoTitle">${video.title}</span>
                  <span class="videoCategory">${video.category.title}</span>
                </a>
              </div>
            </c:forEach>
          </div>
          <div class="tab-content" data-tab="topTab" id="tab5">
            <!-- RANKING -->
            <div id="ranking"></div>
          </div>          
          <div class="tab-content" data-tab="topTab" id="tab7">
            <img src="http://edmdjmixshow.com/wp-content/uploads/2013/02/Coming-Soon-BIG.jpg" style="max-width: 200px; display: block; margin: 0px auto"/>
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

      <br/><br/>

      <div class="tabs-wrapper">
        <div class="tabs-link">
          <span class="tab-link active" data-tab="newsTab" data-content="tab_official_new_group">اخبار سایت رسمی باشگاه</span>
          <span class="tab-link" data-tab="newsTab" data-content="tab_artesh_new_group">اخبار سایت ارتش سرخ</span>
          <span class="tab-link" data-tab="newsTab" data-content="tab_khabaronline_new_group">اخبار خبرآنلاین</span>
          <span class="tab-link" data-tab="newsTab" data-content="tab_varzesh3_new_group">اخبار سایت ورزش۳</span>
          <span class="tab-link" data-tab="newsTab" data-content="tab_isna_new_group">اخبار ایسنا</span>
        </div>
        <div class="tabs-content">
          <div class="tab-content active" data-tab="newsTab" id="tab_official_new_group">
            <!-- OFFICIAL -->
            <div class="news_group" id="official_new_group">              
              <div class="news_group_content" id="official_news_content">                
                <c:forEach items="${officialNewsList}" var="news">
                  <div class="news">
                    <a href="/news/${news.uniqueKey}">${news.title}</a>
                    <c:if test="${news.image != null}">
                      <img src="/rest/image/news/${news.uniqueKey}/80/50"/>
                    </c:if>
                  </div>
                </c:forEach>
              </div>
            </div>   
          </div>
          <div class="tab-content" data-tab="newsTab" id="tab_artesh_new_group">
            <!-- ARTESH -->
            <div class="news_group" id="artesh_new_group">              
              <div class="news_group_content" id="artesh_news_content"></div>
            </div>
          </div>
          <div class="tab-content" data-tab="newsTab" id="tab_khabaronline_new_group">
            <!-- KHABAR ONLINE -->
            <div class="news_group" id="khabaronline_new_group">              
              <div class="news_group_content" id="khabaronline_news_content"></div>
            </div>
          </div>
          <div class="tab-content" data-tab="newsTab" id="tab_varzesh3_new_group">
            <!-- VARZESH3 -->
            <div class="news_group" id="varzesh3_new_group">              
              <div class="news_group_content" id="varzesh3_news_content"></div>
            </div>      
          </div>
          <div class="tab-content" data-tab="newsTab" id="tab_isna_new_group">
            <!-- ISNA -->
            <div class="news_group" id="isna_new_group">        
              <div class="news_group_content" id="isna_news_content"></div>
            </div>
          </div>
        </div>
        <script>
          $("span.tab-link[data-tab='newsTab']").click(function() {
            $("[data-tab='newsTab']").removeClass("active");
            $(this).addClass("active");
            var tab = $(this).attr("data-content");
            $("#" + tab).addClass("active");

            if (tab === 'tab_artesh_new_group' && $("#artesh_news_content").html() === '') {
              showArteshNews();
            } else if (tab === 'tab_khabaronline_new_group' && $("#khabaronline_news_content").html() === '') {
              showKhabarOnlineNews();
            } else if (tab === 'tab_varzesh3_new_group' && $("#varzesh3_news_content").html() === '') {
              showVarzesh3News();
            } else if (tab === 'tab_isna_new_group' && $("#isna_news_content").html() === '') {
              showIsnaNews();
            }
          });
        </script>
      </div>
      <br/>
      <br/>
      <%@include file="jspf/footer.jspf" %>
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
