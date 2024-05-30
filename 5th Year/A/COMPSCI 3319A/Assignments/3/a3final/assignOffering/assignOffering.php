<!DOCTYPE html>
<html>
<head>
    <title>Assign Offering</title>
    <script src="./assignOfferingSubmitter.js"></script>
    <style>
        #numberInput { display: none; }
    </style>
</head>
<body>
    
    <form id="assignOfferingForm" action="assignOfferingHandler.php" method="post">
        <!-- First Dropdown Menu -->
        Assign 
        <select id="firstDropdown" name="firstChoice" onchange="checkSelections()">
            <option value="">--Select a TA--</option>
            <?php include "listTA.php"?>
        </select>

        <!-- Second Dropdown Menu -->
        to:
        <select id="secondDropdown" name="secondChoice" onchange="checkSelections()">
            <option value="">--Select a CO--</option>
            <?php include "listCO.php"?>
        </select>

        <!-- Number Input Field -->
        <div id="numberInput">
            <input type="number" id="number" name="number" placeholder="Enter hours of work">
            <button type="button" id="submitAssignOffering">Submit</button>
        </div>
    </form>
    <div id="result"></div> <!-- Div to display the result -->
    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>
