<?php
include "connectdb.php";
$query = "SELECT DISTINCT tauserid FROM courseoffer JOIN hasworkedon ON courseoffer.coid=hasworkedon.coid"; 
$result = mysqli_query($connection, $query);
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        if($row["tauserid"] == $tauserid){
            echo "You cannot delete this TA because they have worked on a course.";
            //$connection->close();
        }
    }
}

$operation = "DELETE FROM ta WHERE tauserid='" . $tauserid . "'";
if ($connection->query($operation) === FALSE) {
    echo "<br>Error on delete: " . $connection->error;
    echo ("<br>Error description: " . mysqli_error($connection));
}
else{
    $connection->query($operation);
}
$connection->close();



?>