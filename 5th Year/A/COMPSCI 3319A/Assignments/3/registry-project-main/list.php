<?php 
$page = "My Lists";
include 'includes/dash.header.php';
include 'includes/library.php';
$pdo = connectDB();

$listId = $_GET['id'] ?? null;
$editId = $_GET['edit'] ?? null;

function alert($var){
	echo '<script>alert("'.$var.'")</script>';
}

function randomPass() {
	$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	$charactersLength = strlen($characters);
	$randomString = '';
	for ($i = 0; $i < 6; $i++) {
		$randomString .= $characters[rand(0, $charactersLength - 1)];
	}
	return $randomString;
}

if(!empty($_POST['newlist'])) {
	$true = 1; $false = 0; $content = ""; $date = date("Y-m-d H:i:s");
	$stmt = $pdo->prepare("INSERT INTO lists (listname,ownerId,public,disable,views,expiryDate,content) VALUES (:listname,:ownerId,:public,:disable,:views,:expiryDate,:content)");
	$stmt->bindParam(':listname',$_POST['newlist']);
	$stmt->bindParam(':ownerId',$_SESSION['id']);
	$stmt->bindParam(':public', $true);
	$stmt->bindParam(':disable', $false);	
	$stmt->bindParam(':views', $false);
	$stmt->bindParam(':expiryDate', $date);
	$stmt->bindParam(':content', $content);
	$stmt->execute();
	$listId = $pdo->lastInsertId();
	header("Location: /list.php?edit=$listId");
}

if(!empty($editId))
{
	$stmt = $pdo->prepare("SELECT * FROM lists WHERE id = ? AND ownerId = ?");
	$stmt->execute([$editId,$id]);
	$list = $stmt->fetch();
	if($stmt->rowCount() == 0){
		alert("You don't have permission to edit this list");
		echo("<script>location.href='list.php'</script>");
	}
	

}

