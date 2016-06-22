/* 
 * Scrips Gmaps
 * 
 * 
 */
var iconProf = {
    url: "resources/img/Mapicon.png", // url
    scaledSize: new google.maps.Size(50, 50)
};
var map;
$(document).ready(function () {
    map = new GMaps({
        zoom: 10,
        el: '#map',
        lat: -30.0346,
        lng: -51.2177
    });
    $('#geocoding_form').submit(function (e) {
        e.preventDefault();
        GMaps.geocode({
            address: $('#address').val().trim(),
            callback: function (results, status) {
                if (status === 'OK') {
                    var latlng = results[0].geometry.location;
                    map.setCenter(latlng.lat(), latlng.lng());
                    map.addMarker({
                        lat: latlng.lat(),
                        lng: latlng.lng(),
                                                        infoWindow: {
          content: '<p>HTML Content</p>'
        }
                        
                    });
                }
            }
        });

    });
    $(".pz").each(function () {
       var valor = $(this).attr('value');
        GMaps.geocode({
            address: valor,
            callback: function (results, status) {
                if (status === 'OK') {
                    var latlng = results[0].geometry.location;                    
                    map.addMarker({
                        lat: latlng.lat(),
                        lng: latlng.lng(),
                        icon: iconProf
                    });
                }
            }
        });

    });
});
