<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Ricarica </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/ricarica.css">
<script type="text/javascript" src="js/ricarica.js"></script>
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br>
<!-- FINE NAVBAR -->

<!-- INIZIO FORM RICARICA -->

<div  id="divRicarica">
<h2 id="testa"><b> Ricarica la tua carta! </b></h2><br>

<form style="padding-left:20%; padding-right:20%" id="formRicarica" action="Ricarica" method="post">

<label for="cartaPagamento"><b>Scegli la carta da ricaricare: </b></label>
 <select id="cartaPagamento" name="cartaPagamento" class="form-select">
 <c:forEach var="carta" items="${carteu.getLista()}">
 <option value="${carta.getNumero()}">${carta.getNome()}</option>
 </c:forEach>
 </select>
 <br>
<br>

<div class="form-group">
<label for="importo"><b> Importo:* </b></label><br>
<input type="number" class="form-control" id="importo" name="importo" placeholder="Inserire l'importo">
</div><br>

<div class="container mt-3">
<button type="submit" id="ricarica" class="btn btn-success"><b> Ricarica </b></button>
</div><br>

<p id="messaggi" style="color:red"></p>
</form>
</div>

<!--FINE FORM RICARICA-->

<a id="indietro" type="button" role="button" class="btn btn-danger btn-lg" href="Home" style="position: relative; left: 43%;">&lt;&lt; Torna indietro </a><br><br>

<img id="imgcarta" src="img/ricarica.png" alt="Immagine carta">
	
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->


</body>
</html>