<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check if 'elementA' is set in the POST array
    if (isset($_POST['elementA'])) {
        $selectedOption = $_POST['elementA'];
        echo "Form A submitted. Selected option: " . htmlspecialchars($selectedOption);
    } else {
        echo "No option selected in Form A.";
    }
}
?>
