$('.text').focusin(function (e) {
    e.preventDefault();

    this.style.transition = '0.4s';
    this.style.borderColor = '#009dff';

    var label = $("label[for='" + $(this).attr('id') + "']");

    if (this.value === '') {
        console.log(this.value)

        $(label).toggle('hide');    
    }
});

$('.text').focusout(function (e) {
    e.preventDefault();

    this.style.transition = '0.4s';
    this.style.borderColor = '';

    var label = $("label[for='" + $(this).attr('id') + "']");

    if (this.value === '') {
        console.log(this.value)
        $(label).toggle('show');
    }
});