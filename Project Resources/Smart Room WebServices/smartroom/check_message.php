<?php

$response = array();

$email = $_POST['email'];

mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! " . mysql_error());

$db = mysql_select_db('smartroom') or die("Cannot connect to Database! " . mysql_error());

$query = mysql_query("SELECT * FROM `property_advert` WHERE email_id = '$email'");
if ($query) {
    while ($row = mysql_fetch_array($query)) {
        $advert_reference = $row["advert_reference"];
        $response["bool"] = false;

        $query2 = mysql_query("SELECT * FROM `advert_messages` WHERE advert_ref_id = '$advert_reference' and notified = 'NO'");
        if ($query2) {
            $num = mysql_num_rows($query2);
            if ($num > 0) {
                $notice = mysql_fetch_array($query2);

                $response["bool"] = true;
                $response["message"] = $notice["message"];
                $response["messageID"] = $notice["messageID"];
                $response["sender_id"] = $notice["sender_id"];
                $response["advert_ref_id"] = $notice["advert_ref_id"];

                echo json_encode($response);
            }
        }
    }
    $response["bool"] = false;


    echo json_encode($response);
} else {
    $response["bool"] = false;

    echo json_encode($response);
}
?>