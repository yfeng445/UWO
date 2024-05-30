<?php
if (isset($_POST['selection'])) {
    echo "The user selection is: Button " . $_POST['selection'];
} else {
    echo "No selection made.";
}
?>
