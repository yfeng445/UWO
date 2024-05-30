function submitForm() {
    var form = document.getElementById('taOverviewForm');
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'taOverviewHandler.php', true);

    xhr.onload = function () {
        if (this.status == 200) {
            document.getElementById('result').innerHTML = this.responseText;
        }
    };

    xhr.send(formData);
    return false; // Prevent the default form submission
}
