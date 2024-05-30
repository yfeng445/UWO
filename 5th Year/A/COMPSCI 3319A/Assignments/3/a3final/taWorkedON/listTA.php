<?php
include "connectdb.php";
$query = "SELECT * FROM ta";
$result = mysqli_query($connection, $query);
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        //<option value="">--Select an Option--</option>
        echo "<option value='" . $row["tauserid"] . "'>" . $row["tauserid"] . "</option>";
    }
}
mysqli_close($connection);
?>