CREATE DATABASE pc_component_picker;
USE pc_component_picker;

CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    comments TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
