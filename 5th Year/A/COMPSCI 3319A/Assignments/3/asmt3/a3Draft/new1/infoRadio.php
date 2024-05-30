<?php
include 'connectdb.php';
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['radioOption'])) {
        $selectedRadio = $_POST['radioOption'];
        $_SESSION['returnValue'] = $selectedRadio;
        $query = "SELECT * FROM ta ORDER BY ". $_SESSION['returnValue'].";";
        echo $query;
        $result = mysqli_query($connection, $query);
        if (!$result) {
            die("The query failed. on QUERY.PHP");
        }
        echo "<ul>";
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<li>";
            echo $row["tauserid"] . "  |  " . $row["firstname"] . "  |  " . $row["lastname"] . "  |  " . $row["studentnum"] . "  |  " . $row["degreetype"];
            echo "</li>";
        }
        echo "</ul>";
        mysqli_free_result($result);
    } else {
        echo "No radio option selected.";
    }
} else {
    echo "Invalid request method.";
}
?>