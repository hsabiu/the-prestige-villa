<?php
  
  if(isset($_POST['search_date']) && isset($_POST['search_room'])){
    $search_date = $_POST['search_date'];
    $search_room = $_POST['search_room'];
 
  if(!empty($search_date) && !empty($search_room)){
  
    $mysql_host = 'localhost';
	$mysql_user = 'root';
	$mysql_pass = '';
	$mysql_db = 'prestige_villa_db';
		
	if(!@mysql_connect($mysql_host, $mysql_user, $mysql_pass) || !@mysql_select_db($mysql_db)){
	  echo "Could not connect.";
	}
	else{
	  $query_run = mysql_query("SELECT `Room No` FROM  `rooms` WHERE  `Status` =  'Available' && `Room Type` = '$search_room' ORDER BY `Room No` ASC LIMIT 1");
	  if(mysql_num_rows($query_run) > 0){
	    mysql_num_rows($query_run);
	    echo "Available";
	  }
	  else{
	    echo "Unavailable";
	  }
	}
 }
 elseif(empty($search_date)){
   $flag1 = true;
 }
 elseif(!empty($search_room)){
   $flag2= true;
 }
 else{
   $flag3 = true;
 }
}

?>