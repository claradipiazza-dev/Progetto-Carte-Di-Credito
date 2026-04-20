<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Utente </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/utente.css">
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" />
<!-- FINE NAVBAR -->

<div id="text" style="background: url(img/sfondocarte.jpg) no-repeat; background-size: cover;">
<div id="text2">
<pre><b> CARTE PREPAGATE </b></pre><br>
<h2><b> Le carte prepagate <br> pensate per il <br> mondo digital e <br> facili <br> da ottenere </b></h2>
</div>
</div><br>

<hr>

<div id="divTesto">
<p class="p1"> Una carta facile da ottenere, comoda per tutti e più sicura del contante. </p><br>

<div id="left">

<h2><b> Gestione facile delle tue finanze </b></h2><br>
<p class="p1"> Niente pù attesa in coda agli uffici postali. <br>Acquista
la tua carta prepagata per pagare <br> le bollette e
gestire le tue spese. 
</p>
<br><br>

<div class="container mt-3">
<a type="button" id="choice" class="btn btn-primary" href="SceltaCarte"><b> Scegli la tua carta </b></a><br>
</div>


<div id="right">
<h2><b> Facile da ricaricare </b></h2><br>
<p class="p1"> Puoi ricaricarla in ogni momento della giornata fornendo 
solo il numero della tua carta.
</p>
<br><br>

<div class="container mt-3">
<a type="button" id="ricarica" class="btn btn-primary" href="GestisciRicarica"><b> Ricarica </b></a><br>
</div>

</div><br><br>

</div>
</div>
<br>

<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">

<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>