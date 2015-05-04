$(function(){

  $('tr').on('click', function(){
    $('.info').removeClass('info');
    var element = $(this);
    element.addClass("info");
  });

});