<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>CS3319 - Assignment 3</title>
    <script src="autoSubmit.js"></script>
</head>

<body>
    <?php
    session_start();
    include 'connectdb.php'
    ?>
    <h1>CS3319 - Assignment 3</h1>


    <h2>List ta by:</h2>
    <form method="post">
        <input type="radio" name="listby" value="degreetype" /> Degree<br>
        <input type="radio" name="listby" value="lastname ASC" /> Last Name Ascending<br>
        <input type="radio" name="listby" value="lastname DESC" /> Last Name Descending<br>
        <input type="submit" value="Submit" onclick="history.go(-1);">
    </form>
    <?php
    $listby = "tauserid";
    if (isset($_POST['listby'])) {
        $listby = $_POST['listby'];
        $_SESSION['listby'] = $listby;
    }
    $session_listby = $_SESSION['listby'];
    $query = "SELECT * FROM ta ORDER BY $session_listby;";
    include 'query.php';
    ?>



    Select a ta to see their info:
    <form action="selectTA.php" method="post" id="selectTA">
        <select id="selectedTA">
            <option value="">Select Here</option>
            <?php
            include "listTA.php";
            ?>
        </select>
    </form>
 
 


    Select a degree to see the tas:
    <form action="" method="post">
        <select name="pickDegree" id="pickDegree">
            <option value="1">Select Here</option>
            <option value="PhD">PhD</option>
            <option value="Masters">Masters</option>
        </select>
    </form>
    <hr>
    <?php
    if (isset($_POST['pickDegree'])) {
        echo "degree: ". $_POST['pickDegree']." is selected";
        include "connectdb.php";
        include "selectDegree.php";
        session_destroy();
    }
    ?>
    <?php
    if (isset($_POST['listby']) || ($_SESSION && $_SESSION['listby'])) {
        echo '<script src="pickDegree.js"></script>';
    }
    ?>


</body>

</html>