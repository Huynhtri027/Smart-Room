<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();


// check for required fields
if (isset($_POST['fullname']) || isset($_POST['email']) || isset($_POST['password']) || isset($_POST['tenant']) || isset($_POST['landlord'])
 || isset($_POST['image_url']) || isset($_POST['loggedUsing'])) {
    
	$user_fullName = $_POST['fullname'];
	$user_email = $_POST['email'];
	$user_password = $_POST['password'];
	$tenant = $_POST['tenant'];
	$landlord = $_POST['landlord'];
	$image_url = $_POST['image_url'];
	$loggedUsing = $_POST['loggedUsing'];

    mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! ".mysql_error());

	$db = mysql_select_db('smartroom') or die("Cannot connect to Database! ".mysql_error());

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO `profile`(full_name, email_id, password, tenant, landlord, image_url, logged_using) VALUES('$user_fullName','$user_email', '$user_password', '$tenant', '$landlord', '$image_url', '$loggedUsing')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";	
	
    // echoing JSON response
    echo json_encode($response);
}
?>