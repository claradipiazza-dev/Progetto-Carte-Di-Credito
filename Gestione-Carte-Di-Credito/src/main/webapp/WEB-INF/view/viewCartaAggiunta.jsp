<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Carta Aggiunta</title>

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
  
    .container p span {
    color: #4B0082; /* Colore del testo viola scuro */
  }
  
  #imgAggiuntacarta {
    width: 100px; /* Dimensione */
    height: 100px; /* Dimensione */
    border: 3px solid green; /* Aggiunto bordo */
    border-radius: 40%; /* Forma circolare */
    box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Ombreggiatura */
    margin-bottom: 20px; /* Spazio inferiore */
    }
        
}
</style>
</head>
<body>

<div class="container">

<h2 id="h2"><b><img id="imgAggiuntacarta" src="img/aggiuntacarta.jpg"
			alt="Immagine aggiunta carta"> Carta Aggiunta! </b></h2>
<hr>
<div id="p">
<p><b>Nome: <span>${carta.nome}</span></b></p>
<p><b>Numero: <span>${carta.numero}</span></b></p>
<p><b>Cvv: <span>${carta.cvv}</span></b></p>
</div>
<h2><b>La tua carta è stata correttamente aggiunta!</b></h2>
<br>
<a type="button" role="button" class="btn btn-danger btn-lg" href="ProfiloUtente">&lt;&lt; Torna al profilo  </a><br>
</div>
<br>

</body>
</html>