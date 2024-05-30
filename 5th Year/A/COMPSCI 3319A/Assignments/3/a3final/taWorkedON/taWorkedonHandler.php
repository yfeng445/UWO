<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $selectedValue = $_POST['dropdownSelection'];
    $query = "SELECT * FROM 
    ((hasworkedon JOIN courseoffer ON hasworkedon.coid = courseoffer.coid)
    JOIN course ON courseoffer.whichcourse = course.coursenum)
    WHERE tauserid = '$selectedValue';";
    include "connectdb.php";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        echo "<table>";
        echo "<tr><th>Course Number</th><th>Course Name</th><th>Term</th><th>Year</th><th>Hours</th><th>Love/Hate</th></tr>";  // Add new column header
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr><td>" . $row["whichcourse"] . "</td><td>" . $row["coursename"] . "</td><td>" . $row["term"] . "</td><td>" . $row["year"] . "</td><td>" . $row["hours"] . "</td><td>";
            include "LoveHate.php";  
            echo "</td></tr>";
        }
        echo "</table>";
    } else {
        echo "No course offering found.<br>";
    }
}
mysqli_close($connection);
?>
