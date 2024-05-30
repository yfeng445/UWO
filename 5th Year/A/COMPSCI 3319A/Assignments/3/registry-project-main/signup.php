<?php 
include 'includes/library.php';
$username = $_POST['username']??null;
$password = $_POST['password'] ??null;
$email = $_POST['email'] ??null;

session_start();

if(!empty($_SESSION['id'])) {
	header("Location: user.php");
	exit();
}

if(!empty($_POST)){
	$pdo = connectDB();
	$query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?);";
	$stmt = $pdo->prepare($query);
	$stmt->execute([$username, password_hash($password, PASSWORD_DEFAULT), $email]);
	header("Location: login.php");
}
$page = "Signup";
include 'includes/header.php'; ?>
	<!-- Main content -->
	<main>
		<div class="account float">
			<div>
				<h2>Sign Up</h2>
				<form action="" method="post">
					<div>
						<label for="username">Username</label>
						<div>
							<input type="text" name="username" placeholder="Username">
							<i class="ri-user-3-fill"></i>
							<span class="border"></span>
						</div>
						<span class="err"></span>
						<label for="username">Email Address</label>
						<div>
							<input type="email" name="email" placeholder="Email Address">
							<i class="ri-mail-fill"></i>
							<span class="border"></span>
						</div>
						<span class="err"></span>
						<label for="password">Password</label>
						<div>
							<input type="password" name="password" placeholder="Password">
							<i class="ri-lock-fill"></i>
							<span class="border"></span>
						</div>
						<span class="err"></span>
						<label for="password">Repeat Password</label>
						<div>
							<input type="password" name="password" placeholder="Password">
							<i class="ri-lock-fill"></i>
							<span class="border"></span>
						</div>
						<span class="err"></span>
					</div>
					<a href="login.php">Already have account?</a>
					<div class="h-captcha" data-sitekey="b5a27318-cdb1-4c68-9ebf-98aca4fc6839" data-callback="onSubmit" data-size="invisible"></div>
					<button class="btn dark">Sign Up</button>
					<span class="err"></span>
				</form>
			</div>
		</div>
	</main>
<?php include 'includes/footer.php'; ?>
