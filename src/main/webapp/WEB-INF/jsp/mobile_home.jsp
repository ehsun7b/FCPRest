<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />    
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=medium-dpi" />
    <script src="/js/newstext.js"></script>
    <!-- JQM -->
    <link rel="stylesheet" href="/js/jquery.mobile-1.3.2/jquery.mobile.structure-1.3.2.min.css" />
    <link rel="stylesheet" href="/js/jquery.mobile-1.3.2/fcpersepolis.min.css" />
    <script src="/js/jquery.mobile-1.3.2/jquery-1.9.1.min.js"></script>
    <script src="/js/jquery.mobile-1.3.2/jquery.mobile-1.3.2.min.js"></script>
    <!-- /JQM -->
    <link rel="stylesheet" href="/css/newstext-min.css" />
    <style type="text/css">


      article.hotNews {
        margin: 40px 0px;        
        display: block;
        line-height: 1.7em;   
        border-bottom: dotted gray 1px;
      }

      div.hotNewsPhoto {
        float: left;        
        text-align: left;
        width: 50%;
      }

      div.hotNewsDescription {
        min-height: 150px;
      }

      div.hotNewsPhoto img {
        max-width: 100%;
        margin-right: 0px;
        box-shadow: 2px 2px 2px #888;
      }

      article.hotNews a {
        text-decoration: none;
        display: block;
        margin-bottom: 20px;
      }
      
      a.news {
        display: block;
        text-decoration: none;
        margin: 40px 0px;
        line-height: 1.7em;        
      }
    </style>

    <title>FC Persepolis</title>
  </head>
  <body>
    <div id="home" data-role="page">
      <div data-role="header" data-id="headerH" data-theme="b"
           data-position="fixed">
        <h1 style="white-space: normal; padding: 10px 0px">پایگاه خبری پرسپولیس</h1>
      </div>
      <!-- header -->
      <div data-role="content">
        <c:forEach items="${hotNewsList}" var="hotNews" varStatus="loop">
          <article class="hotNews">            
            <header><h1 class="hotNewsTitle">${hotNews.title}</h1></header>
            <div class="hotNewsPhoto"><img src="/rest/image/news/${hotNews.uniqueKey}/200/200/photo.png"/></div>
            <div class="hotNewsDescription">${hotNews.description}...</div>
            <a data-ajax="false" href="/news/${hotNews.uniqueKey}">متن خبر</a>
          </article>
        </c:forEach>
        <div style="clear: both; margin-bottom: 20px;"></div>        
        <h1 style="text-align: center">اخبار جام‌جهانی</h1>
        <c:forEach items="${worldCupNewsList}" var="news" varStatus="loop">
          <a data-ajax="false" class="news" href="/news/${news.uniqueKey}"><h2>${news.title}</h2></a>
        </c:forEach>
        <h1 style="text-align: center">اخبار</h1>
        <c:forEach items="${newsList}" var="news" varStatus="loop">
          <a data-ajax="false" class="news" href="/news/${news.uniqueKey}"><h2>${news.title}</h2></a>
        </c:forEach>
      </div>

      <div class="donation">
        <div style="text-align: center;">
          <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
          <!-- NewsText_Ad_text_image -->
          <ins class="adsbygoogle"
               style="display:inline-block;width:300px;height:250px"
               data-ad-client="ca-pub-1305937802991389"
               data-ad-slot="9621156393"></ins>
          <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
          </script>
        </div>                
      </div>
      <!-- content -->
      <div data-role="footer" data-id="footer" data-theme="b"
           data-position="fixed"></div>
      <!-- footer -->
    </div>

    <script>
      (function(i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function() {
          (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o), m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
      })(window, document, 'script', '//www.google-analytics.com/analytics.js',
              'ga');

      ga('create', 'UA-43478531-1', 'perspolis-news.appspot.com');
      ga('send', 'pageview');
    </script>
  </body>
</html>