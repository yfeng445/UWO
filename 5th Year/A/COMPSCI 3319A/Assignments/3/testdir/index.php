<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>testing page</title>
</head>
<body>
    <?php
    include 'connectdb.php'
    ?>
    <?php
    $query = "SELECT * FROM ta";
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
    ?>
</body>
</html>