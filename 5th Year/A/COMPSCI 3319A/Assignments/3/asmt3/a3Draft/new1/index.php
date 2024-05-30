<!DOCTYPE html>
<html>

<head>
    <title>CS3319 - ASMT3</title>
    <script src="formSubmitter.js"></script>
</head>

<body>
    <?php
    session_start();
    include 'connectdb.php'
        ?>
    <h1>CS3319 - Assignment 3</h1>
    <!-- Radio Button Form -->
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

    <!-- Form A -->
    Select a TA to get more info:
    <form id="formA" action="submitFormA.php" method="post">
        <select id="elementA" name="elementA">
            <option value="">Select</option>
            <?php
            include "listTA.php";
            ?>
        </select>
    </form>
    <div id="resultA"></div> <!-- Div to display results from Form A -->

    <!-- Form B -->
    Select by degreeType:
    <form id="formB" action="submitFormB.php" method="post">
        <select id="elementB" name="elementB">
            <option value="">Select</option>
            <option value="Masters">Masters</option>
            <option value="PhD">PhD</option>
        </select>
    </form>
    <div id="resultB"></div> <!-- Div to display results from Form B -->


    <!-- Complex Form -->
    <?php include "complexForm.php"?>


</body>
</html>