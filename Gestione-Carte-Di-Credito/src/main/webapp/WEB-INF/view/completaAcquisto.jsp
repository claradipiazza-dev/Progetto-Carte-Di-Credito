<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Completa Acquisto </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet" href="css/completaAcquisto.css">
<script type="text/javascript" src="js/acquisto.js"></script>
  
</head>
<body>

 <!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" />
<!-- FINE NAVBAR -->

<div class="container">

<div id="riepilogo-acquisto" class="container-left">
<h2 id="h2"><b> Riepilogo del tuo acquisto! </b></h2><br>
<p><b> Nome carta:</b> ${nomecarta} </p>
<p><b> Numero carta:</b> ${numerocarta} </p>
<input type="hidden" name="cvv" value="${i.getCvv()}" />
<p><b> Prezzo: </b> &euro; ${prezzo} </p>
<p><b> Quantià:</b> ${quantità} x ${nomecarta} </p>
<br>
<img id="imgcarrello" src="img/iconacarrello.jpg" alt="Immagine carrello">
</div>

<div class="container-right">
<h2 style="color: red; font-size: 45px; text-align: center;"><b> Completa ordine</b></h2><br>

<form  id="formPagamento" action="CompletaPagamento" method="post">
<input type="hidden" name="nomeCarta" value="${nomecarta}"/>
<input type="hidden" name="numeroCarta" value="${numerocarta}"/>
<input type="hidden" id="cvvCasuale" name="cvvCasuale" value="${cvv}">
<input type="hidden" name="prezzoCarta" value="${prezzo}"/>       
<input type="hidden" id="quantita_${numerocarta}" name="quantita" value="${quantità}" style="width: 55px;"></input>
 <label for="cartaPagamento"><b>Scegli la carta per il pagamento: </b></label>
 <select id="cartaPagamento" name="cartaPagamento">
 <c:forEach var="carta" items="${carteu.getLista()}">
 <option value="${carta.getNumero()}">${carta.getNome()}</option>
 </c:forEach>
 </select>
 <br><br>
 <label for="cvv"><b> Cvv: </b></label><br>
 <input type="text" id="cvv" name="cvv" placeholder="Inserire il cvv della carta"  required="required" style="width: 40%;" /><br><br>
 
 <c:if test="${not empty messaggio2}">
    <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModalRicarica">Ricarica Carta</button>
</c:if> 
 <br><br>
<button type="submit" class="btn btn-success"><b> Completa pagamento </b></button><br>
<p id="messaggi" style="color:red"><b>${messaggio}${messaggio2}</b></p>
<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">
</form>
</div><br>
<a id="indietro" type="button" role="button" class="btn btn-danger btn-lg" href="SceltaCarte" style="position: relative; left: 42%;">&lt;&lt; Torna indietro </a><br><br>
</div>


<!-- INIZIO Modal RICARICA -->
	<div class="modal fade" id="myModalRicarica">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Ricarica </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM RICARICA-->
					<div id="contenitoreRicarica">
						<form name="formRicarica" id="formRicarica" action="Ricarica" method="post">
						<div class="form-group">
                         <label for="cartaPagamento"><b>Scegli la carta da ricaricare: </b></label>
                         <select id="cartaPagamento" name="cartaPagamento" class="form-select">
                         <c:forEach var="carta" items="${carteu.getLista()}">
                         <option value="${carta.getNumero()}">${carta.getNome()}</option>
                         </c:forEach>
                         </select>
                        </div><br><br>

                        <div class="form-group">
                        <label for="importo"><b> Importo:* </b></label><br>
                        <input type="number" class="form-control" id="importo" name="importo" placeholder="Inserire l'importo">
                        </div><br>

                        <div class="form-group">
                        <button type="submit" id="ricarica" class="btn btn-success"><b> Ricarica </b></button>
                        </div>
                        <br>
						</form>
					</div>
					<!-- FINE FORM RICARICA  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal RICARICA -->

<br>
<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

</body>
</html>