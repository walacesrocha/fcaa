<?php 
include 'gpoint/gpointconverter.class.php';
include 'gpoint/gpoint.php';
?>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0; padding: 0 }
            #map_canvas { height: 100% }
        </style>
        <script type="text/javascript" src="utmConversion.js">
        </script>
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBscSaAFUIMhcGKXA0T4LsVLG0ex4csn1s&sensor=false">
        </script>
        <script type="text/javascript">
            function initialize() {
                var mapOptions = {
                    center: new google.maps.LatLng(-20.279, -40.303),
                    zoom: 14,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map_canvas"),
                mapOptions);
					
                var exclama = new google.maps.Marker({
                    position: map.getCenter(),
                    map: map,
                    title: 'Um atrativo turístico próximo à FCAA'
                });

                var mais = new google.maps.Marker({
                    position: new google.maps.LatLng(-20.27, -40.3),
                    map: map,
                    title: ''
                });

                var interro = new google.maps.Marker({
                    position: new google.maps.LatLng(-20.275, -40.295),
                    map: map,
                    title: ''
                });
		
                exclama.setIcon('exclama.png');
                mais.setIcon('mais.png');
                interro.setIcon('interro.png');

            }
        </script>
    </head>
    <body onload="initialize()">
        <h1>Demonstração Google Maps</h1>
        <!div id="map_canvas" style="width:100%; height:100%"></div-->
        <button onclick="alert(ToLL(345678,7768908,24))">Teste</button>
        <?php
        $conversor = new GpointConverter();
        
        $array = $conversor->convertUtmToLatLng(345678, 7768908, 24);
        echo $array[0]." - ".$array[1];
            echo "<br>Teste";
        ?>
    </body>
</html>