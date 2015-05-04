$(function(){

  $('tr').on('click', function(){
    var element = $(this);
    element.addClass("info");
    alert('Fuck you');
  });

});