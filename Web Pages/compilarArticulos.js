var compilarArticulos = function () {
    var articulos = [];
    $('.articulo').each(function () {
        if ($(this).is(':checked')) {
            articulos.push($(this).attr('name'));
        }
    });
    $('#articulos').val(articulos.join(','));
    alert($('#articulos').val());
};

var permitePublicacion = function (min, max) {
    var total = 0;
    $('.articulo').each(function () {
        if ($(this).is(':checked')) {
            total++;
        }
    });
    
    var puedePublicar = total >= min && total <= max;
    var boton = $('#publicar');
    if (puedePublicar) {
        boton.removeAttr('disabled')
    } else {
        boton.attr('disabled', 'disabled');
    }
    
};
