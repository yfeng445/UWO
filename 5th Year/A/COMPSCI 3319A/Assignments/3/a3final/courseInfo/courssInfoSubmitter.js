function showAdditionalSelections() {
    var initialDropdown = document.getElementById("initialDropdown").value;
    var additionalSelections = document.getElementById("additionalSelections");
    additionalSelections.style.display = initialDropdown ? "block" : "none";
}

document.addEventListener("DOMContentLoaded", function() {
    var submitBtn = document.getElementById("submitCourseInfo");
    submitBtn.addEventListener("click", function() {
        submitFormWithAjax("courseInfoForm", "result");
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
    document.getElementById("additionalSelections").style.display = "none";
}
