<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check if 'elementA' is set in the POST array
    if (isset($_POST['elementB'])) {
        $selectedOption = $_POST['elementB'];
        //echo "Form A submitted. Selected option: " . htmlspecialchars($selectedOption);
        include "connectdb.php";
        $query = "select * from ta where degreeType ='" . $selectedOption . "';";  //fill in with correct query
        $result = mysqli_query($connection, $query);
        if (!$result) {
            die("databases query on ta failed. ");
          }
          echo "<ul>";
          while ($row = mysqli_fetch_assoc($result)) {
            echo "<li>";
            echo $row["tauserid"];
            echo "</li>";
          }
         echo "</ul>";   //end the bulleted list
          mysqli_free_result($result);
    } else {
        echo "No option selected in Form A.";
    }
}
?>
