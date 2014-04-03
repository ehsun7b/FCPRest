<%-- 
    Document   : mobile_home
    Created on : Mar 12, 2014, 12:57:28 PM
    Author     : ehsun7b
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="format-detection" content="telephone=no" />
    <link rel="shortcut icon" href="/favicon.ico" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=medium-dpi" />
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
    <!--<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCRYiaS1SawMg6ybt0-39FFqUbHMtEtzsU&sensor=false"></script>-->
    <link rel="stylesheet" href="js/rtl.jquery.mobile-1.4.0/css/themes/default/rtl.jquery.mobile-1.4.0.css" />    
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/rtl.jquery.mobile-1.4.0/js/rtl.jquery.mobile-1.4.0.js"></script>
    <!--
    <script src="js/mobile.js"></script>
    <script src="js/mobile_results.js"></script>
    <script src="js/mobile_stadium_map.js"></script>
    <script src="js/news.js"></script>
    <script src="js/mobile_news.js"></script>
    -->
    <script src="js/mobile_compressed.js"></script>
    <link rel="stylesheet" type="text/css" href="css/mobile.css"/>    
    <title>FC Persepolis</title>
  </head>
  <body>

    <div id="pageHome" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <p style="text-align: center">پایگاه خبری پرسپولیس</p>
      </div>

      <!-- content -->
      <div data-role="content">
        <div data-role="controlgroup">
          <a data-role="button" data-theme="a" data-icon="" href="#pageMatchInfo">برنامه بازی‌ها</a>
          <a data-role="button" data-theme="a" data-icon="" href="#pageRanking">جدول لیگ برتر</a>
          <a data-role="button" data-theme="a" data-icon="" href="#pageNews">اخبار</a>
        </div>
      </div>

      <div class="advert" id="home_adsense">
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- FCPersepolisMobileBanner -->
        <ins class="adsbygoogle"
             style="display:inline-block;width:320px;height:100px"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7953692798"></ins>
        <script>
          (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>

      <div data-role="footer" data-id="footer" data-position="fixed" data-theme="a">

      </div>
      <!-- footer -->
    </div>

    <div id="pageMatchInfo" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>برنامه بازی‌ها</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <div id="results"></div>

        <div class="map">
          <h4>محل برگزاری</h4>          
          <div id="map-canvas"></div>
          <input type="button" value="فاصله من ؟" id="btnDistance"/>
          <div id="distance"></div>
        </div>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" data-rel="back">صفحه اصلی</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

    <div id="pageRanking" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>جدول لیگ برتر</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <div id="results"></div>

        <div class="map">          
          <div id="ranking"></div>
        </div>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" data-rel="back">صفحه اصلی</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

    <div id="pageNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>اخبار</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <div data-role="controlgroup">
          <a data-role="button" data-theme="b" href="#pageOfficialNews">اخبار سایت رسمی باشگاه</a>
          <a data-role="button" data-theme="b" href="#pageArteshNews">اخبار سایت ارتش سرخ</a>
          <a data-role="button" data-theme="b" href="#pageVarzesh3News">اخبار سایت ورزش۳</a>
          <a data-role="button" data-theme="b" href="#pageIsnaNews">اخبار سایت ایسنا</a>
          <a data-role="button" data-theme="b" href="#pageIrnaNews">اخبار سایت ایرنا</a>
          <a data-role="button" data-theme="b" href="#pageKhabarOnlineNews">اخبار سایت خبرآنلاین</a>
        </div>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" data-rel="back">صفحه اصلی</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>    

    <div id="pageOfficialNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت باشگاه</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="official_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>    

    <div id="pageArteshNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت ارتش سرخ</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="artesh_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

    <div id="pageVarzesh3News" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت ورزش۳</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="varzesh3_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

    <div id="pageIsnaNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت ایسنا</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="isna_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div> 

    <div id="pageIrnaNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت ایرنا</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="irna_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

    <div id="pageKhabarOnlineNews" data-role="page">
      <!-- header -->
      <div data-role="header" data-id="headerH" data-theme="a">
        <h2>سایت خبرآنلاین</h2>
      </div>

      <!-- content -->
      <div data-role="content">
        <ul data-role="listview" id="khabaronline_news_content"></ul>
      </div>

      <div data-role="footer" data-id="footer" data-theme="a" data-position="fixed">
        <div data-role="navbar">
          <ul>
            <li><a data-rol="button" href="#pageHome">صفحه اصلی</a></li>
            <li><a data-rol="button" data-rel="back">اخبار</a></li>
          </ul>
        </div>        
      </div>
      <!-- footer -->
    </div>

  </body>
</html>
