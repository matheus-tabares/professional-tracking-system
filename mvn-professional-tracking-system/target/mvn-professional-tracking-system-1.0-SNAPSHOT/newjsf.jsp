<%-- 
    Document   : newjsf
    Created on : 23/04/2016, 17:56:24
    Author     : Igor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta name="viewport" content="initial-scale=1.0, user-scalable=no">            
            <title>Travel modes in directions</title>
            <style>
                html, body {
                    height: 100%;
                    margin: 0;
                    padding: 0;
                }
                #map {
                    height: 100%;
                }
                #floating-panel {
                    position: absolute;
                    top: 10px;
                    left: 25%;
                    z-index: 5;
                    background-color: #fff;
                    padding: 5px;
                    border: 1px solid #999;
                    text-align: center;
                    font-family: 'Roboto','sans-serif';
                    line-height: 30px;
                    padding-left: 10px;
                }

            </style>
        </head>
        <body>
            <div id="floating-panel">
                <b>Mode of Travel: </b>
                <select id="mode">
                    <option value="DRIVING">Driving</option>
                    <option value="WALKING">Walking</option>
                    <option value="BICYCLING">Bicycling</option>
                    <option value="TRANSIT">Transit</option>
                </select>
            </div>
            <div id="map"></div>
            <script>
                function initMap() {
                    var directionsDisplay = new google.maps.DirectionsRenderer;
                    var directionsService = new google.maps.DirectionsService;
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 14,
                        center: {lat: 37.77, lng: -122.447}
                    });
                    directionsDisplay.setMap(map);

                    calculateAndDisplayRoute(directionsService, directionsDisplay);
                    document.getElementById('mode').addEventListener('change', function () {
                        calculateAndDisplayRoute(directionsService, directionsDisplay);
                    });
                }

                function calculateAndDisplayRoute(directionsService, directionsDisplay) {
                    var selectedMode = document.getElementById('mode').value;
                    directionsService.route({
                        origin: {lat: 37.77, lng: -122.447}, // Haight.
                        destination: {lat: 37.768, lng: -122.511}, // Ocean Beach.
                        // Note that Javascript allows us to access the constant
                        // using square brackets and a string value as its
                        // "property."
                        travelMode: google.maps.TravelMode[selectedMode]
                    }, function (response, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setDirections(response);
                        } else {
                            window.alert('Directions request failed due to ' + status);
                        }
                    });
                }

            </script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZiFconlkX4lv9MKcXQo-BkQX9-QY-L0w&signed_in=true&callback=initMap"
            async defer></script>

        </body>
    </html>
</f:view>
<!--EXEMPLO QUE ESTOU SEGUINDO DA DOCUMENTAÇÃO DO GOOGLE MAPS-->