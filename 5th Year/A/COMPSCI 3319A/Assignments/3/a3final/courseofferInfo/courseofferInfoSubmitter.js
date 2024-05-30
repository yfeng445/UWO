function submitForm() {
    var form = document.getElementById('courseOfferForm');
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'courseofferInfoHandler.php', true);

    xhr.onload = function () {
        if (this.status == 200) {
            document.getElementById('result').innerHTML = this.responseText;
        }
    };

    xhr.send(formData);
    return false; // Prevent the default form submission
}
