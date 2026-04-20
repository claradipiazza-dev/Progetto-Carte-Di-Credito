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
<script type="text/javascript" src="js/aggiungiCartaUtente.js"></script>
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br>
<!-- FINE NAVBAR -->

<!-- INIZIO FORM AGGIUNGI CARTA -->

<div  id="divAggiungiCarte">
<h2 id="testa"><b> Aggiungi carta </b></h2><br>

<form style="padding-left:20%; padding-right:20%" id="formAggiungiCarte" name="AggiungiCartaUtenti" action="AggiungiCartaUtenti" method="post">

<div class="form-group">
<label for="nomecarta"><b> Nome carta:* </b></label><br>
<input type="text" class="form-control" id="nomecarta" name="nomecarta" placeholder="Inserire nome carta">
</div><br>

<div class="form-group">
<label for="card"><b> Numero carta:* </b></label><br>
<input type="text" class="form-control" id="card" name="card" placeholder="Inserire numero carta">
</div><br>

<div class="form-group">
<label for="cvv"><b> Cvv:* </b></label><br>
<input type="text" class="form-control" id="cvv" name="cvv" placeholder="Inserire cvv carta">
</div><br>


<div class="container mt-3">
<button type="submit" id="add" class="btn btn-success"><b> Aggiungi </b></button>
</div><br>

<p id="messaggi" style="color:red"></p>
</form>
</div>

<!--FINE FORM AGGIUNGI CARTA-->

<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">
	
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->


</body>
</html>