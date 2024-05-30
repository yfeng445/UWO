<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $selectedValue = $_POST['dropdownSelection'];
    echo "The ta you picked: " . htmlspecialchars($selectedValue) . "<br>";
    include "connectdb.php";
    $query = "SELECT * FROM ta WHERE tauserid = '$selectedValue'";
    $result = mysqli_query($connection, $query);
    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<li>";
            echo "tauserid: " . $row["tauserid"] . ", " .
                "firstname: " . $row["firstname"] . ", " .
                "lastname: " . $row["lastname"] . ", " .
                "degree: " . $row["degreetype"] . ". ";
            echo "</li>";
            echo "<img src='" . $row["image"] . "'>";
            $tauserid = $row["tauserid"];
            echo "</li>";
        }
    }
    include "getLoveHate.php";
    echo "</ul>";   //end the bulleted list
    mysqli_free_result($result);
    mysqli_close($connection);
}
?>