$(window).load(function() {
  //var css = $("<link/>", {"rel": "stylesheet", "type": "text/css", "href": "/css/fonts-min.css"});

  //$('head').append(css);

  WebFontConfig = {
    google: {families: ['Joti+One::latin']}
  };
  (function() {
    var wf = document.createElement('script');
    wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
            '://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
    wf.type = 'text/javascript';
    wf.async = 'true';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(wf, s);
  })();
});
