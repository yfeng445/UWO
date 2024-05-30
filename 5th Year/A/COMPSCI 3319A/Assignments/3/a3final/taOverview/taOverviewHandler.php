<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $selectedValue = $_POST['dropdownSelection'];
    echo "The order you picked: " . htmlspecialchars($selectedValue) . "<br>";
    include "../connectdb.php";
    $query = "SELECT * FROM ta ORDER BY $selectedValue";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        echo "TA overview: <br>";
        while ($row = mysqli_fetch_assoc($result)) {
            echo "tauserid: " . $row["tauserid"] .
                ", firstname: " . $row["firstname"] .
                ", lastname: " . $row["lastname"] .
                ", degreetype: " . $row["degreetype"] .
                "<br>";
        }
    }
    mysqli_close($connection);
}
?>