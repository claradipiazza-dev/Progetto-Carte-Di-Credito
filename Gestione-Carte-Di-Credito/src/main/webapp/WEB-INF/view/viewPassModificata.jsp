<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Password modificata</title>

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
    font-size: px;
    text-align: center;
    color: green
    font-family: Arial, sans-serif; /* Font */
    margin-bottom: 20px; /* Spazio inferiore */
  }
  
    #imgPassmodificata {
    width: 100px; /* Dimensione */
    height: 100px; /* Dimensione */
    border: 3px solid green; /* Aggiunto bordo */
    border-radius: 40%; /* Forma circolare */
    box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Ombreggiatura */
    margin-bottom: 20px; /* Spazio inferiore */
  }
</style>
</head>
<body id="bodyPass" style="background: url(../../img/sfondo2.jpg) no-repeat center fixed; background-size: cover;">
<br><br>

<div class="container">

<h2 id="h2"><b><img id="imgPassmodificata" src="img/passModificata.jpg"
			alt="Immagine password modificata"> La tua password è stata correttamente modificata! </b></h2>
<hr>
<br>
<a type="button" role="button" class="btn btn-danger btn-lg" href="index.jsp">&lt;&lt; Torna all'home </a><br>
</div>

</body>
</html>