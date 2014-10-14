<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

    
	$senderID = $_POST['senderID'];
	$msg = $_POST['msg'];
	$advertRefNum = $_POST['advertRefNum'];

    mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! ".mysql_error());

	$db = mysql_select_db('smartroom') or die("Cannot connect to Database! ".mysql_error());

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO `advert_messages`(sender_id, advert_ref_id, message, status, notified) VALUES('$senderID','$advertRefNum', '$msg'
	, 'UNREAD', 'NO')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        //$response["message"] = "Message sent successfully.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
?>