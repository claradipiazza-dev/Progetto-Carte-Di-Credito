<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Aggiungi Carta </title>
<link rel="stylesheet" href="css/aggiungiCarta.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/aggiungiCarta.js"></script>
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br>
<!-- FINE NAVBAR -->

<!-- INIZIO FORM AGGIUNGI CARTA -->

<div  id="divAggiungiCarte">
<h2 id="testa"><b> Aggiungi carta </b></h2><br>

<form style="padding-left:20%; padding-right:20%" id="formAggiungiCarte" name="AggiungiCarta" action="AggiungiCarta" method="post">
 <input type="hidden" name="action" value="add">
<div class="form-group">
<label for="nomecarta"><b> Nome carta:* </b></label><br>
<input type="text" class="form-control" id="nomecarta" name="nomecarta" placeholder="Inserire nome carta">
</div><br>

<div class="form-group">
<label for="prezzo"><b> Prezzo:* </b></label><br>
<input type="text" class="form-control" id="prezzo" name="prezzo" placeholder="Inserire prezzo carta">
</div><br>

<div class="form-group">
<label for="quantità"><b> Quantità:* </b></label><br>
<input type="number" class="form-control" id="quantità" name="quantità" placeholder="Inserire quantità carta">
</div><br>

<div class="container mt-3">
<button type="submit" id="add" class="btn btn-success"><b> Aggiungi </b></button>
</div><br>

<div class="form-group">
<input type="hidden" name="operazione" value="aggiungi">
</div>

</form>
<p id="error_message" style="color:red"><b></b></p>
</div>
<br>
	<a type="button" role="button" class="btn btn-danger btn-lg"
		href="Home" style="position:relative; left: 43%;">&lt;&lt; Torna indietro </a><br>
<!--FINE FORM AGGIUNGI CARTA-->
<br>
<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">
	
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->


</body>
</html>