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
                <option value="">--Select Option 1--</option>
                <option value="option1">Option 1</option>
                <option value="option2">Option 2</option>
            </select>

            <select id="thirdDropdown" name="thirdChoice">
                <option value="">--Select Option 2--</option>
                <option value="option1">Option 1</option>
                <option value="option2">Option 2</option>
            </select>

            <button type="button" id="submitCourseInfo">Submit</button>
        </div>
    </form>
    <div id="result"></div> <!-- Div to display the result -->
</body>
</html>
