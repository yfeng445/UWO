<?php 
include "connectdb.php";
$query = "SELECT DISTINCT year FROM courseoffer ORDER BY year ASC";
$max; $min;
$result = mysqli_query($connection, $query);
$count = 0;
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        //<option value="">--Select an Option--</option>
        if($count == 0){$min = $row["year"];}
        echo "<option value='" . $row["year"] . "'>" . $row["year"] . "</option>";
        $max = $row["year"];
        $count++;
    }
    
}
mysqli_close($connection);
?>