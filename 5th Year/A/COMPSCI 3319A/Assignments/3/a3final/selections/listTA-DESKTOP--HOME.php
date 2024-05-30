<?php
  $query = "select tauserid from ta;";
  $result = mysqli_query($connection,$query);
  echo $query;
  if (!$result) {
    die("databases query failed.");
  }
  while ($row = mysqli_fetch_assoc($result)) {
    echo "<option value='" . $row["tauserid"] ."'>" . $row["tauserid"] . "</option>";
    }
    
   mysqli_free_result($result);
?>
