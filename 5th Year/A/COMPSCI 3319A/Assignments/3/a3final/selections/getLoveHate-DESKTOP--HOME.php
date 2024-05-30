<?php
$count = 0;
    $lquery = "SELECT DISTINCT lcoursenum FROM loves WHERE ltauserid ='".$tauserid . "';";
   $lresult = mysqli_query($connection, $lquery); 
   if (!$lresult) {
    echo $lquery;
     die("lquery failed. ");
    }
    echo "<ul>";   
    echo "<li> Loves: </li>";
    while ($row=mysqli_fetch_assoc($lresult)) {
        $count++;
          echo "<li>";
          echo $row["lcoursenum"];
          echo "</li>";
    }
    if ($count == 0) {
        echo "This t.a. has not picked courses that they love";
    }
    echo "</ul>";
    mysqli_free_result($lresult);
?>
<?php
$count = 0;
    $hquery = "SELECT DISTINCT hcoursenum FROM hates WHERE htauserid ='".$tauserid . "';"; 
    $hresult = mysqli_query($connection, $hquery); 
    if (!$hresult) {
        echo $hquery;
      die("hquery failed. ");
     }
     echo "<ul>";   
     echo "<li> Hates: </li>";
     while ($row=mysqli_fetch_assoc($hresult)) {
        $count++;
        echo "<li>";
        echo $row["hcoursenum"];
        echo "</li>";
  }
  if ($count == 0) {
      echo "This t.a. has not picked courses that they hate";
  }
     echo "</ul>";
     mysqli_free_result($hresult);
?>
