<?php
include "connectdb.php";
$query = "SELECT * FROM ta;";
$result = mysqli_query($connection, $query);
if (mysqli_num_rows($result) > 0) {
    echo "<br>TA Table:<br>";
    while ($row = mysqli_fetch_assoc($result)) {
        echo $row["tauserid"] . ", " . $row["firstname"] . ", " . $row["lastname"] . ", " . $row["studentnum"] . ", " . $row["degreetype"] . ", " . $row["image"] . "<br>";
    }
}
$lquery = "SELECT * FROM loves;";
$lresult = mysqli_query($connection, $lquery);
if (mysqli_num_rows($lresult) > 0) {
    echo "<br>loves Table:<br>";
    while ($row = mysqli_fetch_assoc($lresult)) {
        echo $row["ltauserid"] . ", " . $row["lcoursenum"] . "<br>";
    }
}
$hquery = "SELECT * FROM hates;";
$hresult = mysqli_query($connection, $hquery);
if (mysqli_num_rows($result) > 0) {
    echo "<br>hates Table:<br>";
    while ($row = mysqli_fetch_assoc($hresult)) {
        echo $row["htauserid"] . ", " . $row["hcoursenum"] . "<br>";
    }
}
?>
