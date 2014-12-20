<?php

include "db.php";

$response = array();

if (isset($_POST['email']) || isset($_POST['rent_amount']) || isset($_POST['postcode']) || isset($_POST['address']) || isset($_POST['title']) || isset($_POST['description'])) {

    $email = $_POST['email'];

    $address = $_POST['address'];
    $description = $_POST['description'];
    $fullname = $_POST['fullname'];
    $telephone = $_POST['telephone'];
    $advertiser_title = $_POST['advertiser_title'];
    $title = $_POST['title'];
    $availability_date = $_POST['availability_date'];
    $bed_num = $_POST['bed_num'];
    $country = $_POST['country'];
    $deposit = $_POST['deposit'];
    $furnishing = $_POST['furnishing'];
    $postcode = $_POST['postcode'];
    $price_frequency = $_POST['price_frequency'];
    $property_type = $_POST['property_type'];
    $rent_amount = $_POST['rent_amount'];
    $seller_type = $_POST['seller_type'];
    $balcony = $_POST['balcony'];
    $bill_included = $_POST['bill_included'];
    $broadband = $_POST['broadband'];
    $disabled_access = $_POST['disabled_access'];
    $display_fullname = $_POST['display_fullname'];
    $display_telephone = $_POST['display_telephone'];
    $garage = $_POST['garage'];
    $garden = $_POST['garden'];
    $parking = $_POST['parking'];
    $reference = $_POST['reference'];

    $base = $_POST['image'];
    $buffer = base64_decode($base);

    $buffer = mysql_real_escape_string($buffer);

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO `property_advert`(`email_id`, `seller_type`, `bed_num`, `property_type`, `rent_amount`, `price_frequency`, `deposit_amount`, `postcode`, `address`, `country`, `furnishing`, `available_date`, `reference_required`, `image`, `parking_available`, `garden_available`, `garage_available`, `balcony_available`, `disabled_access_available`, `broadband_available`, `bill_included`, `advertiser_title`, `advert_title`, `advert_description`, `advertiser_fullname`, `display_name`, `advertiser_telephone`, `display_telephone`, `created_at`) VALUES('$email','$seller_type', '$bed_num', '$property_type', '$rent_amount', '$price_frequency', '$deposit', '$postcode', '$address', '$country', '$furnishing', '$availability_date', '$reference', '$buffer', '$parking', '$garden', '$garage', '$balcony', '$disabled_access', '$broadband', '$bill_included', '$advertiser_title', '$title', '$description', '$fullname', '$display_fullname', '$telephone', '$display_telephone', NOW())");


    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Advert Successfully created.";

        // echoing JSON response
        //echo json_encode($response);
        echo "1";
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo "0";
        //echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    //echo json_encode($response);
    echo "0";
}
?>