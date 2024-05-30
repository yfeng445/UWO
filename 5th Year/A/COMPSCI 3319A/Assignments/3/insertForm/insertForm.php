<!DOCTYPE html>
<html>

<head>
    <script src="insertFormSubmitter.js"></script>
</head>

<body>

    <!-- Complex Form -->
    <form id="insertForm" action="insertFormHandler.php" method="post">
        <!-- Four Input Fields -->
        <input type="text" id="tauserid" name="tauserid" placeholder="tauserid">
        <input type="text" id="firstname" name="firstname" placeholder="firstname">
        <input type="text" id="lastname" name="lastname" placeholder="lastname">
        <input type="text" id="studentnum" name="studentnum" placeholder="studentnum">

        <!-- degreetype Box -->
        <select id="degreetypeBox" name="degreetype">
            <option value="PhD">PhD</option>
            <option value="Masters">Masters</option>
        </select>

        <!-- First Set of Checkboxes -->
        <div>
            <?php
            include "loveCourses.php";
            ?>
        </div>

        <!-- Second Set of Checkboxes -->
        <div>
            <?php
            include "hateCourses.php";
            ?>
        </div>

        <!-- Additional Input Field -->
        <input type="text" id="image" name="image" placeholder="image">

        <!-- Submit Button -->
        <button type="button" id="submitinsertForm">Submit</button>
    </form>
    <div id="resultinsertForm"></div> <!-- Div to display results from Complex Form -->

    <p>
        <a href="../index.php">Go back to main page</a>
    </p>
</body>
</html>