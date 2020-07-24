<?php
// Registration Function
function register($username, $email, $password, $conn) {
    $hash = password_hash($password, PASSWORD_DEFAULT);
    $stmt = $conn->prepare(
	    'SELECT * from CREDENTIALS WHERE Username=? or EMAIL=?');
    $stmt->execute([$username, $email]);
    if ($stmt ->rowCount() > 0) {
	return False;
    }
    $stmt = $conn->prepare(
    	  'insert into CREDENTIALS set Username=?, Email=?, Password=?,Member=0');
    $stmt->execute([$username, $email, $hash]);
    return True;
}
$dbservername = '35.227.29.52';
$dbusername = 'root';
$dbpassword = 'mypassword123';

try {
    $conn = new PDO(
    	   'mysql:host=$dbservername;dbname=USERS', $dbusername, $dbpassword);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo 'Connected successfully';
    if (isset($_POST['username']) && isset($_POST['email']
       	    && isset($_POST['password']){
	if(register($_POST['username'],$_POST['email'],$_POST['password'], $conn)){
            echo 'Registration Successful';
        } else {
            echo 'Registration Unsuccessful';
        }
    } else {
        echo 'credential error';
    }
} catch(PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}
?>