/*
function update($new,$place,$id){
	$pdo = connectDB();
    $sql = "UPDATE lists set $place = '$new' WHERE id = $id";
    $stmt = $pdo->prepare($sql);
    $stmt->execute();
    if($stmt->rowCount()){
        alert("update successful!!");
    }else {
        alert("error");
    }
}

if(!empty($_POST['listname'])){
	update(RemoveXSS($_POST['listname']),'listname',$listId);
}else{
	alert('new user name is not valid or repeat');
}
	
if(isset($_POST['public'])){
    update(1,'public',$listId);
}else{
	update(0,'public',$listId);
}

if(isset($_POST['disable'])){
    update(1,'disable',$listId);
}else{
	update(0,'disable',$listId);
}

if($_FILES['file']['error'] == 0) {
		// Initialize counter if not set
		if(!isset($_SESSION['file'])) {
			$_SESSION['file'] = 1;
		}
		$unique_id = $_SESSION['file'];
		$path = WEBROOT."www_data/";
		$fileroot = "text";
		if(is_uploaded_file($_FILES['file']['tmp_name'])){
			$results = checkErrors('file', 1024000);
			if(strlen($results) > 0){
				$message = $results;
			} else {
				// Move file to www_data
				$newname = createFilename('file', $path, $fileroot, $unique_id);
				if(!move_uploaded_file($_FILES['file']['tmp_name'], $newname)){
					$message = "Failed to move uploaded file.";
				} else {
					$message = "File uploaded successfully.";
					update(file_get_contents($newname),'featureImage',$listId);
				}
			}
		} else{
			$results = checkErrors('file', 1024000);
			$message = $results;
		}
	}

if(isset($_POST['content'])){
    update(RemoveXSS($_POST['content']),'content',$listId);
}
*/
if(!empty($_GET['delete'])){
	$deleteId = $_GET['delete'];
	$sql = "SELECT ownerId FROM `lists` WHERE `id` = $deleteId";
	$stmt = $pdo ->prepare($sql);
	$stmt->execute();
	$ownId = $stmt->fetch()['ownerId'];
	if($ownId == $_SESSION['id']){

		$sql = "DELETE FROM `lists` WHERE `id` = $deleteId";
		$stmt = $pdo ->prepare($sql);
		$stmt->execute();
		$list = $stmt->rowCount();
		if($list>0){
			alert("delete list successful");
		}
	}else{
		alert("User does not have delete permission");	
	}
	echo("<script>location.href='list.php'</script>");
}
if(empty($listId)): 

	$pdo = connectDB();
	$stmt = $pdo->prepare("SELECT * FROM lists where ownerId = ?");
	$stmt->execute([$id]);
	$rows = $stmt->rowCount();
	$result = $stmt->fetchAll()
	?>

	<main>
		<div class="list">
			<!-- hidden form for self-processing and honeypot -->
			<form method="get">
				<input type="text" name="input">
			</form>
			<div>
				<h2>My Lists</h2>
				<div>
					<ul>
						<li></li>
						<li><a href="?create=1"><button class="btn round" name="create">Create</button></a></li>
						<li><button class="btn round" name="delete">Delete</button></li>
					</ul>
				</div>
				<?php if(!empty($_GET['create'])): ?>
					<form method="post">
						<input type="text" name="newlist" placeholder="List Name">
						<button class="btn dark round" type="submit">Create</button>
					</form>
				<?php endif ?>
				<ul>
					<?php if($rows> 0){
					foreach ($result as $row):?>
					<a href="list.php?id=<?php echo $row['id']?>">
						<li>
							<input type="checkbox" name="<?php echo $row['id']?>">
							<!-- https://codepen.io/felquis/pen/JjMEEG -->
							<p data-content="<?php echo $row['public'] == 1 ? "Public" : "Private" ?>"><?php echo $row['listname']?></p>
							<button class="btn round" name="edit">Edit</button>
							<button class="btn round" name="delete">Delete</button>
						</li>
					</a>
					<?php endforeach;}
					else{?>
					<a href="list.php?create=1">	
						<li>
							<span>You have no registry on the list, but you can create it now!</span>
						</li>
					</a>
					<?php };?> 
				</ul>
			</div>
		</div>
	</main>
<?php else: 
	$pdo = connectDB();
	$stmt = $pdo->prepare("SELECT * FROM lists where id = ?");
	$stmt->execute([$listId]);
	$result = $stmt->fetch();
	if($result) {
		if($result['ownerId'] != $id) {
			echo 'You are not the owner of this list';
			echo '<script>window.location.href = "user.php"</script>';
		}
		else {
			$stmt = $pdo->prepare("SELECT username FROM users where id = ?");
			$stmt->execute([$result['ownerId']]);
			$owner = $stmt->fetch()['username'];
		}
	} else {
		alert("list not found");
		echo '<script>window.location.href = "user.php"</script>';
	}
?>
	<main>
		<div class="list">
			<!-- hidden form for self-processing and honeypot -->
			<form method="get">
				<input type="text" name="input">
			</form>
			<div>
				<h2><?php echo $result['listname'] ?></h2>
				<p><i class="ri-user-fill">Owner: </i><span><?php echo $owner ?></span> <i class="ri-git-repository-private-fill"></i>Visible: <?php echo $result['public'] == 1 ? "Public" : "Private" ?></span></p>
				<div>
					<ul>
						<li></li>
						<li><button class="btn round" name="edit">Edit</button></li>
						<li><button class="btn round" name="delete">Delete</button></li>
					</ul>
				</div>
				<ul contenteditable>
					<?php echo $result['content'] ?>
				</ul>
				<div class="edit">
					<button class="btn round" onclick="newItem()">New item</button>
					<button class="btn round" onclick="update()">Submit</button>
				</div>
			</div>
		</div>
	</main>
<?php endif; ?>
<?php include 'includes/dash.footer.php'; ?>