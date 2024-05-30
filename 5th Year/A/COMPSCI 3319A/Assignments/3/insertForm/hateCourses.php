<?php
include "connectdb.php";
$query = "SELECT coursenum FROM course;";
$result = mysqli_query($connection, $query);
if (!$result) {
    die("The query failed. on QUERY.PHP");
}
echo "Hates:<ul>";
while ($row = mysqli_fetch_assoc($result)) {
    echo "<li>";
    echo "<input type='checkbox' id='" . $row["coursenum"] . "' name='Hates[]' value='" . $row["coursenum"] . "'>";
    echo "<label for='" . $row["coursenum"] . "'>" . $row["coursenum"] . "</label>";
    echo "</li>";
}
echo "</ul>";
mysqli_free_result($result);
echo "</ol>";
mysqli_close($connection);
?>