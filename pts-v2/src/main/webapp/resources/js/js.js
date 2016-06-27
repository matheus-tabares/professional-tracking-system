/*
 * SCRIPT QUE CARREGA O MAPA NA HOME
 * 
 * 
 */
var map;
var pin = {
    url: 'resources/img/pinB.png',
    scaledSize: new google.maps.Size(50, 50)
};

function loadmap() {

    var portoAlegre = {lat: -30.0346, lng: -51.2177};
    var mapOptions = {
        zoom: 10,
        center: portoAlegre,
        mapTypeControl: false,
        streetViewControl: false
    };
    map = new google.maps.Map(document.getElementById("map"), mapOptions);

}

function carregaEnd() {
    //alert($('.pz').length);
    var geocoder = new google.maps.Geocoder();
    $(".pz").each(function () {
        var valorEnd = $(this).attr('value');
        var nomeProf = $(this).attr('name');
        var categ = $(this).attr('title');
        geocoder.geocode({'address': valorEnd}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    icon: pin,
                    title: valorEnd
                });
                var infowindow = new google.maps.InfoWindow();
                var content = '<p class="map-content">' + nomeProf + '</p> <p>Categoria ' + categ + '</p>';
                infowindow.setContent(content);

                marker.addListener('click', function () {
                    infowindow.open(map, marker);
                    map.setCenter(results[0].geometry.location);
                    //map.setZoom(12);
                    marker.setAnimation(google.maps.Animation.DROP);
                });
            } else {
                alert("DEU BOSTON!!!: " + status);
            }
        });

    });

}

var iconeUser = {
    url: 'resources/img/pinBusca.png',
    scaledSize: new google.maps.Size(50, 50)
};
var xxx;
var foraArea;
function pontoTest() {

    var geocoder = new google.maps.Geocoder();

    $('#geocoding_form').submit(function (e) {
        e.preventDefault();

        var address = document.getElementById("address").value;
        geocoder.geocode({'address': address}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                foraArea = results[0].formatted_address;
                if (foraArea.match(/Porto/)) {

                } else {
                    alert('VOCÊ ESTA FORA DA AREA DE COBERTURA');
                }
                map.setCenter(results[0].geometry.location);
                //map.setZoom(12);
                xxx = new google.maps.Marker({
                    map: map,
                    title: 'Você esta aqui',
                    icon: iconeUser,
                    position: results[0].geometry.location
                });
            } else {
                alert("Há pegadinha do Malandro!!!" + status);
            }
        });
        $("#address").val('');
        limpar();
    });
}

function geolocalizacao() {
    
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            map.setCenter(pos);
            //map.setZoom(12);
            xxx = new google.maps.Marker({
                map: map,
                title: 'Você esta aqui',
                icon: iconeUser,
                position: pos
            });
            marker.setAnimation(google.maps.Animation.DROP);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {

        handleLocationError(false, infoWindow, map.getCenter());
    }
    limpar();
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}
/*PARA UM MARCADOR APAGAR QUANDO UMA NOVA PESQUISA FOR FEITA*/
function limpar() {
    xxx.setMap(null);
}

$(window).load(function () {
    
    loadmap();
    carregaEnd();
    pontoTest();
});
function reload(){
    $('#loader').show();
    window.setTimeout(function() {
        $('#loader').hide();
}, 5000);
    loadmap();
    carregaEnd();
}
