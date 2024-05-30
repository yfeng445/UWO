<!DOCTYPE html>
<html>

<head>
    <title>CS3319 - ASMT3</title>
    <script src="formSubmitter.js"></script>
</head>

<body>
    <?php
    session_start();
    include 'connectdb.php'
        ?>
    <h1>CS3319 - Assignment 3</h1>
    <!-- Radio Button -->
    <?php include "./radioButtons/radioButton.php" ?>
    <!-- Selection -->
    <?php include "./selections/selection.php" ?>
    <!-- Insert Form -->
    <?php include "./insertForm/insertForm.php" ?>
   <!-- Delete Form -->
   <?php include "./deleteForm/deleteForm.php" ?>
    <!-- Update Form -->
    <?php include "./updateForm/updateForm.php" ?>
    <!-- Assign Offering -->
    <?php include "./assignOffering/assignOffering.php" ?>
    
</body>

</html>