<?php
    $query = "select * from ta ORDER BY $listby;";
    //echo "<br>" . $query . "<br>";
    $result = mysqli_query($connection, $query); 
    if (!$result) {
        die("");
    }
    echo "<ul>";  
    while ($row=mysqli_fetch_assoc($result)) {
        echo "<li>";
        echo $row["tauserid"] . " | " . $row["firstname"] . " | " . $row["lastname"] . " | " . $row["studentnum"] . " | " . $row["degreetype"];
        echo "</li>";
    }

    echo "</ul>";   
    mysqli_free_result($result);
    echo "</ol>";
?>

