document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("submitCheckboxForm").addEventListener("click", function() {
        var form = document.getElementById("checkboxForm");
        var xhr = new XMLHttpRequest();
        xhr.open("POST", form.action, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById("result").innerHTML = xhr.responseText;
            }
        };

        var formData = new FormData(form);
        xhr.send(new URLSearchParams(formData).toString());
    });
});
