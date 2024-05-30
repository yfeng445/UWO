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


    List ta by:
    <!-- Radio Button Form -->
    <form id="formRadio" action="submitFormRadio.php" method="post">
    <label for="radio1">Degree</label>
    <input type="radio" id="radio1" name="A" value="radio1"><br>
    <label for="radio2">LName ASC</label>
    <input type="radio" id="radio2" name="B" value="radio2"><br>
    <label for="radio3">LName DESC</label>
    <input type="radio" id="radio3" name="C" value="radio3"><br>
        
        
    </form>
    <div id="resultRadio"></div> <!-- Div to display results from Radio Form -->


    <!-- Form A -->
    <form id="formA" action="submitFormA.php" method="post">
        <select id="elementA" name="elementA">
            <option value="optionA1">Option A1</option>
            <option value="optionA2">Option A2</option>
        </select>
    </form>
    <div id="resultA"></div> <!-- Div to display results from Form A -->

    <!-- Form B -->
    <form id="formB" action="submitFormB.php" method="post">
        <select id="elementB" name="elementB">
            <option value="optionB1">Option B1</option>
            <option value="optionB2">Option B2</option>
        </select>
    </form>

    <div id="resultB"></div> <!-- Div to display results from Form B -->
</body>

</html>