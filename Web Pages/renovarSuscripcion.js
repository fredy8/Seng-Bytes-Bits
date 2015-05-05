$(function(){
  $('#check').change(function(){
    if (this.checked) {
      $('#discount').text('Gratis');
    } else {
      $('#discount').text('Precio Normal');
    };
  });
});