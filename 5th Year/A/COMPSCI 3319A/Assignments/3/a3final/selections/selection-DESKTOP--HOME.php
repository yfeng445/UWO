<!DOCTYPE html>
<html>

<head>
    <title>CS3319 - ASMT3</title>
    <script src="selectionFormSubmitter.js"></script>
</head>

<body>
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

</body>

</html>