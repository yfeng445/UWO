<?php
include "connectdb.php";
if ($connection->query($operation) === FALSE) {
    echo "<br>Error on insert: " . $connection->error;
    echo ("<br>Error description: " . mysqli_error($connection));
}
else{
    $connection->query($operation);
}
$connection->close();
?>