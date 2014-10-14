<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();


// check for required fields
if (isset($_POST['email']) && isset($_POST['password'])) {

	$user_email = $_POST['email'];
	$user_password = $_POST['password'];
		
	
    mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! ".mysql_error());

	$db = mysql_select_db('smartroom') or die("Cannot connect to Database! ".mysql_error());

    // mysql inserting a new row
	$query = mysql_query("SELECT * FROM `profile` where email_id = '$user_email' and password = '$user_password'");

    if ($query) {
	
	$numberOfrows = mysql_num_rows($query);
	
	if($numberOfrows == 1){
	
		 $row = mysql_fetch_array($query);
		
        $response["email"] = $row['email_id'];
		$response["full_name"] = $row['full_name'];
		$response["img_url"] = $row['image_url'];
		$response["looged_using"] = $row['logged_using'];

       echo json_encode($response);
	}
	else{
	$response["email"] = "invalid";
	echo json_encode($response);
	}
     

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