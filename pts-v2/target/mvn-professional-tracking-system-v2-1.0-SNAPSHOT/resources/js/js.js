/*
 * SCRIPT QUE CARREGA O MAPA NA HOME
 * 
 * 
 */
var map;
var icones = ['resources/img/pinB.png',
    'resources/img/pinB.png',
    'resources/img/icones/carpinteiro.png',
    'resources/img/icones/eletrecista.png',
    'resources/img/icones/encanador.png',
    'resources/img/icones/estofador.png',
    'resources/img/icones/faxineiro.png',
    'resources/img/icones/jardineiro.png',
    'resources/img/icones/marceneiro.png', 
    'resources/img/icones/mecanico.png',
    'resources/img/icones/pedreiro.png',    
    'resources/img/icones/pintor.png'        
    ];

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
var pontos = [];
var infos = [];
function carregaEnd() {

    clearMarkers();
    var geocoder = new google.maps.Geocoder();
    $(".pz").each(function () {
        var profissional = $(this).data('profissional');/*PASSA OBJETO PROFISSIONAL*/
        var icone = profissional[0];/*NUMERO QUE FUNCIONA COM AS CATEGORIAS FIXAS E PUXA IMAGEM*/
        var nomeProf = profissional[1];
        var categori = profissional[2];
        var endereco = profissional[3];
        var descrica = profissional[4];
        var profid = '.xyz'+profissional[5];
        //alert(profid)
        var pin = {
            url: icones[icone],
            scaledSize: new google.maps.Size(50, 50)
        };
        geocoder.geocode({'address': endereco}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    icon: pin,
                    title: nomeProf
                });
                marker.setAnimation(google.maps.Animation.DROP);

                var infowindow = new google.maps.InfoWindow();

                var content = '<div class="nome-p">' + nomeProf +'</div>' +
                        '<div>Categoria :' + categori + '</div>' +
                        '<div><p class="desc">' + descrica + '</p></div>' +
                        '<div class="fresc"><img src="resources/img/stars.png"/></div>'+
                        '<div><button onclick="$('+"'"+profid+"'"+').click();">+DETALHES</button></div>';

                infowindow.setContent(content);
                marker.addListener('click', function () {
                    closeinfos();
                    infowindow.open(map, marker);
                    //map.setCenter(results[0].geometry.location);
                });
                pontos.push(marker);
                infos.push(infowindow);
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

function clearMarkers() {
    for (var i = 0; i < pontos.length; i++) {
        pontos[i].setMap(null);
    }
}
function closeinfos() {
    for (var i = 0; i < infos.length; i++) {
        infos[i].close();
    }
}
