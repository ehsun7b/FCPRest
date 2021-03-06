<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html itemscope itemtype="http://schema.org/News">
  <head>
    <meta charset="utf-8" />
    
    <!-- Add the following three tags inside head. -->
    <meta itemprop="name" content="${news.title}">
    <meta itemprop="description" content="${news.description}">
    <meta itemprop="image" content="${news.image}">    
    
    <meta property="og:title" content="${news.title}" />
    <meta property="og:description" content="${news.description}" />
    <meta property="og:image" content="${news.image}"/>
    <meta property="og:url" content="http://fcpersepolis.info/news/${news.uniqueKey}"/>
    <meta property="og:site_name" content="پایگاه خبری پرسپولیس"/>
    
    <meta name=robots content="index, follow" />
    <meta name=googlebot content="index, follow" />
    <meta name=description content="${news.title}" />
    
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
    <title>FC Persepolis</title>
  </head>
  <body>
    <div id="newsText" data-role="page">
      <div data-role="header" data-id="headerH" data-theme="b"
           data-position="fixed">
        <h2>متن خبر</h2>
      </div>
      <!-- header -->
      <div data-role="content">
        <div class="date-news">${news.publishDate}</div>
        <div class="title-news">${news.title}</div>
        <c:if test="${news.image != null}">
          <div class="image-news">
            <img src="/rest/image/news/${news.uniqueKey}/400/400/photo.png" />
          </div>
        </c:if>
        <div class="text-news">${news.content}</div>
        <br/>        
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