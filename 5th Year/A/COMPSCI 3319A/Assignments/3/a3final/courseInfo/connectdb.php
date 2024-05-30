<?php
    $dbhost = "localhost";
    $dbuser = "root";
    $dbpass = "Ff1404545652";
    $dbname = "assign2db";
    $connection = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname);
    if (mysqli_connect_errno()) {
        die("database connection failed :" .
            mysqli_connect_error() .
            "(" . mysqli_connect_errno() . ")"
        );
    }
    
?> 