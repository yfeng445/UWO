<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check if 'elementA' is set in the POST array
    if (isset($_POST['elementA'])) {
        $selectedOption = $_POST['elementA'];
        //echo "Form A submitted. Selected option: " . htmlspecialchars($selectedOption);
        include "connectdb.php";
        $query = "select * from ta where tauserid ='" . $selectedOption . "';";  //fill in with correct query
        $result = mysqli_query($connection, $query);
        if (!$result) {
            die("databases query on ta failed. ");
          }
          echo "<ul>";
          while ($row = mysqli_fetch_assoc($result)) {
            echo "<li>";
            echo "tauserid: " . $row["tauserid"] . ", " .
              "firstname: " . $row["firstname"] . ", " .
              "lastname: " . $row["lastname"] . ", " .
              "degree: " . $row["degreetype"] . ". ";
            echo "</li>";
            echo "<img src='" . $row["image"] . "'>";
            $tauserid = $row["tauserid"];
            echo "</li>";
          }
          include "getLoveHate.php";
          echo "</ul>";   //end the bulleted list
          mysqli_free_result($result);
    
    
    } else {
        echo "No option selected in Form A.";
    }
    mysqli_close($connection);
}
?>
