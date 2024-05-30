<?php
include "connectdb.php";
$query = "SELECT * FROM courseoffer";
$result = mysqli_query($connection, $query);
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        echo "<option value='" . $row["coid"] . "'>" . $row["coid"] . "</option>";
    }
}
mysqli_close($connection);
?>