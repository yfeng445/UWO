<?php
session_start(); // Start or resume the session
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Process each input, degreetype, and checkboxes
    $tauserid = isset($_POST['tauserid']) ? $_POST['tauserid'] : '';
    $firstname = isset($_POST['firstname']) ? $_POST['firstname'] : '';
    $lastname = isset($_POST['lastname']) ? $_POST['lastname'] : '';
    $studentnum = isset($_POST['studentnum']) ? $_POST['studentnum'] : '';
    $degreetype = isset($_POST['degreetype']) ? $_POST['degreetype'] : '';
    $Loves = isset($_POST['Loves']) ? $_POST['Loves'] : [];
    $Hates = isset($_POST['Hates']) ? $_POST['Hates'] : [];
    $image = isset($_POST['image']) ? $_POST['image'] : '';

    // INSERT ta
    echo "Operation:INSERT INTO ta VALUES ('$tauserid', '$firstname', '$lastname', '$studentnum', '$degreetype', '$image');<br>";
    $operation = "INSERT INTO ta VALUES ('$tauserid', '$firstname', '$lastname', '$studentnum', '$degreetype', '$image');";
    include "insert.php";
    include "selectall.php";

    // INSERT loves
    
    for ($i = 0; $i < count($Loves); $i++) {
        echo "INSERT INTO loves VALUES ('$tauserid', '$Loves[$i]');<br>";
        $operation = "INSERT INTO loves VALUES ('$tauserid', '$Loves[$i]');";
        include "insert.php";
    }
    // INSERT hates
    for ($i = 0; $i < count($Hates); $i++) {
        echo "INSERT INTO hates VALUES ('$tauserid', '$Loves[$i]');<br>";
        $operation= "INSERT INTO hates VALUES ('$tauserid', '$Hates[$i]');";
        include "insert.php";
    }

    include "selectall.php";
} else {
    echo "Invalid request method.";
}
?>