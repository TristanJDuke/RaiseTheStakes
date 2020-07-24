<?php
// $hash is what you would store in your database
$hash = password_hash($_POST['inputPassword'], PASSWORD_DEFAULT, ['cost' => 12]);

// $hash would be the $hash (above) stored in your database for this user
$checked = password_verify($_POST['inputPassword'], $hash);
if ($checked) {
    echo 'password correct';
} else {
    echo 'wrong credentials';
}
?>
