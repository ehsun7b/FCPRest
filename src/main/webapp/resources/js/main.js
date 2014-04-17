$(function() {
  var input = $(":input:visible:enabled:first");
  if (input !== null && input !== undefined) {
    $(input).focus();
  }
});