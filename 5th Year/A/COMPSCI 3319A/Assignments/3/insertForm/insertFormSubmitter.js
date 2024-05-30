window.onload = function() {
    // Existing event listeners...
    // ...

    // Event Listener for Complex Form Submit Button
    var radios = document.querySelectorAll('input[name="radioOption"]');
    radios.forEach(function(radio) {
        radio.addEventListener("change", function() {
            submitFormWithAjax("formRadio", "resultRadio");
        });
    });
};

// Existing submitFormWithAjax function...
// ...

