<?php

 $messageID = $_POST['messageID'];

  mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! ".mysql_error());
  
  $db = mysql_select_db('smartroom') or die("Cannot connect to Database! ".mysql_error());

  $query = mysql_query("UPDATE `advert_messages` SET notified = 'YES' WHERE messageID = '$messageID'");
  
  if($query){
  echo "1";
  }
  else {
  echo "0";
  }
  

?>