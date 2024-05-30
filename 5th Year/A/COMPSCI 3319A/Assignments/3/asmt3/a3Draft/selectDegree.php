<?php
  $degree = $_POST['pickDegree'];
  $query = "select tauserid from ta where degree='" . $degree ."';";
  $result = mysqli_query($connection,$query);
  if (!$result) {
    die("databases query failed.");
  }
  while ($row = mysqli_fetch_assoc($result)) {
    echo $row["tauserid"];
  }
   mysqli_free_result($result);
?>