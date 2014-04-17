<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${news.title}</title>    
    <link rel="shortcut icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/css/desktop.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/kineticjs/kinetic-v5.0.1.min.js"></script>
        
    <script type="text/javascript" src="/js/single-min.js"></script>
    
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
        font: normal bold 20px "times new roman", times, serif;
        text-shadow: 1px 1px 2px #CCC;
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
        border-radius: 0px;
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
        <%@include file="jspf/header.jspf" %>
      </div>
      <div class="news">
        <div class="date-news">${news.publishDate}</div>
        <div class="title-news">${news.title}</div>
        <c:if test="${news.image != null}">
          <div class="image-news">
            <img src="/rest/image/thumbnail/400/300?url=${news.image}" />
          </div>
        </c:if>
        <div class="text-news">${news.content}</div>
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
