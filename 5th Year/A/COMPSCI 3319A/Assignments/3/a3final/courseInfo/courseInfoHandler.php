<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $initialChoice = isset($_POST['initialChoice']) ? $_POST['initialChoice'] : '';
    $secondChoice = isset($_POST['secondChoice']) ? $_POST['secondChoice'] : '';
    $thirdChoice = isset($_POST['thirdChoice']) ? $_POST['thirdChoice'] : '';
    echo "The course you picked: " . htmlspecialchars($initialChoice) . "<br>";
    include "connectdb.php";
    include "listYear.php"; // $min and $max are defined in "listYear.php
    $query = "SELECT * FROM courseoffer WHERE whichcourse = '$initialChoice'";
    $result = mysqli_query($connection, $query);
    if($secondChoice == ""){
        $secondChoice = $min;
    }
    if($thirdChoice == ""){
        $thirdChoice = $max;
    }
    if ((int) $secondChoice > (int) $thirdChoice) {
        echo "You cannot set the starting year to be greater than the ending year.<br>";
    } else {
        if (mysqli_num_rows($result) > 0) {
            $offerFound = 0;
            while ($row = mysqli_fetch_assoc($result)) {
                if($row["year"] >= (int)$secondChoice && $row["year"] <= (int)$thirdChoice){
                   echo "coid: " . $row["coid"] . ", numstudents: " . $row["numstudent"] . ", term: " . $row["term"] . ", year: " . $row["year"] . "<br>";
                    $offerFound++;
                }
            }
        } else {
            echo "No course offering found.<br>";
        }
        if ($offerFound == 0) {
            echo "No course offering found.<br>";
        } else {
            echo "Number of course offering found: " . $offerFound . "<br>";
        }
        if ($secondChoice != '') {
            echo "Starting year you picked: " . htmlspecialchars($secondChoice) . "<br>";
        }
        if ($thirdChoice != "") {
            echo "Ending year you picked: " . htmlspecialchars($thirdChoice) . "<br>";
        }
    }
} else {
    echo "Invalid request method.";
}
?>