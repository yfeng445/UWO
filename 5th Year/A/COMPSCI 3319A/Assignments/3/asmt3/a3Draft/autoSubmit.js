window.onload = function() {
    var elementA = document.getElementById("elementA");
    var elementB = document.getElementById("elementB");
    var resultA = document.getElementById("resultA");
    var resultB = document.getElementById("resultB");

    if (elementA && resultA) {
        elementA.addEventListener("change", function() {
            submitFormWithAjax("formA", "resultA");
        });
    }

    if (elementB && resultB) {
        elementB.addEventListener("change", function() {
            submitFormWithAjax("formB", "resultB");
        });
    }
    
    // Radio Button Event Listener
    var radios = document.querySelectorAll('input[name="radioOption"]');
    radios.forEach(function(radio) {
        radio.addEventListener("change", function() {
            submitFormWithAjax("formRadio", "resultRadio");
        });
    });




};


function submitFormWithAjax(formId, resultId) {
    var form = document.getElementById(formId);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resultElement = document.getElementById(resultId);
            if (resultElement) {
                resultElement.innerHTML = xhr.responseText;
            }
        }
    };

    var formData = new FormData(form);
    xhr.send(new URLSearchParams(formData).toString());
}
