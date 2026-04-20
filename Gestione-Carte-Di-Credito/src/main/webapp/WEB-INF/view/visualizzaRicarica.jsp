<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Visualizza ricarica </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
/* Stile del contenitore principale */
  .container {
    color: green;
    text-align: center;
    padding: 1%; 
    font-size: 20px;
    border: 2px solid #ccc; /* Aggiunto bordo */
    border-radius: 10px; /* Angoli arrotondati */
    background-color: #f9f9f9; /* Sfondo */
  }
  
  /* Stile del titolo */
  #h2 {
    font-size: 50px;
    text-align: center;
    color: green
    font-family: Arial, sans-serif; /* Font */
    margin-bottom: 20px; /* Spazio inferiore */
  }
  
  /* Stile dei paragrafi */
  .container p {
    color: green; /* Colore del testo */
    font-size: 20px; /* Dimensione del testo */
  }
  
    .container label span {
    color: #4B0082; /* Colore del testo viola scuro */
  }
  
    #imgRicarica {
    width: 100px; /* Dimensione */
    height: 100px; /* Dimensione */
    border: 3px solid green; /* Aggiunto bordo */
    border-radius: 40%; /* Forma circolare */
    box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Ombreggiatura */
    margin-bottom: 20px; /* Spazio inferiore */
  }
  
</style>
</head>

<body style="background: url(img/sfondo2.jpg) no-repeat center fixed; background-size: cover;"><br>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br><br>
<!-- FINE NAVBAR -->

<div class="container">

<h2 id="h2"><b><img id="imgRicarica" src="img/ricaricaCarta.jpg"
			alt="Immagine ricarica carta"> Ricarica effettuata! </b></h2>
<hr>
<label for="tipo"><b> Nome utente: <span>${utente.nome}</span></b></label><br>
<c:forEach items="${carte.getLista()}" var="i">
<label for="tipo" style="color: green; font-size: 20px;"><b> Nome carta: <span>${i.getNome()}</span></b></label><br>
<label for="tipo" style="color: green; font-size: 20px;"><b> Numero carta: <span>${i.getNumero()}</span></b></label><br>
</c:forEach>
<label for="card"><b> Credito: <span>&euro; ${creditoAggiornato.credito} </span></b></label><br>
<br>
<a type="button" role="button" class="btn btn-danger btn-lg" href="Home">&lt;&lt; Torna al profilo utente </a><br>

</div>


</body>
</html>