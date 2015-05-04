var compilarAutores = function () {
    var autores = [];
    $('.autor').each(function () {
       if ($(this).is(':checked')) {
           autores.push($(this).attr('id').slice(5));
       }
    });
    $('#autores').val(autores.join(','));
};