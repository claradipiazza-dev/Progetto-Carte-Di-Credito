<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Carte </title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet" href="css/acquistoCarte.css">
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br>
<!-- FINE NAVBAR -->

<div id="testo" style="background: url(img/acquisto.jpg) no-repeat fixed; background-size: cover;"> 
</div>

<h2 class="head"><b> Lista Carte </b></h2>

<br>
<div class="carte">
<div id="errore_${i.getNumero()}" style="color: red;"><b>${errore}</b></div>

<c:forEach var="i" items="${carta.getLista()}">
    <h2 id="testa"><b> Carta prepagata ${i.getNome()} </b></h2>
    <div class="group"><br>
        <p><b>Banca:</b> ${i.getNome()}</p>
        <p><b>Numero:</b> ${i.getNumero()}</p>
        <input type="hidden" name="cvv" value="${i.getCvv()}" />
        <p><b>Prezzo:</b> ${i.getPrezzo()} &euro;</p>                   
        <c:if test="${not empty sessionScope.utente and sessionScope.utente.ruolo eq 'amministratore'}">
            <p><b>Quantità:</b> ${i.getQuantità()}</p>
        </c:if>
        <c:if test="${not empty sessionScope.utente and sessionScope.utente.ruolo eq 'utente'}">
            <form id="formAcquistoCarte" action="AcquistoCarte" method="post">
                <input type="hidden" name="nomeCarta" value="${i.getNome()}"/>
                <input type="hidden" name="numeroCarta" value="${i.getNumero()}"/>
                <input type="hidden" name="cvvCasuale" value="${i.getCvv()}"/>
                <input type="hidden" name="prezzoCarta" value="${i.getPrezzo()}"/>
                <label for="quantita_${i.getNumero()}" ><b>Quantità: </b></label>          
                <input type="number" id="quantita_${i.getNumero()}" name="quantita" min="1" style="width: 55px;"></input> <br>
                <button type="submit" class="btn btn-success"><b> Acquista </b></button><br><br>
            </form>
        </c:if>
    </div>
</c:forEach>
</div>

<c:if test="${not empty sessionScope.utente and sessionScope.utente.ruolo eq 'utente'}">
    <a id="indietro" type="button" role="button" class="btn btn-danger btn-lg" href="Home" style="position: relative; left: 42%;">&lt;&lt; Torna indietro </a><br>
</c:if>
<c:if test="${not empty sessionScope.utente and sessionScope.utente.ruolo eq 'amministratore'}">
    <a id="profilo" type="button" role="button" class="btn btn-danger btn-lg" href="Home" style="position:relative; left: 38%;">&lt;&lt; Torna al profilo Amministratore </a><br>
</c:if><br>
<br>
<br><br>
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->


</body>
</html>
