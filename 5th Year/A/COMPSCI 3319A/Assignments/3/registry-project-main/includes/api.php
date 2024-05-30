<?php
require 'library.php';
$pdo = connectDB();
if(isset($_GET['username'])){
    $username = $_GET['username'];
	if($username == "") {
		session_start();
		$id = $_SESSION['id'] ?? null;
		if(!empty($id)) {
			$stmt = $pdo->prepare('SELECT username FROM users where id = ?');
			$stmt->execute([$id]);
			$results = $stmt->fetch();
			echo $results['username'];
		}
	} else {
		//query for record matching user name 
		$stmt = $pdo->prepare("SELECT username FROM `users` WHERE username = ?");
		$stmt->execute([$username]);
		
		//remember fetch returns false when there were no records
		if ($stmt->fetch()) {
			echo 'true'; //username found
		} else {
			echo 'false'; //user name not exists
		}
		exit();
	}
}
if(isset($_GET['email'])){
    $stmt = $pdo->prepare("SELECT email FROM `users` WHERE email = ?");
		$stmt->execute([$email]);
		
		//remember fetch returns false when there were no records
		if ($stmt->fetch()) {
			echo 'true'; //email found
		} else {
			echo 'false'; //email not exists
		}
		exit();
}
else if(!empty($_GET['popular'])){
    $popular = $_GET['popular'];
    //query for record matching top lists
    $stmt = $pdo->prepare("SELECT * FROM `lists` WHERE public = 1 AND disable = 0 ORDER BY `lists`.`views` DESC LIMIT $popular");
	$stmt->execute();
    $result = $stmt->fetchAll();
    echo json_encode($result);
    exit();
}
else{
}
    

?>
