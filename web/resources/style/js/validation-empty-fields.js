/*document.getElementById('one-not-empty').addEventListener('submit', function(e) {
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
});*/

/*
function validate(form) {
    var inputs = form.elements, result, i;

    for(i = 0; i < inputs.length; i++) {
        if(inputs[i].value) {
            result = true;
            break;
        }
    }

    if(!result) {
        alert('Заполните хотя бы одно поле!');
    }
}*/

function validate(){
    //Считаем значения из полей name и email в переменные x и y
    var x=document.forms["one_not_empty"]["from_city"].value;
    var y=document.forms["one_not_empty"]["to_city"].value;
    var z=document.forms["one_not_empty"]["departure_date"].value;

    if (x.length!=0){
        return true;
    }

    if (y.length!=0){
        return true;
    }

    if (z.length!=0){
        return true;
    }
    return false;
}