<?php
include "connectdb.php";
$tauserid = $row["tauserid"];
$coursenum = $row["coursenum"];
$lquery = "SELECT * FROM loves WHERE ltauserid = '$tauserid' AND lcoursenum = '$coursenum'";
$hquery = "SELECT * FROM hates WHERE htauserid = '$tauserid' AND hcoursenum = '$coursenum'";
$lresult = mysqli_query($connection, $lquery);
$hresult = mysqli_query($connection, $hquery);
if (mysqli_num_rows($lresult) > 0) {
    echo " : ) ";
}
else if (mysqli_num_rows($hresult) > 0) {
    echo " : ( ";
}
else {
    echo " : |";
}
mysqli_close($connection);
?>