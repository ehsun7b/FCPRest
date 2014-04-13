<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${video.title}</title>    
    <link rel="shortcut icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/css/desktop.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>    
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/kineticjs/kinetic-v5.0.1.min.js"></script>        

    <script type="text/javascript" src="/js/single-min.js"></script>
    
    <style type="text/css">
      div.videoShow {
        padding: 10px;
        text-align: center;
      }
      
      div.videoShow div.videoTitle {
        font: normal bold 20px Yas, "Times New Roman";
        margin-bottom: 10px; 
      }
      
      div.videoShow div.videoTitle a.videoCategory {
        font-size: 12px;
        display: inline-block;
        text-decoration: none;
        margin-right: 10px;
      }
    </style>
    <script>
     
    </script>

  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <%@include file="jspf/header.jspf" %>
      </div>     

      <div class="videoShow">
        <div class="videoTitle">${video.title}<a href="/video/cat/${video.category.code}" class="videoCategory">${video.category.title}</a></div>
        <div class="embed">${video.embedCode}</div>
      </div>      

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
