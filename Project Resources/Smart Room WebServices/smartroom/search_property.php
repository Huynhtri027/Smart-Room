<?php

include "db.php";

$count = 0;

$tempQuery = "SELECT * FROM `property_advert` ";

if (isset($_POST['minPrice'])) {

    if ($_POST['minPrice'] != "") {
        if ($count == 0) {
            $tempQuery = $tempQuery . " where ";
        }
        $count++;
        $tempQuery = $tempQuery . " rent_amount >= " . $_POST['minPrice'];
    }
}

if (isset($_POST['maxPrice'])) {

    if ($_POST['maxPrice'] != "") {
        if ($count == 0) {
            $tempQuery = $tempQuery . " where ";
        }
        if ($count > 0) {
            $tempQuery = $tempQuery . " and ";
        }
        $count++;
        $tempQuery = $tempQuery . " rent_amount <= " . $_POST['maxPrice'];
    }
}


if (isset($_POST['minBed'])) {

    if ($_POST['minBed'] != "") {
        if ($count == 0) {
            $tempQuery = $tempQuery . " where ";
        }
        if ($count > 0) {
            $tempQuery = $tempQuery . " and ";
        }
        $count++;
        $tempQuery = $tempQuery . " bed_num >= " . $_POST['minBed'];
    }
}


if (isset($_POST['maxBed'])) {

    if ($_POST['maxBed'] != "") {
        if ($count == 0) {
            $tempQuery = $tempQuery . " where ";
        }
        if ($count > 0) {
            $tempQuery = $tempQuery . " and ";
        }
        $count++;
        $tempQuery = $tempQuery . " bed_num <= " . $_POST['maxBed'];
    }
}


if (isset($_POST['searchValue'])) {
    if ($_POST['searchValue'] != "") {
        if ($count == 0) {
            $tempQuery = $tempQuery . " where ";
        }
        if ($count > 0) {
            $tempQuery = $tempQuery . " and ";
        }
        $count++;
        $tempQuery = $tempQuery . " `advert_reference` = '" . str_replace("#", "", $_POST['searchValue']) . "' or `postcode` = '%" . $_POST['searchValue'] . "%' or `address` = '%" . $_POST['searchValue'] . "%'";
    }
}

$tempQuery = $tempQuery . " ORDER BY `advert_reference` DESC";
$query = mysql_query($tempQuery);

if ($query) {
    $response["property_adverts"] = array();
    $search_results = array();
    while ($row = mysql_fetch_array($query)) {

        $search_results["advert_reference"] = $row["advert_reference"];
        $search_results["property_type"] = $row["property_type"];
        $search_results["rent_amount"] = $row["rent_amount"] . " (" . $row["price_frequency"] . ")";
        $search_results["address"] = $row["address"];
        $search_results["image"] = base64_encode($row['image']);
        $search_results["advert_title"] = $row["advert_title"];

        array_push($response["property_adverts"], $search_results);
    }
    $response["success"] = 1;
    $jsonstring = json_encode($response);
    echo $jsonstring;
} else {
    
}
?>