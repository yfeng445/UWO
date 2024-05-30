<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check if 'elementB' is set in the POST array
    if (isset($_POST['elementB'])) {
        $selectedOption = $_POST['elementB'];
        echo "Form B submitted. Selected option: " . htmlspecialchars($selectedOption);
    } else {
        echo "No option selected in Form B.";
    }
}
?>
