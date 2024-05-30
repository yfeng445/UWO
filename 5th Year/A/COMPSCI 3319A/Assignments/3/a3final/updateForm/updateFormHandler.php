<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $dropdownValue = isset($_POST['dropdown']) ? $_POST['dropdown'] : '';
    $manualInputValue = isset($_POST['manualInput']) ? $_POST['manualInput'] : '';

    // Process the data
    echo "The ta you picked: " . htmlspecialchars($dropdownValue) . "<br>";
    $tauserid = $dropdownValue;
    echo "The new image url is: " . htmlspecialchars($manualInputValue);
    $image_url = $manualInputValue;
    include "update.php";
} else {
    echo "Invalid request method.";
}
?>
