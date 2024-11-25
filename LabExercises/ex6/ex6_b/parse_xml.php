<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['xmlFile'])) {
    // Check if a file is uploaded
    if (is_uploaded_file($_FILES['xmlFile']['tmp_name'])) {
        // Load the uploaded XML file
        $xml = simplexml_load_file($_FILES['xmlFile']['tmp_name']) or die("Error: Cannot load XML file.");

        // Create an array to hold the data
        $components = [];

        // Iterate through each component and add it to the array
        foreach ($xml->component as $component) {
            $components[] = [
                'type' => (string) $component->type,
                'brand' => (string) $component->brand,
                'model' => (string) $component->model,
                'price' => (string) $component->price,
                'rating' => (string) $component->rating,
            ];
        }

        // Return JSON response
        echo json_encode($components);
        exit;
    } else {
        echo json_encode(['error' => 'File upload failed']);
        exit;
    }
}
?>
