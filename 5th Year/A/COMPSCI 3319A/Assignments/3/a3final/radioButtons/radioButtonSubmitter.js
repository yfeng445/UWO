document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('radioForm').addEventListener('submit', function(e) {
        e.preventDefault();

        // Create an instance of FormData to capture form data
        const formData = new FormData(this);

        // Send a POST request to radioButtonHandler.php
        fetch('radioButtonHandler.php', {
            method: 'POST',
            body: formData
        })
        .then(response => response.text())
        .then(text => {
            // Display the result in the 'result' div
            document.getElementById('result').innerHTML = text;
        })
        .catch(error => console.error('Error:', error));
    });
});
