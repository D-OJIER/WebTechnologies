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

// Retrieve data from database
$sql = "SELECT id, username, email, rating, comments, submitted_at FROM feedback ORDER BY submitted_at DESC";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Feedback - PC Component Picker</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #0D0D0D;
            color: #D3F8E2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .table-container {
            background-color: #1A1A1A;
            border: 2px solid #33FF66;
            padding: 20px;
            width: 90%;
            max-width: 1000px;
            border-radius: 10px;
            box-shadow: 0 0 20px #33FF66;
            transition: all 0.3s ease;
            overflow-x: auto;
        }

        .table-container:hover {
            transform: scale(1.03);
            box-shadow: 0 0 30px #66FF99;
        }

        h1 {
            color: #33FF66;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #33FF66;
            padding: 10px;
            text-align: left;
            color: #D3F8E2;
        }

        th {
            background-color: #0D0D0D;
            color: #33FF66;
        }

        tr:nth-child(even) {
            background-color: #1A1A1A;
        }

        tr:hover {
            background-color: #333333;
            cursor: pointer;
        }

        .no-data {
            color: #FF3333;
            text-align: center;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="table-container">
        <h1>Feedback Overview</h1>
        <?php
        if ($result->num_rows > 0) {
            echo "<table>";
            echo "<tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Rating</th>
                    <th>Comments</th>
                    <th>Submitted At</th>
                  </tr>";
            
            // Output data for each row
            while($row = $result->fetch_assoc()) {
                echo "<tr>
                        <td>" . $row['id'] . "</td>
                        <td>" . htmlspecialchars($row['username']) . "</td>
                        <td>" . htmlspecialchars($row['email']) . "</td>
                        <td>" . $row['rating'] . "</td>
                        <td>" . htmlspecialchars($row['comments']) . "</td>
                        <td>" . $row['submitted_at'] . "</td>
                      </tr>";
            }

            echo "</table>";
        } else {
            echo "<p class='no-data'>No feedback data available.</p>";
        }
        
        $conn->close();
        ?>
    </div>
</body>
</html>
