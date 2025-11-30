<?php
require_once 'file_util.php';  // the get_file_list function
require_once 'image_util.php'; // the process_image function

$image_dir = 'images';
$image_dir_path = getcwd() . DIRECTORY_SEPARATOR . $image_dir;

$action = filter_input(INPUT_POST, 'action');
if ($action == NULL) {
    $action = filter_input(INPUT_GET, 'action');
    if ($action == NULL) {
        $action = '';
    }
}

function upload($file_input_name, $image_dir_path) { 
    if (isset($_FILES[$file_input_name])) {
        $filename = $_FILES[$file_input_name]['name'];
        if (!empty($filename)) {
            $source = $_FILES[$file_input_name]['tmp_name'];
            $target = $image_dir_path . DIRECTORY_SEPARATOR . $filename;
            move_uploaded_file($source, $target);
            // create the '400' and '100' versions of the image
            process_image($image_dir_path, $filename);
        }
    }
}

switch ($action) {
    case 'upload':    
        // handle the uploaded files    
        upload('file1', $image_dir_path);
        upload('file2', $image_dir_path);
        upload('file3', $image_dir_path);
        break;
    case 'delete':
        $filename = filter_input(INPUT_GET, 'filename', 
                FILTER_SANITIZE_STRING);
        $target = $image_dir_path . DIRECTORY_SEPARATOR . $filename;
        if (file_exists($target)) {
            unlink($target);
        }
        break;
}

$files = get_file_list($image_dir_path);
include('uploadform.php');
?>