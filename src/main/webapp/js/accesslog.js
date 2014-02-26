var ip2locUrl = "http://freegeoip.net/";
var ip2locFormat = "json";

$(function() {
	console.log("document ready!");
	$("td.ip").each(function(index, td) {				
		var ip = $(td).html();
		
		$.ajax({
      type: 'GET',
      url: ip2locUrl + "/" + ip2locFormat + "/" + ip,
      contentType: "application/json",
      dataType: 'jsonp',
      jsonp: 'callback',
      crossDomain: true,
      success: function(data, status, jqXHR) {   
      	alert(data);
      	alert(data.country_name);
        var countryName = data.country_name;
        $($("td.ip")[index]).append($("<span/>", {class: "country"}).append(countryName));
      }      
    });
	});
});