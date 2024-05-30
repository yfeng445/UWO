window.onload = function() {
    // Existing event listeners...
    // ...

    // Event Listener for Complex Form Submit Button
    var submitComplexFormBtn = document.getElementById("submitComplexForm");
    if (submitComplexFormBtn) {
        submitComplexFormBtn.addEventListener("click", function() {
            submitFormWithAjax("complexForm", "resultComplexForm");
        });
    }
};

// Existing submitFormWithAjax function...
// ...

