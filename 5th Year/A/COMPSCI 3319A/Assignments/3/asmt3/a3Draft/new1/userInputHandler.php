<?php
session_start(); // Start or resume the session

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['userInput'])) {
        $userInput = $_POST['userInput'];
        $_SESSION['userInputValue'] = $userInput; // Optionally store the value in a session variable
        echo "User input received: " . htmlspecialchars($userInput);
    } else {
        echo "No user input received.";
    }
} else {
    echo "Invalid request method.";
}
?>
