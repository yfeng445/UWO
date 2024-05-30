window.onload = function() {
    var elementA = document.getElementById("elementA");
    var elementB = document.getElementById("elementB");
    var resultA = document.getElementById("resultA");
    var resultB = document.getElementById("resultB");
    if (elementA && resultA) {
        elementA.addEventListener("change", function() {
            submitFormWithAjax("formA", "resultA");
        });
    }
    if (elementB && resultB) {
        elementB.addEventListener("change", function() {
            submitFormWithAjax("formB", "resultB");
        });
    }
    var submitInputBtn = document.getElementById("submitUserInput");
    if (submitInputBtn) {
        submitInputBtn.addEventListener("click", function() {
            submitFormWithAjax("formUserInput", "resultUserInput");
        });
    }

};

