<!DOCTYPE html>
<html>
<head>
    <title>Selection Form</title>
    <script src="deleteFormSubmitter.js"></script>
</head>
<body>
    <form id="selectionForm" action="deleteFormHandler.php" method="post">
        <!-- Dropdown Menu -->
        <select id="dropdown" name="dropdown">
            <option value="">--Select an Option--</option>
            <?php
            include "listTA.php"
            ?>
        </select>

        <!-- Manual Input Field -->
        <input type="text" id="manualInput" name="manualInput" placeholder="Or Enter Manually">

        <!-- Submit Button -->
        <button type="button" id="submitSelection">Submit</button>
    </form>
    <div id="result"></div> <!-- Div to display the result -->
</body>
</html>
