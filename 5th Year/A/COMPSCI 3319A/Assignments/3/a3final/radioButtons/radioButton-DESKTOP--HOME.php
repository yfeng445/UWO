<!DOCTYPE html>
<html>
<head>
    <title>CS3319 - ASMT3</title>
    <script src="radioFormSubmitter.js"></script>
</head>

<body><!-- Radio Button Form -->
    List all TAs in:
    <form id="formRadio" action="infoRadio.php" method="post">
        <input type="radio" id="degreeType" name="radioOption" value="degreeType">
        <label for="degreeType">Degree Type</label>
        <input type="radio" id="lNameAsc" name="radioOption" value="lastname ASC">
        <label for="lNameAsc">LName ASC</label>
        <input type="radio" id="lNameDesc" name="radioOption" value="lastname DESC">
        <label for="lNameDesc">LName DESC</label>
    </form>
    <div id="resultRadio"></div> <!-- Div to display results from Radio Form -->
</body>

</html>