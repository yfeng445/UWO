<!DOCTYPE html>
<html>
<head>
    <title>Course Offer Information</title>
    <script src="courseofferInfoSubmitter.js"></script>
</head>
<body>
    <form id="courseOfferForm" onsubmit="return submitForm()">
        <select name="dropdownSelection" id="dropdownSelection">
            <option value="">Select a courseoffer</option>
            <?php include "listCO.php"?>
        </select>
        <input type="submit" value="Submit">
    </form>

    <div id="result"></div> <!-- Result will be displayed here -->

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>
