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
            <title>Mapa</title>
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
                    top: 1px;
                    left: 45%;
                    z-index: 5;
                    background-color: transparent;
                    padding: 5px;
                    border: 1px solid #999;
                    text-align: center;
                    font-family: 'Roboto','sans-serif';
                    line-height: 30px;
                    padding-left: 10px;
                }
                #pontofinal{
                    background: tomato;position: absolute;opacity: 0;
                }

            </style>
        </head>
        <body >
            <div id="floating-panel">
                <b>Modo :</b>
                <select id="mode">
                    <option value="DRIVING">Carro</option>
                    <option value="WALKING">Andando</option>                                        
                </select>                
            </div>
            <div>
            <h:inputText  id="pontofinal" value="#{usuarioBean.usuario.endereco.logradouro} , #{usuarioBean.usuario.endereco.numero} , #{usuarioBean.usuario.endereco.bairro} , #{usuarioBean.usuario.endereco.cidade}" />                              
            </div>
            
            <div id="map"></div>
            <script type="text/javascript">

                var pontodepartida = document.getElementById('partida').value; 
                
                function initMap() {
                    if ("geolocation" in navigator) {
                        navigator.geolocation.getCurrentPosition(function (position) {
                            //alert(posicao.coords.latitude + ', ' + posicao.coords.longitude);
                             pontodepartida = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                             
                        });
                    } else {
                        alert('seu navegador não suporta geolocation');
                    }
                    
                    var directionsDisplay = new google.maps.DirectionsRenderer;
                    var directionsService = new google.maps.DirectionsService;
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 14,
                        center: {lat: 37.77, lng: -122.447}
                    });
                    
                    directionsDisplay.setMap(map);
                    /*executa a func calcula rota*/
                    calculaRota(directionsService, directionsDisplay);
                    document.getElementById('mode').addEventListener('change', function () {
                        calculaRota(directionsService, directionsDisplay);
                    });
                }

                function calculaRota(directionsService, directionsDisplay) {
                    
                   alert('com geolocalização!');
                    var selectedMode = document.getElementById('mode').value;
                    directionsService.route({
                        origin: pontodepartida, // cliente.
                        destination: document.getElementById('pontofinal').value, //profissional

                        travelMode: google.maps.TravelMode[selectedMode]
                    }, function (response, status) {
                        if (status === google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setDirections(response);
                        } else {
                            window.alert('Falha na localização' + status);
                        }
                    });

                }


                /*Exemplo results[0].formatted_address: "275-291 Bedford Ave, Brooklyn, NY 11211, USA"*/
            </script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZiFconlkX4lv9MKcXQo-BkQX9-QY-L0w&signed_in=true&callback=initMap"
            async defer></script>

        </body>
    </html>
</f:view>
