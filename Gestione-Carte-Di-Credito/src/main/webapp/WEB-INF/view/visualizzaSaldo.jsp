<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Visualizza Saldo </title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
/* Stile del contenitore principale */
  .container {
    color: green;
    text-align: center;
    padding: 1%; 
    font-size: 10px;
    border: 2px solid #ccc; /* Aggiunto bordo */
    border-radius: 10px; /* Angoli arrotondati */
    background-color: #f9f9f9; /* Sfondo */
  }
  
  /* Stile del titolo */
  #h2 {
    font-size: 45px;
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

#imgcarta {
	position: relative;
	width: 10%;
	height: 10%;
	float: left;
}

  #imgCredito {
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

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br><br>
<!-- FINE NAVBAR -->

<div class="container">

<h2 id="h2"><b><img id="imgCredito" src="img/credito.jpg"
			alt="Immagine credito carta"> Il tuo credito è pari a: </b></h2>
<hr>
<div id="p">
<c:choose>
            <%-- Verifica se l'attributo saldo è presente nell'oggetto della richiesta --%>
            <c:when test="${not empty saldo}">
                <p><b>Importo: &euro; ${saldo.credito} </b></p>
            </c:when>
            <%-- Verifica se l'attributo credito è presente nell'oggetto della richiesta --%>
            <c:when test="${not empty credito}">
                <p><b>Importo: &euro; ${credito.credito} </b></p>
            </c:when>
        </c:choose>
</div>
<br>
<a type="button" id="choice" class="btn btn-danger" href="index.jsp"><b> Torna all'home </b></a><br>
</div>
	
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>