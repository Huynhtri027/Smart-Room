CREATE TABLE IF NOT EXISTS `profile` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tenant` varchar(100) NOT NULL,
  `landlord` varchar(100) NOT NULL,
  `image_url` varchar(100) NOT NULL,
  `logged_using` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`,`email_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS  `property_advert` (
 `advert_reference` INT( 11 ) NOT NULL AUTO_INCREMENT,
 `email_id` VARCHAR( 100 ) NOT NULL ,
`seller_type` VARCHAR( 100 ) NOT NULL ,
 `bed_num` VARCHAR( 100 ) NOT NULL ,
 `property_type` VARCHAR( 100 ) NOT NULL ,
 `rent_amount` VARCHAR( 100 ) NOT NULL ,
 `price_frequency` VARCHAR( 100 ) NOT NULL ,
 `deposit_amount` VARCHAR( 100 ) NOT NULL ,
`postcode` VARCHAR( 100 ) NOT NULL ,
 `address` VARCHAR( 100 ) NOT NULL ,
 `country` VARCHAR( 100 ) NOT NULL ,
 `furnishing` VARCHAR( 100 ) NOT NULL ,
 `available_date` VARCHAR( 100 ) NOT NULL ,
 `reference_required` VARCHAR( 100 ) NOT NULL ,
 `image` mediumblob,
`parking_available` VARCHAR( 100 ) NOT NULL ,
`garden_available` VARCHAR( 100 ) NOT NULL ,
`garage_available` VARCHAR( 100 ) NOT NULL ,
`balcony_available` VARCHAR( 100 ) NOT NULL ,
`disabled_access_available` VARCHAR( 100 ) NOT NULL ,
`broadband_available` VARCHAR( 100 ) NOT NULL ,
`bill_included` VARCHAR( 100 ) NOT NULL ,
`advertiser_title` VARCHAR( 100 ) NOT NULL ,
 `advert_title` VARCHAR( 100 ) NOT NULL ,
 `advert_description` VARCHAR( 100 ) NOT NULL ,
 `advertiser_fullname` VARCHAR( 100 ) NOT NULL ,
 `display_name` VARCHAR( 100 ) NOT NULL ,
 `advertiser_telephone` VARCHAR( 100 ) NOT NULL ,
 `display_telephone` VARCHAR( 100 ) NOT NULL ,
 `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
 `updated_at` TIMESTAMP NOT NULL DEFAULT  '0000-00-00 00:00:00',
PRIMARY KEY (  `advert_reference` ,  `email_id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT =1;

CREATE TABLE IF NOT EXISTS  `advert_messages` (
 `messageID` INT( 11 ) NOT NULL AUTO_INCREMENT ,
 `sender_id` VARCHAR( 100 ) NOT NULL ,
 `advert_ref_id` VARCHAR( 100 ) NOT NULL ,
 `message` VARCHAR( 100 ) NOT NULL ,
 `status` VARCHAR( 100 ) NOT NULL ,
 `notified` VARCHAR( 100 ) NOT NULL ,
 `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
 `updated_at` TIMESTAMP NOT NULL DEFAULT  '0000-00-00 00:00:00',
PRIMARY KEY (  `messageID` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT =1;



