<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Carta Sbloccata</title>

<style>
/* Stile del contenitore principale */
  .container {
    color: green;
    text-align: center;
    padding: 3%; 
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
  
    .container p span {
    color: #4B0082; /* Colore del testo viola scuro */
    font-size: 25px;
  }
  
    #imgCartasbloccata {
    width: 100px; /* Dimensione */
    height: 100px; /* Dimensione */
    border: 3px solid green; /* Aggiunto bordo */
    border-radius: 40%; /* Forma circolare */
    box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Ombreggiatura */
    margin-bottom: 20px; /* Spazio inferiore */
  }
  
</style>
</head>
<body style="background: url(img/sfondo2.jpg) no-repeat center fixed; background-size: cover;">

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br><br>
<!-- FINE NAVBAR -->

<div class="container">

<h2 id="h2"><b><img id="imgCartasbloccata" src="img/lucchetto.jpg"
			alt="Immagine sblocca carta"> Carta Sbloccata! </b></h2>
<hr>
<div id="p">
<h3><b> Operazione riuscita </b></h3>
<p><b>La carta <span>${carta.nome} - ${carta.numero}</span> è stata sbloccata!</b> </p>
<br>
<a type="button" role="button" class="btn btn-danger btn-lg" href="Home">&lt;&lt; Torna al profilo Amministratore </a><br>
</div>
</div>
<br>


</body>
</html>