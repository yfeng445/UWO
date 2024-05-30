<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['radioOption'])) {
        $selectedRadio = $_POST['radioOption'];
        echo "Radio Form submitted. Selected option: " . htmlspecialchars($selectedRadio);
    } else if (isset($_POST['A'])) {
        echo "A selected in Radio Form.";
    } else if (isset($_POST['B'])) {
        echo "B selected in Radio Form.";
    } else if (isset($_POST['C'])) {
        echo "C selected in Radio Form.";
    }
}
?>
