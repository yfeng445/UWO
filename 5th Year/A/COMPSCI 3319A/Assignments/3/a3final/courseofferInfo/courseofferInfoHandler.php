<?php
/*
Allow the user to select a course offering and display the course number 
and name and the first and last names 
and user ids of all t.a.s  who have worked on this course offering.
*/
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $selectedValue = $_POST['dropdownSelection'];
    echo "The courseoffer you picked: " . htmlspecialchars($selectedValue) . "<br>";
    include "connectdb.php";
    $query = "SELECT * FROM courseoffer 
    JOIN course ON courseoffer.whichcourse = course.coursenum
    WHERE coid = '$selectedValue'";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            echo "coid: " . $row["coid"] . ", Course: " . $row["whichcourse"] . ": ".$row["coursename"].", term: " . $row["term"] . ", year: " . $row["year"] . "<br>";
        }
    } else {
        echo "No course offering found.<br>";
    }
    $query = "SELECT * FROM hasworkedon 
    JOIN ta ON hasworkedon.tauserid = ta.tauserid
    WHERE coid = '$selectedValue'";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        echo "TA(s) who have worked on this course offer: <br>";
        while ($row = mysqli_fetch_assoc($result)) {
            echo "tauserid: " . $row["tauserid"] . ", firstname: " . $row["firstname"] . ", lastname: " . $row["lastname"] ."<br>";
        }
    } else {
        echo "No TA worked on this course offer.<br>";
    }
}
mysqli_close($connection);
?>
