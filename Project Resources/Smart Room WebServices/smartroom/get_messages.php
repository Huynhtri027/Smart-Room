<?php

include "db.php";

$response["messages"] = array();
$message = array();

$email = $_POST['email'];

$query = mysql_query("SELECT * FROM `property_advert` WHERE email_id = '$email'");
if ($query) {

    $response["bool"] = false;
    while ($row = mysql_fetch_array($query)) {
        $advert_reference = $row["advert_reference"];

        $query2 = mysql_query("SELECT * FROM `advert_messages` WHERE advert_ref_id = '$advert_reference'");
        if ($query2) {
            $num = mysql_num_rows($query2);
            if ($num > 0) {

                $message["image"] = base64_encode($row['image']);

                while ($row2 = mysql_fetch_array($query2)) {

                    $response["bool"] = true;
                    $message["message"] = $row2["message"];
                    $message["messageID"] = $row2["messageID"];
                    $message["sender_id"] = $row2["sender_id"];
                    $message["advert_ref_id"] = $row2["advert_ref_id"];
                    $message["date"] = $row2["created_at"];

                    array_push($response["messages"], $message);
                }
            }
        }
    }

    echo json_encode($response);
} else {
    $response["bool"] = false;

    echo json_encode($response);
}
?>