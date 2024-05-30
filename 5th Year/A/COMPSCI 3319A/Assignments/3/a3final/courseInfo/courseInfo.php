<!DOCTYPE html>
<html>
<head>
    <title>Course Information Form</title>
    <script src="courseInfoSubmitter.js"></script>
    <style>
        #additionalSelections { display: none; }
    </style>
</head>
<body>
    <form id="courseInfoForm" action="courseInfoHandler.php" method="post">
        <!-- Initial Dropdown Menu -->
        <select id="initialDropdown" name="initialChoice" onchange="showAdditionalSelections()">
            <option value="">--Select a Course--</option>
            <?php include "listCourse.php" ?>
        </select>

        <!-- Additional Dropdown Menus -->
        <div id="additionalSelections">
            <select id="secondDropdown" name="secondChoice">
                <option value="">--Start Year--</option>
                <?php include "listYear.php" ?>
            </select>

            <select id="thirdDropdown" name="thirdChoice">
                <option value="">--End Year--</option>
                <?php include "listYear.php" ?>
            </select>

            <button type="button" id="submitCourseInfo">Submit</button>
        </div>
        <?php 
        echo "<br>Min year: " . $min . "<br>";
        echo "Max year: " . $max . "<br>";
        ?>
    </form>
    <div id="result"></div> <!-- Div to display the result -->

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>
