<?php
require_once('database.php');

$name = trim((string)filter_input(INPUT_POST, 'category_name'));
if ($name === '') { $error = 'Category name is required.'; include 'error.php'; exit; }

try {
    $q = 'INSERT INTO categories (categoryName) VALUES (:n)';
    $s = $db->prepare($q);
    $s->bindValue(':n', $name);
    $s->execute();
    $s->closeCursor();
} catch (PDOException $e) {
    $error_message = $e->getMessage();
    include 'database_error.php'; exit;
}

header('Location: category_list.php'); exit;