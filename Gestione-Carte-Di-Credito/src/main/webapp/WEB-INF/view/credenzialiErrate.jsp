<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Credenziali Errate </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<style>

  .container {
  background-color: black;
  opacity: 0.8;
  color: wheat;
  text-align: center;
  padding: 5%; 
  }
  
  #h2 {
  font-size: 45px;
  color: red;
  }
  
  p {
  font-size: 20px;
  }
  
</style>

</head>

<body style="background: url(img/sfondo2.jpg) no-repeat center fixed; background-size: cover;">

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br><br>
<!-- FINE NAVBAR -->

<div class="container">

<h2 id="h2"><b> Attenzione: errore di autenticazione! </b></h2><br>
<p> Effettua il login o registrati </p>

</div>

</body>
</html>