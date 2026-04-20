<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Amministratore</title>
<link rel="stylesheet" href="css/amministratoreView.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

			<!-- INIZIO NAVBAR -->
			<jsp:include page="/WEB-INF/components/navbar.jsp" /><br>
			
			<!-- FINE NAVBAR -->

			<div id="div1"
				style="background: url(img/admin.jpg) no-repeat fixed; background-size: cover;">
				<h1 id="testo1">
					<b> Benvenuto nel profilo Amministratore! </b>
				</h1>
			</div>
			<br>

			<div class="container mt-3">
				<a type="button" id="blocca-sblocca" class="btn btn-danger"
					href="GestioneCarte"><b> Area <br> Blocca/Sblocca
						carta
				</b></a> 
				<a type="button" id="addcarta" class="btn btn-success"
					href="AreaCarta"><b> Area <br> Aggiungi carta
				</b></a>
				<a type="button" id="lista-carte" class="btn btn-primary"
					href="ListaCarte"><b> Area <br> Lista carte
				</b></a>
			</div>
			<br>

			<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">

			<!-- FOOTER -->
			<jsp:include page="/WEB-INF/components/footer.jsp" />
			<!-- FINE FOOTER -->

</body>
</html>