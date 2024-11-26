CREATE TABLE components (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT
);

INSERT INTO components (name, category, brand, price, description)
VALUES 
('RTX 4090', 'GPU', 'NVIDIA', ROUND(RAND() * (2000 - 1000) + 1000, 2), 'High-end graphics card for 4K gaming and AI workloads'),
('Intel Core i9-13900K', 'CPU', 'Intel', ROUND(RAND() * (600 - 300) + 300, 2), 'Flagship processor for gaming and heavy multitasking'),
('Corsair Vengeance LPX 16GB', 'RAM', 'Corsair', ROUND(RAND() * (100 - 50) + 50, 2), '16GB DDR4 RAM for gaming and productivity'),
('Samsung 970 EVO 1TB', 'Storage', 'Samsung', ROUND(RAND() * (150 - 100) + 100, 2), 'Fast NVMe SSD for gaming and software development'),
('Asus ROG Strix Z690-E', 'Motherboard', 'Asus', ROUND(RAND() * (400 - 200) + 200, 2), 'Premium motherboard for Intel 12th Gen processors'),
('NZXT H510', 'Case', 'NZXT', ROUND(RAND() * (100 - 50) + 50, 2), 'Mid-tower case with clean design and cable management'),
('Corsair RM850x', 'PSU', 'Corsair', ROUND(RAND() * (150 - 100) + 100, 2), '850W power supply with 80+ Gold certification'),
('Logitech G Pro X', 'Peripheral', 'Logitech', ROUND(RAND() * (130 - 70) + 70, 2), 'High-quality gaming headset with Blue VO!CE microphone technology'),
('Razer BlackWidow V3', 'Peripheral', 'Razer', ROUND(RAND() * (130 - 80) + 80, 2), 'Mechanical gaming keyboard with RGB lighting');
