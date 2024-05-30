<?php
    $query = "select tauserid, firstname, lastname, studentnum, degreetype from ta;";
    $result = mysqli_query($connection, $query); 
    if (!$result) {
        die("The query failed. on QUERY.PHP");
    }
    echo "<ul>";  
    while ($row=mysqli_fetch_assoc($result)) {
        echo "<li>";
        echo $row["tauserid"] . "  |  " . $row["firstname"] . "  |  " . $row["lastname"] . "  |  " . $row["studentnum"] . "  |  " . $row["degreetype"];
        echo "</li>";
    }
    echo "</ul>";   
    mysqli_free_result($result);
    echo "</ol>";
?>