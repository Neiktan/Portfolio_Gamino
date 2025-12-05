<?php
require_once('database.php');

$id = filter_input(INPUT_POST, 'category_id', FILTER_VALIDATE_INT);
if (!$id) { $error = 'Invalid category ID.'; include 'error.php'; exit; }

// Evita borrar categorías con productos asociados
$chk = $db->prepare('SELECT COUNT(*) FROM products WHERE categoryID = :id');
$chk->bindValue(':id', $id, PDO::PARAM_INT);
$chk->execute();
if ((int)$chk->fetchColumn() > 0) {
    $error = 'This category has products. Delete only the categories you created.';
    include 'error.php'; exit;
}

$del = $db->prepare('DELETE FROM categories WHERE categoryID = :id');
$del->bindValue(':id', $id, PDO::PARAM_INT);
$del->execute();
$del->closeCursor();

header('Location: category_list.php'); exit;