<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $firstChoice = isset($_POST['firstChoice']) ? $_POST['firstChoice'] : '';
    $secondChoice = isset($_POST['secondChoice']) ? $_POST['secondChoice'] : '';
    $number = isset($_POST['number']) ? $_POST['number'] : 0;
    echo "Operation:INSERT INTO hasworkedon VALUES ('$firstChoice', '$secondChoice', '$number');<br>";
    $operation = "INSERT INTO hasworkedon VALUES ('$firstChoice', '$secondChoice', '$number');";
    include "connectdb.php";
    if ($connection->query($operation) === FALSE) {
        echo "<br>Error on insert: " . $connection->error;
        echo ("<br>Error description: " . mysqli_error($connection));
    } else {
        $connection->query($operation);
    }
    $connection->close();
} else {
    echo "Invalid request method.";
}
?>