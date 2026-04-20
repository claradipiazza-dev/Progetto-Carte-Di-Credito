<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/profiloUtente.css">
<script type="text/javascript" src="js/cartaUtente.js"></script>
</head>
<body id="radice">

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br>
	<!-- FINE NAVBAR -->
	<div id="divRadice">

	<h2
		style="color: mediumvioletred; text-align: center; font-size: 45px;  font-family: 'Caveat', cursive;">
		<b> Benvenuto nel tuo profilo! </b>
	</h2>
	<br>
	<br>

	<h3 style="text-align: center;  font-family: 'Caveat', cursive;" id="info">
		<b> <img id="imgcarta" src="img/iconautente.jpg"
			alt="Immagine utente">Informazioni personali
		</b>
	</h3>
	<br>

	<div class="informazioni">

		<div class="div-group">
			<p class="head">
				<b> Nome: </b>
			</p>
			<p>${utente.nome}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Cognome: </b>
			</p>
			<p>${utente.cognome}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Email: </b>
			</p>
			<p>${utente.email}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Numero di telefono: </b>
			</p>
			<p>${utente.numero}</p>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Lista carte: </b>
			</p>
			<img id="imgcarte" src="img/carte.jpg" alt="Immagine carte"><br>
			<c:forEach items="${carte.getLista()}" var="i" varStatus="status">
				<p
					style="background-color: orange; opacity: 0.9; color: black; padding: 6px; border-radius: 8px;">
					<b> Carta ${status.index + 1}</b>
				</p>
				<p>
					<b>Nome: </b> ${i.getNome()}
				</p>
				<p>
					<b>Nnumero: </b> ${i.getNumero()}
				</p>
				<p>
					<b>Credito: </b> &euro; ${i.credito}
				</p>
				<button type="button" class="btn btn-danger btn-sm elimina-carta"
					data-numero="${i.getNumero()}">Elimina</button>
				<br>
				<hr class="linea-divisoria">
			</c:forEach>
			<a type="submit" role="button" class="btn btn-success btn-lg"
				data-bs-toggle="modal" data-bs-target="#myModalCarta"> Aggiungi
				carta </a><br> <br>
		</div>
		<br>

		<div class="div-group">
			<p class="head">
				<b> Lista acquisti: </b>
			</p>
			<img id="imgacquisti" src="img/acquisti.jpg" alt="Immagine acquisti"><br>
			<c:forEach items="${listaAcquisti.getLista()}" var="i"
				varStatus="status">
				<p
					style="background-color: black; opacity: 0.8; color: orange; padding: 6px;">
					<b> Acquisto ${status.index + 1}</b>
				</p>
				<p>
					<b>Nome: </b> ${i.getNome()}
				</p>
				<p>
					<b>Nnumero: </b> ${i.getNumero()}
				</p>
				<p>
					<b>Cvv: </b> ${i.getCvv()}
				</p>
				<p>
					<b>Prezzo: </b> &euro; ${i.getPrezzo()}
				</p>
				<p>
					<b>Quantità: </b> ${i.getQuantità()}
				</p>
				<hr class="linea-divisoria">
			</c:forEach>
		</div>
		<br>
	</div>
	<br>
	<br>
	<a type="button" role="button" class="btn btn-danger btn-lg"
		href="Home">&lt;&lt; Torna indietro </a>
	<br>
	<br>
</div>

	<!-- INIZIO Modal AGGIUNTA CARTA -->
	<div class="modal fade" id="myModalCarta">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Aggiungi Carta </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM AGGIUNTA CARTA-->
					<div id="contenitoreCartaUtente">
						<form name="formCartaUtente" id="formCartaUtente"
							action="AggiungiCartaUtente" method="post">
							<div class="form-group">
								<label for="nomecarta"><b> Nome della carta:* </b></label><br> <input
									type="text" class="form-control" id="nomecarta"
									name="nomecarta" placeholder="Inserire il nome della carta"
									required="required">
							</div>
							<br>
							<div class="form-group">
								<label for="card"><b> Numero della carta:* </b></label><br>
								<input type="text" class="form-control" id="card" name="card"
									placeholder="Inserire il numero della carta (4 per Visa - 5 per Mastercard)"
									required="required" maxlength="16">
							</div>
							<br>
							<div class="form-group">
								<label for="cvv"><b> Cvv:* </b></label><br> <input
									type="text" class="form-control" id="cvv" name="cvv"
									placeholder="Inserire il cvv della carta" required="required"
									maxlength="3">
									<input type="hidden" name="operazione" value="aggiungi">
							<p id="messaggio_errore" style="color: red"></p>
							</div>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="avanti" class="btn btn-success btn-lg">
								Aggiungi</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM AGGIUNTA CARTA  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal AGGIUNTA CARTA -->


	<!-- Finestra modale -->
	<div class="modal fade" id="confermaEliminazioneModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Conferma
						eliminazione</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Sei sicuro di voler eliminare questa
					carta?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Annulla</button>
					<button type="button" class="btn btn-danger conferma-eliminazione">Elimina</button>
				</div>
			</div>
		</div>
	</div>


	<script>
		$(document).ready(
				function() {
					$(".elimina-carta").click(
							function() {
								// Recupero il numero della carta dal pulsante
								var numeroCarta = $(this).data("numero");
								// Imposto il numero della carta nella finestra modale
								$("#confermaEliminazioneModal").data(
										"numeroCarta", numeroCarta);
								// Mostro la finestra modale
								$("#confermaEliminazioneModal").modal("show");
							});

					// Gestisco la conferma di eliminazione
					$(".conferma-eliminazione").click(
							function() {
								// Recupero il numero della carta dalla finestra modale
								var numeroCarta = $(
										"#confermaEliminazioneModal").data(
										"numeroCarta");
								// Esegue la richiesta POST per eliminare la carta
								$.post("EliminaCartaUtente", {
									numeroCarta : numeroCarta
								}, function() {
									// Ricarica la pagina per visualizzare le modifiche
									location.reload(true);
								});
								// Chiudo la finestra modale
								$("#confermaEliminazioneModal").modal("hide");
							});
				});
	</script>

</body>
</html>