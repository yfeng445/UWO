<?php
   include 'connecttodb.php';
   $art = $_POST["work"];
   $artist = $_POST["artist"];
   $theyear = $_POST["theyear"];
   $whichmus = $_POST["whichmus"];
   $query= 'INSERT INTO workofart (artname, artist, year, whichmus)  VALUES ("' . $art .'","' . $artist . '",' . $theyear . ',' . $whichmus . ');';
   echo $query;
   if (!mysqli_query($connection,$query)) {
      die ("Error while trying to add new art". mysqli_error($connection));
   } else {
     header('Location: museum.php');  //send back to museum page once it is done
     exit;
  }
?>
