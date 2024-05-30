<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $groupA = isset($_POST['groupA']) ? $_POST['groupA'] : [];
    $groupB = isset($_POST['groupB']) ? $_POST['groupB'] : [];

    echo "Group A Selections: " . implode(", ", $groupA) . "<br>";
    echo "Group B Selections: " . implode(", ", $groupB) . "<br>";
}
?>
