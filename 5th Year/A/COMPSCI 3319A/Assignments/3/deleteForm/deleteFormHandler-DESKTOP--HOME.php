<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $dropdownValue = isset($_POST['dropdown']) ? $_POST['dropdown'] : '';
    $manualInputValue = isset($_POST['manualInput']) ? $_POST['manualInput'] : '';
    $tauserid = "";
    // Process the data
    if (!empty($dropdownValue)) {
        //echo "Your selection is: " . htmlspecialchars($dropdownValue);
        $tauserid = $dropdownValue;
    } elseif (!empty($manualInputValue)) {
        //echo "Your input is: " . htmlspecialchars($manualInputValue);
        $tauserid = $manualInputValue;
    } else {
        echo "No selection made.";
    }
    echo "Operation:DELETE FROM ta WHERE tauserid='".$tauserid."';";
    
    include "delete.php";

} else {
    echo "Invalid request method.";
}
?>
