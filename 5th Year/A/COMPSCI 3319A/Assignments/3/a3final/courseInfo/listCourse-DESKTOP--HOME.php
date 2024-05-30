<?php
include "connectdb.php";
$query = "SELECT * FROM course";
$result = mysqli_query($connection, $query);
echo $query;
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        //<option value="">--Select an Option--</option>
        echo "<option value='" . $row["coursenum"] . "'>" . $row["coursenum"] . "</option>";
    }
}
?>