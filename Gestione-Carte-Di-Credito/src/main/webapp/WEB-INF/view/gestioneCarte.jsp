<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Gestione carte </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/gestioneCarte.css">
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br>
<!-- FINE NAVBAR -->

<!-- INIZIO FORM BLOCCO/SBLOCCO CARTA -->

<div  id="divGestisciCarte">
<h2 id="testa"><b> Blocca/Sblocca carta </b></h2><br>

<div class="group">
			<p class="head">
				<b> Lista carte utenti: </b>
			</p>
			<div class="dettagli">
			<c:forEach items="${carte.getLista()}" var="i">
			<p><b>Nome carta: </b> ${i.getNome()}</p>
			<p><b>Numero carta: </b> ${i.getNumero()} </p>
			<p><b>Stato carta: <span class="stato-carta">${i.getStato()}</span> </b> </p>
			<form id="formGestisciCarte" action="GestisciCarte" method="post">
            <input type="hidden" name="numeroCarta" class="numero-carta" value="${i.getNumero()}"/>
            <button type="submit" class="btn btn-danger btn-sm blocca-carta" name="azione" value="blocca">Blocca</button>
            <button type="submit" class="btn btn-success btn-sm sblocca-carta" name="azione" value="sblocca">Sblocca</button>
            </form><br>
            <hr class="linea-divisoria">        
			</c:forEach>
			<p id="messaggi" style="color:red"><b>${errorMessage}</b></p>
			</div> 
		</div>
	</div>
	<br>
	<a type="button" role="button" class="btn btn-danger btn-lg"
		href="Home" style="position:relative; left: 43%;">&lt;&lt; Torna indietro </a><br>

<!--FINE FORM BLOCCO/SBLOCCO CARTA-->

<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">
	
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>