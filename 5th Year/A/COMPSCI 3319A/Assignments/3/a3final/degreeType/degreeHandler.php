<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $selectedValue = $_POST['dropdownSelection'];
    echo "The degree you picked: " . htmlspecialchars($selectedValue) . "<br>";
    include "../connectdb.php";
    $query = "SELECT * FROM ta WHERE degreetype = '$selectedValue'";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        echo "TA(s) who in this degree: <br>";
        while ($row = mysqli_fetch_assoc($result)) {
            echo "tauserid: " . $row["tauserid"] . ", firstname: " . $row["firstname"] . ", lastname: " . $row["lastname"] ."<br>";
        }
    }
}
mysqli_close($connection);
?>
