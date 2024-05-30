<?php
   if (isset($_POST['pickaTA'])) {
    echo "A ta is picked";
   include "connectdb.php";
   include "getPicture.php";
  }
?>