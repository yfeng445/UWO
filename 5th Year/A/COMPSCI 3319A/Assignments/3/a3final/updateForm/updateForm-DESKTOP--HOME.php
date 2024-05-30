<!DOCTYPE html>
<html>
<head>
    <title>Update Form</title>
    <script src="updateFormSubmitter.js"></script>
    <style>
        #userInputField { display: none; }
    </style>
</head>
<body>
    <form id="updateForm" action="updateFormHandler.php" method="post">
        <!-- Dropdown Menu -->
        <select id="dropdown" name="dropdown" onchange="showInputField()">
            <option value="">--Select an Option--</option>
            <?php include "listTA.php" ?>
        </select>

        <!-- Hidden Manual Input Field -->
        <div id="userInputField">
            <input type="text" id="manualInput" name="manualInput" placeholder="image url">
            <button type="button" id="submitUpdateForm">Submit</button>
        </div>
    </form>
    <div id="result"></div> <!-- Div to display the result -->
</body>
</html>
