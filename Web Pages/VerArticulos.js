$(function(){

  $('tbody tr').on('click', function(){
    $('.selected').removeClass('selected');
    var element = $(this);
    element.addClass("selected");
  });

});