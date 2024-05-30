<?php
include "connectdb.php";
$query = "SELECT coursenum FROM course;";
    $result = mysqli_query($connection, $query); 
    if (!$result) {
        die("The query failed. on QUERY.PHP");
    }
    echo "Loves:<ul>";  
    while ($row=mysqli_fetch_assoc($result)) {
        echo "<li>";
        echo "<input type='checkbox' id='".$row["coursenum"]."' name='Loves[]' value='".$row["coursenum"]."'>";
        echo "<label for='".$row["coursenum"]."'>".$row["coursenum"]."</label>";
        echo "</li>";
    }
    echo "</ul>";   
    mysqli_free_result($result);
    echo "</ol>";













?>