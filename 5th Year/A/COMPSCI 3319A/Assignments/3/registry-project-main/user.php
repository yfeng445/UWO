<?php 
$page = "User Profile";
include 'includes/dash.header.php'; 
include 'includes/library.php';
$Cname = $_POST['username']??null;
$Cemail = $_POST['email']??null;
$Cpassword = $_POST['password']??null;
$usernamevalid = '/^[0-9a-zA-Z_.-]+$/';
$passwordvalid = '/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-\/]).{8,}$/';
$emailvalid = '/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/';
$pdo = connectDB();

function update($new,$place,$id){
	$pdo = connectDB();
    $sql = "UPDATE users set $place = '$new' WHERE id = $id";
    $stmt = $pdo->prepare($sql);
    $stmt->execute();
    if($stmt->rowCount()){
        alert("update successful!!");
    }else {
        alert("error");
    }
     
}
function same($str,$place){
    $pdo = connectDB();
    $sql = "SELECT * from users WHERE $place = '$str'";
    $stmt = $pdo ->prepare($sql);
    $stmt->execute();  
    $count = $stmt->rowCount();
    if($count >0){
        return true;
    }else {
        return false;
    }
    
}
function alert($var){
	echo '<script>alert("'.$var.'")</script>';
}

if(!empty($_POST['username'])){
    //update new user name
    if (preg_match($usernamevalid, $Cname)) {
        if(!same($Cname,'username')){
            update($Cname,'username',$id);
            $_SESSION['username']=$Cname;
        }else {
            alert("user name exits");
        }
    }else{
        alert('new user name is not valid or repeat');
    }
}
if(!empty($_POST['email'])){
    
    //update the datebase of new email 
    if (preg_match($emailvalid, $Cemail)) {
        if(!same($Cemail,'email')){
            update($Cemail,'email',$id);
        }else {
            alert("email already used");
        }
    }else{
        alert('new email name is not valid or repeat');
    }
    
}
if(!empty($_POST['password'])){
    //update the datebase of new password
    if (preg_match($passwordvalid, $Cpassword)) {
        update(password_hash($Cpassword,PASSWORD_DEFAULT),'password',$id);
    }else{
        alert('new password is not valid');
    }
}
if(isset($_POST['delete'])){
    try{
        $sql = "DELETE FROM `lists` WHERE `ownerId` = $id";
        $stmt = $pdo ->prepare($sql);
        $stmt->execute();
    }catch(Exception $e) {
        alert('delet list failed');
    }
    $sql = "DELETE FROM `users` WHERE `id` = $id";
    $stmt = $pdo ->prepare($sql);
    $stmt->execute();  
    $accountdel = $stmt->rowCount();
    if($accountdel >0){
         alert('delete account successful');
         session_destroy();
         header("Location: login.php");
         exit();
    }else {
        alert('delete account failed!');
    }
}

if(isset($_GET['logout'])){
    session_destroy();
    header("Location: login.php");
    exit();
}
try{
    $stmt = $pdo->prepare("SELECT * FROM users where id = ?");
    $stmt->execute([$id]);

    $result = $stmt->fetch();
}catch(Exception $e) {
    echo $e;
}
?>
    
	<main>
		<div class="profile">
			<!-- hidden form for self-processing and honeypot -->
			<form method="post">
				<input type="text" name="input">
			</form>
			<div>
				<h2><?php echo $result['username']?>'s Profile</h2>
				<img src = "https://avatars.dicebear.com/api/initials/<?php echo $result['username']?>.svg" width = "64px">
				<form method="post">
					<ul>
						<li>
							<label for="username">Username</label>
							<input type="text" name="username" value="<?php echo $result['username']; ?>">
							<button class="btn round" onclick="edit()" type="button"><i class="ri-edit-line"></i>Edit</button>
						</li>
						<li>
							<label for="username">Email</label>
							<input type="email" name="email" value="<?php echo $result['email'] ?>">
							<button class="btn round" onclick="edit()" type="button"><i class="ri-edit-line"></i>Edit</button>
						</li>
						<li>
							<label for="username">Password</label>
							<input type="password" name="password" placeholder="Please enter your new password">
							<button class="btn round" onclick="edit()" type="button"><i class="ri-edit-line"></i>Edit</button>
						</li>
					</ul>
					<div>
						<button class="btn round" type="submit">Submit all changes</button>
						<button class="btn round" onclick="deleteUser()" type="button">Delete Account</button>
					</div>
				</form>
			</div>
		</div>
	</main>

<?php include 'includes/dash.footer.php'; ?>