function NewsBoard(config) {
  try {
    var container = config.container;
    var newsList = config.newsList;
    var maxTextWidth = config.maxTextWidth;
    var interval = config.interval;
    var size = config.size;
    var imageSize = config.imageSize;
    var nodeImage, nodeTitle, nodeDescription;
    var refInterval;
    var index = 0;

    var l = newsList.length;
    var images = [];
    for (var i = 0; i < l; ++i) {
      var img = new Image();
      img.src = newsList[i].image;
      images.push(img);
    }

    if (Kinetic === undefined) {
      alert("kineticJS is not loaded!");
      return;
    }

    var stage = new Kinetic.Stage({
      container: container,
      width: size.width,
      height: size.height
    });

    var layer1 = new Kinetic.Layer();

    _initLayer1();

    stage.add(layer1);
    layer1.draw();

    _initLoop();
  } catch (e) {
    console.log("Error: " + e);
  }

  // private methods  
  function _initLayer1() {
    var news = newsList[0];
    if (news === undefined || news === null) {
      console.log("News not found!");
      return;
    }

    nodeTitle = new Kinetic.Text({
      text: _fixText(news.title),
      x: stage.width() / 2,
      y: 10,
      fontFamily: "tahoma",
      fontSize: 12,
      fill: "blue",
      align: "right",
      opacity: 1
    });
    nodeTitle.x(stage.width() - nodeTitle.width() - 10);

    nodeDescription = new Kinetic.Text({
      text: _fixText(_breakText(news.description, maxTextWidth)),
      x: stage.width() / 2,
      y: 30,
      fontFamily: "tahoma",
      fontSize: 12,
      fill: "black",
      align: "right",
      lineHeight: 1.5,
      opacity: 1
    });
    nodeDescription.x(stage.width() - nodeDescription.width() - 10);

    nodeTitle.on("mouseover", function(event) {
      $(document.body).css({"cursor": "pointer"});
    });

    nodeTitle.on("mouseout", function(event) {
      $(document.body).css({"cursor": "default"});
    });

    nodeTitle.on("click", function(event) {
      window.open(newsList[index].link);
    });

    if (news.image !== undefined && news.image !== null) {
      var img = new Image();
      img.addEventListener("load", function() {
        var imgSize = _calculateSize(img);
        nodeImage = new Kinetic.Image({
          x: 10,
          y: 10,
          width: imgSize.width,
          height: imgSize.height,
          image: img,
          stroke: "black",
          strokeWidth: 1,
          opacity: 1
        });

        nodeImage.on("mouseover", function(event) {
          $(document.body).css({"cursor": "pointer"});
        });

        nodeImage.on("mouseout", function(event) {
          $(document.body).css({"cursor": "default"});
        });

        nodeImage.on("click", function(event) {
          window.open(newsList[index].link);
        });

        layer1.add(nodeImage);
        layer1.drawScene();
        stage.draw();
      });
      img.src = news.image;
    }

    layer1.add(nodeTitle);
    layer1.add(nodeDescription);
  }

  function _breakText(text, width) {
    var result = "";
    var len = text.length;
    var count = 0;
    for (var i = 0; i < len; ++i) {
      if (count >= width && text[i] === ' ') {
        result += "\n";
        count = 0;
      }

      result += text[i];
      count++;
    }

    return result;
  }

  function _fixText(text) {
    try {
      var len = text.length;
      if (text[len - 1] === '.' || text[len - 1] === '!' || text[len - 1] === '?') {
        return text.substr(0, len - 1);
      }
    } catch (e) {
      console.log("Error in fixText: " + e);
    }
    return text;
  }

  function _calculateSize(image) {
    var size = null;
    if (image.width <= imageSize.width && image.height <= imageSize.height) {
      return imageSize;
    } else {      
      if (image.width > imageSize.width) {
        var ratio = imageSize.width / image.width;
        var size = {width: imageSize.width, height: image.height * ratio};        
      }

      if (image.height > imageSize.height || size.height > imageSize.height) {
        var ratio = imageSize.height / image.height;
        var size = {height: imageSize.height, width: image.width * ratio};        
      }
    }
    
    return size;
  }

  function _initLoop() {
    refInterval = setInterval(function() {
      //console.log("interval");
      if (index < newsList.length - 1) {
        index++;
      } else {
        index = 0;
      }

      var nodeTitlePos = {x: nodeTitle.x(), y: nodeTitle.y()};
      var nodeDescriptionPos = {x: nodeDescription.x(), y: nodeDescription.y()};
      var nodeImagePos = {x: nodeImage.x(), y: nodeImage.y()};

      var animTitle = new Kinetic.Animation(function(frame) {
        if (frame.time < 50) {
          nodeTitle.y(nodeTitle.y() + 3);
        } else if (frame.time < 100) {
          nodeTitle.y(nodeTitle.y() + 3);
        } else if (frame.time < 400) {
          nodeTitle.y(nodeTitle.y() - 4);
          if (nodeTitle.opacity() > 0.1) {
            nodeTitle.opacity(nodeTitle.opacity() - 0.1);
          }
        } else {
          this.stop();
          nodeTitle.opacity(1.0);
        }
      }, layer1);

      var animDescription = new Kinetic.Animation(function(frame) {
        if (frame.time < 50) {
          nodeDescription.x(nodeDescription.x() - 4);
        } else if (frame.time < 100) {
          nodeDescription.x(nodeDescription.x() - 6);
        } else if (frame.time < 500) {
          nodeDescription.x(nodeDescription.x() + 12);
          if (nodeDescription.opacity() > 0.1) {
            nodeDescription.opacity(nodeDescription.opacity() - 0.1);
          }
        } else if (frame.time < 700) {
          nodeDescription.x(nodeDescription.x() + 15);
          if (nodeDescription.opacity() > 0.1) {
            nodeDescription.opacity(nodeDescription.opacity() - 0.1);
          }
        } else {
          this.stop();
          nodeDescription.opacity(1.0);
        }
      }, layer1);

      var animImage = new Kinetic.Animation(function(frame) {
        if (frame.time < 50) {
          nodeImage.x(nodeImage.x() + 5);
        } else if (frame.time < 100) {
          nodeImage.x(nodeImage.x() + 7);
        } else if (frame.time < 500) {
          nodeImage.x(nodeImage.x() - 10);
          if (nodeImage.opacity() > 0.1) {
            nodeImage.opacity(nodeImage.opacity() - 0.1);
          }
        } else {
          this.stop();
          nodeImage.opacity(1.0);
        }
      }, layer1);

      animTitle.start();
      animDescription.start();
      animImage.start();

      setTimeout(function() {
        //console.log("second");
        var news = newsList[index];
        nodeTitle.text(_fixText(news.title));
        nodeDescription.text(_fixText(_breakText(news.description, maxTextWidth)));
        var img = new Image();
        img.addEventListener("load", function() {
          nodeImage.image(img);
          var imgSize = _calculateSize(img);
          nodeImage.width(imgSize.width);
          nodeImage.height(imgSize.height);
          layer1.drawScene();
        });
        img.src = news.image;

        nodeTitle.x(stage.width() - nodeTitle.width() - 10);
        nodeTitle.y(nodeTitlePos.y);
        nodeDescription.x(stage.width() - nodeDescription.width() - 10);
        nodeDescription.y(nodeDescriptionPos.y);
        nodeImage.x(nodeImagePos.x);
        nodeImage.y(nodeImagePos.y);

        stage.draw();
      }, 1000);
    }, interval);
  }
  // public methods
  this.initLayer2 = function() {

  }
}

