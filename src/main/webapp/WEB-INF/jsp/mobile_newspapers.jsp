<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="format-detection" content="telephone=no" />
    <link rel="shortcut icon" href="/favicon.ico" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=medium-dpi" />
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>    
    <link rel="stylesheet" href="/js/rtl.jquery.mobile-1.4.0/css/themes/default/rtl.jquery.mobile-1.4.0.css" />    
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/rtl.jquery.mobile-1.4.0/js/rtl.jquery.mobile-1.4.0.js"></script>

    <script src="/js/mobile-min.js"></script>
    <script src="/js/mobile_results-min.js"></script>
    <script src="/js/mobile_stadium_map-min.js"></script>
    <script src="/js/news-min.js"></script>
    <script src="/js/mobile_news-min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/mobile.css"/>    
    <title>FC Persepolis</title>

    <style>
      /* Transparent footer */
      .ui-footer {
        background: none;
        border: none;
      }
      /* The footer won't have a height because there are only two absolute positioned elements in it.
      So we position the buttons from the bottom. */
      .control.ui-btn-left, .trivia-btn.ui-btn-right {
        top: auto;
        bottom: 7px;
        margin: 0;
      }

      /* Custom styling for the trivia source */
      small {
        font-size: .75em;
        color: #666;
      }
      /* Prevent text selection while swiping with mouse */
      .ui-header, .ui-title, .control .ui-btn, .trivia-btn {
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        -o-user-select: none;
        user-select: none;
      }

      img#imgNewspaper {
        width: 100%;
        max-width: 600px;
      }

      div#board {
        text-align: center;
      }
    </style>
  </head>
  <body>

    <script>
      $(document).on("pageinit", "#pageNewspaper", function() {
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
          //console.log("show photo");
          $("#board").html("Loading");
          var img = new Image();

          img.onload = function() {
            $("img#imgNewspaper").attr({"src": img.src});
            $("#board").html("");
            $("#title").html(newspapers[newspaperIndex].title);
          };

          img.src = newspapers[newspaperIndex].photoURL;
        }

        $(document).on("swiperight", $("#pageNewspaper"), function() {
          if (newspaperIndex > 0) {
            newspaperIndex--;
          } else {
            newspaperIndex = newspapers.length - 1;
          }
          showPhoto();
        });

        $(document).on("swipeleft", $("#pageNewspaper"), function() {
          if (newspaperIndex < newspapers.length - 1) {
            newspaperIndex++;
          } else {
            newspaperIndex = 0;
          }
          showPhoto();
        });
      });
    </script>

    <div id="pageNewspaper" data-role="page">
      <!-- header -->
      <div data-role="header" data-position="fixed" data-fullscreen="true" data-theme="a">
        <p id="title" style="text-align: center">${newspaper.title}</p>
        <div id="board"></div>
      </div>

      <!-- content -->
      <div data-role="content">
        <img src="${newspaper.photoURL}" id="imgNewspaper"/>
      </div>

      <div data-role="footer" data-fullscreen="true" data-position="fixed" data-theme="a">
        <div data-role="controlgroup" class="control ui-btn-left" data-type="horizontal" data-mini="true">            
          <a href="#" id="btnNext" class="next" data-role="button" data-icon="arrow-r" data-iconpos="notext" data-theme="d">Next</a>
          <a href="#" id="btnPrevious" class="prev" data-role="button" data-icon="arrow-l" data-iconpos="notext" data-theme="d">Previous</a>
        </div>
      </div>
      <!-- footer -->
    </div>



  </body>
</html>
