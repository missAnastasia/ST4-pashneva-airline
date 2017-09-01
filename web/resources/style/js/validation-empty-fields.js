document.getElementById('f').addEventListener('submit', function(e) {
    var inputs = document.querySelectorAll('.field-form'), result, i;

    for(i = 0; i < inputs.length; i++) {
        if(inputs[i].value) {
            result = true;
            break;
        }
    }

    if(!result) {
        e.preventDefault();
        alert('Заполните хотя бы одно поле!');
    }
});