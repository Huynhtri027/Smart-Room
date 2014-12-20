<?php

include "db.php";

$response = array();

$id = $_POST['refID'];

$response["bool"] = false;
$query = mysql_query("SELECT * FROM `property_advert` WHERE `advert_reference` = '$id'");
if (mysql_num_rows($query) > 0) {

    $response["bool"] = true;

    $row = mysql_fetch_array($query);

    $response["ref"] = $row["advert_reference"];
    $response["email"] = $row["email_id"];
    $response["seller_type"] = $row["seller_type"];
    $response["bed_num"] = $row["bed_num"];
    $response["property_type"] = $row["property_type"];
    $response["rent_amount"] = $row["rent_amount"];
    $response["price_frequency"] = $row["price_frequency"];
    $response["deposit_amount"] = $row["deposit_amount"];
    $response["postcode"] = $row["postcode"];
    $response["address"] = $row["address"];
    $response["country"] = $row["country"];
    $response["furnishing"] = $row["furnishing"];
    $response["available_date"] = $row["available_date"];
    $response["reference_required"] = $row["reference_required"];
    $response["image"] = base64_encode($row['image']);
    $response["parking_available"] = $row["parking_available"];
    $response["garden_available"] = $row["garden_available"];
    $response["garage_available"] = $row["garage_available"];
    $response["balcony_available"] = $row["balcony_available"];
    $response["disabled_access_available"] = $row["disabled_access_available"];
    $response["broadband_available"] = $row["broadband_available"];
    $response["bill_included"] = $row["bill_included"];
    $response["advertiser_title"] = $row["advertiser_title"];
    $response["advert_title"] = $row["advert_title"];
    $response["advert_description"] = $row["advert_description"];
    $response["advertiser_fullname"] = $row["advertiser_fullname"];
    $response["display_name"] = $row["display_name"];
    $response["advertiser_telephone"] = $row["advertiser_telephone"];
    $response["display_telephone"] = $row["display_telephone"];

    echo json_encode($response);
} else {
    $response["bool"] = false;

    echo json_encode($response);
}
?>


