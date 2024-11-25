<?php
// Database connection
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "pc_component_picker";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Handle form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $email = $_POST['email'];
    $rating = $_POST['rating'];
    $comments = $_POST['comments'];

    $stmt = $conn->prepare("INSERT INTO feedback (username, email, rating, comments) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssis", $username, $email, $rating, $comments);

    if ($stmt->execute()) {
        echo "<div class='success-message'>Thank you for your feedback!</div>";
    } else {
        echo "<div class='error-message'>Error: " . $stmt->error . "</div>";
    }

    $stmt->close();
}

$conn->close();
?>
