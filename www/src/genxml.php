<?php

require("dbinfo.php");
require_once '../gpoint/gpointconverter.class.php';
require_once '../gpoint/gpoint.php';

// Start XML file, create parent node

$dom = new DOMDocument("1.0");
$node = $dom->createElement("markers");
$parnode = $dom->appendChild($node); 

// Opens a connection to a MySQL server

$connection=mysql_connect ($host, $username, $password);
if (!$connection) {  die('Not connected : ' . mysql_error());} 

// Set the active MySQL database

$db_selected = mysql_select_db($database, $connection);
if (!$db_selected) {
  die ('Can\'t use db : ' . mysql_error());
} 

// Select all the rows in the markers table

$query = "SELECT * FROM atrativos WHERE 1";
$result = mysql_query($query);
if (!$result) {  
  die('Invalid query: ' . mysql_error());
} 

header("Content-type: text/xml");

// conversor dos pontos GPS para Lat/Lang
$conversor = new GpointConverter();
        
        

// Iterate through the rows, adding XML nodes for each
while ($row = @mysql_fetch_assoc($result)){
  // ADD TO XML DOCUMENT NODE
  $node = $dom->createElement("marker");
  $newnode = $parnode->appendChild($node);

  $newnode->setAttribute("name", utf8_encode($row['NOME']));
  $newnode->setAttribute("address", "Sem endereco");
  
  $array = $conversor->convertUtmToLatLng($row['X_GPS'], $row['Y_GPS'], 24);
  
  $newnode->setAttribute("lat", $array[0]);
  $newnode->setAttribute("lng", $array[1]);
  $newnode->setAttribute("type", utf8_encode($row['TIPO']));
  $newnode->setAttribute("subtype", utf8_encode($row['SUBTIPO']));
  $newnode->setAttribute("pavimentacao", utf8_encode($row['PAVIMENTACAO']));
  $newnode->setAttribute("faixas", utf8_encode($row['FAIXAS']));
  $newnode->setAttribute("calcada", utf8_encode($row['CALCADA']));
  $newnode->setAttribute("estacionamento", utf8_encode($row['ESTACIONAMENTO']));
  $newnode->setAttribute("municipio", utf8_encode($row['MUNICIPIO']));
  $newnode->setAttribute("data_leitura", utf8_encode($row['DATA_LEITURA']));
  
}

echo $dom->saveXML();


?>
