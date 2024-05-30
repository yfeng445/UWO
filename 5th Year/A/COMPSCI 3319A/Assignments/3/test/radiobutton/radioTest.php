<!DOCTYPE html>
<html>
<head>
    <title>RT</title>
    <script src="formSubmitter.js"></script>
</head>
<body>
    
    <!-- Radio Button Form -->
    <form id="formRadio" action="submitFormRadio.php" method="post">
        <input type="radio" id="radio1" name="radioOption" value="radio1">
        <label for="radio1">Radio 1</label>
        <input type="radio" id="radio2" name="radioOption" value="radio2">
        <label for="radio2">Radio 2</label>
        <input type="radio" id="radio3" name="radioOption" value="radio3">
        <label for="radio3">Radio 3</label>
    </form>
    <div id="resultRadio"></div> <!-- Div to display results from Radio Form -->

    </body>
</html>
