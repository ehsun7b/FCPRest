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
    <script type="text/javascript" src="/js/single-min.js"></script>    
  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <%@include file="jspf/header.jspf" %>
      </div>            

      <div class="middle">
        <div class="left">
          <div class="sideNewsList">
            <h4>اخبار جام‌جهانی</h4>
            <c:forEach items="${worldCupNewsList}" var="news">
              <div class="sideNews">
                <a class="sideNewsSite" href="/cat/${news.website}">${news.websiteName}</a>
                <div class="sideNewsPhoto"><img src="/rest/image/news/crop/${news.uniqueKey}/50/35/photo.png"/></div>
                <div class="sideNewsTitle"><a title="${news.title}" href="/news/${news.uniqueKey}">${news.title}</a></div>
              </div>
            </c:forEach>
          </div>
          
          <div class="sideNewsList">
            <h4>اخبار</h4>
            <c:forEach items="${newsList}" var="news">
              <div class="sideNews">
                <a class="sideNewsSite" href="/cat/${news.website}">${news.websiteName}</a>
                <div class="sideNewsPhoto"><img src="/rest/image/news/crop/${news.uniqueKey}/50/35/photo.png"/></div>
                <div class="sideNewsTitle"><a title="${news.title}" href="/news/${news.uniqueKey}">${news.title}</a></div>
              </div>
            </c:forEach>
          </div>
        </div>

        <div class="right">
          <c:forEach items="${hotNewsList}" var="hotNews" varStatus="loop">
            <article class="hotNews">              
              <header><h1 class="hotNewsTitle"><a title="${hotNews.title}" href="/news/${hotNews.uniqueKey}">${hotNews.title}</a></h1></header>
              <div class="hotNewsPhoto"><a href="/news/${hotNews.uniqueKey}"><img src="/rest/image/news/crop/${hotNews.uniqueKey}/200/140/photo.png" /></a></div>
              <div class="hotNewsDescription">${hotNews.shortDescription}</div>
            </article>
          </c:forEach>
        </div>
      </div>

      <div id="footer">
        <%@include file="jspf/footer.jspf" %>
      </div>
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
