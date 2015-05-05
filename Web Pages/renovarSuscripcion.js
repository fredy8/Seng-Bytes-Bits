$(function(){
  $('#check').change(function(){
    if (this.checked) {
      $('#discount').text('Gratis');
      $('#extra_discount').text('-');
    } else {
      $('#discount').text('Precio Normal');
    };
  });

  $('select').on('change', function(){
    // var value = parseInt($(this).val());
    // var num = (value - 1) * 25;
    // num.toString();
    var discount = "-";
    console.log(parseInt($(this).val()));
    if (parseInt($(this).val()) > 1) {
     discount = "%25 en años pasados"; 
    };
    $('#extra_discount').text(discount);
  });

});