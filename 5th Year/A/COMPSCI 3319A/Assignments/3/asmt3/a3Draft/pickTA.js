window.onload = function() {
    prepareListener();
 }
 function prepareListener() {
     var droppy;
     droppy = document.getElementById("pickaTA");
     droppy.addEventListener("change",submitForm);
 }
 function submitForm() {
    this.form.submit();
 }