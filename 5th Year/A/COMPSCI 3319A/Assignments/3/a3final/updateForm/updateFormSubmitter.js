function showInputField() {
    var dropdownValue = document.getElementById("dropdown").value;
    var userInputField = document.getElementById("userInputField");
    userInputField.style.display = dropdownValue ? "block" : "none";
}

document.addEventListener("DOMContentLoaded", function() {
    var submitBtn = document.getElementById("submitUpdateForm");
    submitBtn.addEventListener("click", function() {
        submitFormWithAjax("updateForm", "result");
    });
});

function submitFormWithAjax(formId, resultId) {
    var form = document.getElementById(formId);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resultElement = document.getElementById(resultId);
            resultElement.innerHTML = xhr.responseText;
            clearForm(form);
        }
    };

    var formData = new FormData(form);
    xhr.send(new URLSearchParams(formData).toString());
}

function clearForm(form) {
    form.reset();
    document.getElementById("userInputField").style.display = "none";
}
