<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

mysql_connect('localhost', 'root', '') or die("Cannot connect to Server! " . mysql_error());

mysql_select_db('smartroom') or die("Cannot connect to Database! " . mysql_error());
?>
