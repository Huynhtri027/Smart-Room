<?php

include "db.php";

$messageID = $_POST['messageID'];

$query = mysql_query("UPDATE `advert_messages` SET notified = 'YES' WHERE messageID = '$messageID'");

if ($query) {
    echo "1";
} else {
    echo "0";
}
?>