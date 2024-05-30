document.addEventListener("DOMContentLoaded", function() {
    var submitBtn = document.getElementById("submitSelection");
    submitBtn.addEventListener("click", function() {
        // Show confirmation dialog
        var userConfirmed = confirm("Are you sure you want to submit this selection?");
        if (userConfirmed) {
            // If user clicks 'Yes', proceed with form submission
            submitFormWithAjax("selectionForm", "result");
        }
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
            clearForm(form); // Clear the form after successful submission
        }
    };

    var formData = new FormData(form);
    xhr.send(new URLSearchParams(formData).toString());
}

function clearForm(form) {
    form.reset();
}
