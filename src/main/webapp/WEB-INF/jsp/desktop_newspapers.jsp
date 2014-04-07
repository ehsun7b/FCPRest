<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>روزنامه‌های ورزشی ${set.publishDate}</title>    
    <link rel="shortcut icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/css/desktop.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>    
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/kineticjs/kinetic-v5.0.1.min.js"></script>        

    <script type="text/javascript" src="/js/single-min.js"></script>
    
    <style type="text/css">
      div.newspapers div.title {
        text-align: center;
        font: normal bold 14px 'times new roman';
      }

      div.newspaper div.title {
        text-align: center;
        font: normal bold 20px 'times new roman';
        padding: 5px 0px;
      }

      div.newspaper div.image {
        text-align: center;
        padding-bottom: 20px;                
      }

      div.newspaper div.image img {
        border: 1px solid #555;
        box-shadow: 2px 2px 2px #999;
      }

      div.newspapers table {
        width: 99%;
        margin: 0px auto;
      }

      div.newspapers table td {
        text-align: center;
      }

      img#btnNext, img#btnPrevious {
        cursor: pointer;
      }

      div#board {
        min-height: 15px;
        font: normal normal 12px tahoma;
      }
    </style>
    <script>
      var newspapers = [];
      var newspaperIndex = 0;

      <c:forEach items="${newspapers}" var="n" varStatus="loop">
      newspapers.push({title: "${n.title}", photoURL: "${n.photoURL}"});

        <c:if test="${n.id == newspaper.id}">
      newspaperIndex = ${loop.index};
        </c:if>

      </c:forEach>


      $(function() {
        $("#btnNext").click(function() {
          if (newspaperIndex < newspapers.length - 1) {
            newspaperIndex++;
          } else {
            newspaperIndex = 0;
          }
          showPhoto();
        });
      });

      $(function() {
        $("#btnPrevious").click(function() {
          if (newspaperIndex > 0) {
            newspaperIndex--;
          } else {
            newspaperIndex = newspapers.length - 1;
          }
          showPhoto();
        });
      });

      function showPhoto() {
        console.log("show photo");
        $("#board").html("Loading");
        var img = new Image();

        img.onload = function() {
          $("img#imgNewspaper").attr({"src": img.src});
          $("#board").html("");
          $("#title").html(newspapers[newspaperIndex].title);
        };

        img.src = newspapers[newspaperIndex].photoURL;
      }
    </script>

  </head>
  <body>    
    <div id="main_container">
      <div id="header">
        <div id="logo"><a href="/"><img src="/img/main_title.png" alt="پایگاه خبری پرسپولیس" /></a></div>
        <div id="newspaper">
          <a id="newspaper_thumbnail" href="/newspapers/0"></a>
        </div>
      </div>     

      <div class="newspapers">
        <!-- <div class="title">روزنامه‌های ورزشی ${set.publishDate}</div> -->
        <table>
          <tr>
            <td>
              <img style="width: 50px" id="btnNext" src="/img/right.png"/>
            </td>
            <td>
              <div class="newspaper">
                <div class="title" id="title">${newspaper.title}</div>
                <div class="image"><div id="board"></div><img id="imgNewspaper" src="/image?url=${newspaper.photoURL}"/></div>
              </div>
            </td>
            <td>
              <img style="width: 50px" id="btnPrevious" src="/img/left.png"/>
            </td>
          </tr>
        </table>        
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
