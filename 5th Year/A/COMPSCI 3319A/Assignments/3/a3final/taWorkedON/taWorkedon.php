<!DOCTYPE html>
<html>
<head>
    <title>Dropdown Selection</title>
    <script src="taWorkedonSubmitter.js"></script>
</head>
<body>
    <?php echo "TA Worked On" ?>;
    <form id="selectionForm" onsubmit="return submitForm()">
        <select name="dropdownSelection" id="dropdownSelection">
            <option value="">Select a TA</option>
            <?php include "listTA.php"?>
        </select>
        <input type="submit" value="Submit">
    </form>

    <div id="result"></div> 

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>
