<!DOCTYPE html>
<html>

<head>
    <title>ta Offer Information</title>
    <script src="taInfoSubmitter.js"></script>
</head>

<body>
    <form id="taForm" onsubmit="return submitForm()">
        <select name="dropdownSelection" id="dropdownSelection">
            <option value="">Select a ta</option>
            <?php include "listTA.php" ?>
        </select>
        <input type="submit" value="Submit">
    </form>

    <div id="result"></div> <!-- Result will be displayed here -->

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>

</html>