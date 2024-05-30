<?php
    $whichMus = $_POST["pickamuseum"]; //get selected museum value from the form
    $query = "select * from workofart where whichmus =".$whichMus . ";";  //fill in with correct query
    echo "<br>" . $query . "<br>";  //this line is just to help you debug
   $result = mysqli_query($connection, $query); 
   if (!$result) {
     die("databases query on art pieces failed. ");
    }
    echo "<ul>";   //put the artwork in an unordered bulleted list
    while ($row=mysqli_fetch_assoc($result)) {
          echo "<li>";
          echo $row["artname"] . " BY " . $row["artist"];
         echo "</li>";
    }

    echo "</ul>";   //end the bulleted list
    mysqli_free_result($result);
?>
