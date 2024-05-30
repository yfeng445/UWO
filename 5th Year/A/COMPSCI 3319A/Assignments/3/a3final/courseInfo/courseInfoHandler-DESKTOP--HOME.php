<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $initialChoice = isset($_POST['initialChoice']) ? $_POST['initialChoice'] : '';
    $secondChoice = isset($_POST['secondChoice']) ? $_POST['secondChoice'] : '';
    $thirdChoice = isset($_POST['thirdChoice']) ? $_POST['thirdChoice'] : '';

    // Process the data
    echo "The course you picked: " . htmlspecialchars($initialChoice) . "<br>";
    include "connectdb.php";
    $query = "SELECT * FROM courseoffer WHERE whichcourse = '$initialChoice'";
    $result = mysqli_query($connection, $query);  
    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            echo "coid: " . $row["coid"] . ", numstudents: " . $row["numstudent"] . ", term: " . $row["term"] . ", year: " . $row["year"] . "<br>";
        }
    }
    else{
        echo "No course offering found.<br>";
    }

    if((int)$secondChoice > (int)$thirdChoice){
        echo"You cannot set the starting year to be greater than the ending year.";
    }

    if($secondChoice != '') {
        echo "Starting year: " . htmlspecialchars($secondChoice) . "<br>";
    }
    if($thirdChoice != "") {
        echo "Ending year: " . htmlspecialchars($thirdChoice) . "<br>";
    }
} else {
    echo "Invalid request method.";
}
?>
