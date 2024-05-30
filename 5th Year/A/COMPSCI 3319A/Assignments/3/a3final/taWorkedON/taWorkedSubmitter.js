function submitForm() {
    var form = document.getElementById('selectionForm');
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'taWorkedonHandler.php', true);

    xhr.onload = function () {
        if (this.status == 200) {
            document.getElementById('result').innerHTML = this.responseText;
        }
    };

    xhr.send(formData);
    return false; // Prevent page reload
}
