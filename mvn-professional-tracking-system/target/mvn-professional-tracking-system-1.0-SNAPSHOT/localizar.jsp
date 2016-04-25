<%-- 
    Document   : localizar
    Created on : 23/04/2016, 15:46:32
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
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>            
            <title>Localização</title>
        </head>
        <body>
            <!--Div que conten o MAPA-->
            <div id="mapa" style="height: 98vh; width: 98%; margin: auto;"></div>
            
            <form method="post" action="localizar.jsp">
                <fieldset style="opacity: 0.5">
                    <legend>Criar rotas</legend>

                    <div>
                        <label for="txtEnderecoPartida">Endereço de partida:</label>
                        <input type="text" id="txtEnderecoPartida"  name="txtEnderecoPartida" />
                    </div>

                    <div>
                        <label for="txtEnderecoChegada">Endereço de chegada:</label>
                        <input type="text" id="txtEnderecoChegada" value="91910004" name="txtEnderecoChegada" />
                    </div>

                    <div>
                        <input type="submit" id="btnEnviar" name="btnEnviar" value="Enviar" />
                    </div>
                </fieldset>
            </form>
            <!-- Jquery-->
            <script src="js/jquery.min.js"></script>            
            <!-- Maps API Javascript -->
            <!-- Arquivo de inicialização do mapa -->
            
            <script src="resources/js/mapa.js"></script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZiFconlkX4lv9MKcXQo-BkQX9-QY-L0w&signed_in=true&callback=initialize" ></script>

        </body>
    </html>
</f:view>
