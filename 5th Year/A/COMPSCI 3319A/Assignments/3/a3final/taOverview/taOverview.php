<!DOCTYPE html>
<html>
<head>
    <title>Course Offer Information</title>
    <script src="taOverviewSubmitter.js"></script>
</head>
<body>
    <form id="taOverviewForm" onsubmit="return submitForm()">
        <select name="dropdownSelection" id="dropdownSelection">
            <option value="">Order By</option>
            <option value="lastname ASC">lastname ASC</option>
            <option value="lastname DESC">lastname DESC</option>
            <option value="degreetype">degreetype</option>
        </select>
        <input type="submit" value="Submit">
    </form>

    <div id="result"></div> <!-- Result will be displayed here -->

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>
