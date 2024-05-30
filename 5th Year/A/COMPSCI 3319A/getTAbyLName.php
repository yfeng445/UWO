<?php
$order="";
?>
<form method="post" id="radioSelect">
    <input type="radio" name="order" value="ASC"/> Ascending<br>
    <input type="radio" name="order" value="DESC"/> Descending<br>
    <input type="submit" value="Update">
</form>

<?php
if(isset($_POST['order'])){
    $order = $_POST['order'];
}
if($order=="ASC"){ 
    include 'getTAbyLNameASC.php';
}
if($order=="DESC"){
    include 'getTAbyLNameDESC.php';
}
?>

