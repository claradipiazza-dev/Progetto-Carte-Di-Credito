<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Carte </title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet" href="css/acquistoCarte.css">
</head>
<body>

<!-- INIZIO NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br>
<!-- FINE NAVBAR -->

<div id="testo" style="background: url(img/acquisto.jpg) no-repeat fixed; background-size: cover;"> 
</div>

<h2 class="head"><b> Lista Carte </b></h2>

<br>
<div class="carte">
<c:if test="${not empty messaggioConferma}">
    <div style="color: green;">
       <b>${messaggioConferma}</b>
    </div>
</c:if>

<c:forEach var="i" items="${carta.getLista()}">
    <h2 id="testa"><b> Carta prepagata ${i.getNome()} </b></h2>
    <div class="group"><br>
        <p><b>Banca:</b> ${i.getNome()}</p>
        <p><b>Numero:</b> ${i.getNumero()}</p>
        <p><b>Prezzo:</b> ${i.getPrezzo()} &euro;</p>                   
        <button type="button" class="btn btn-primary btn-sm modifica-carta" data-bs-toggle="modal" data-bs-target="#myModalModificaCarta" data-numero="${i.getNumero()}" data-nome="${i.getNome()}" data-prezzo="${i.getPrezzo()}" data-quantita="${i.getQuantità()}">Modifica</button>
        <button type="button" class="btn btn-danger btn-sm elimina-carta" data-numero="${i.getNumero()}">Elimina</button><br>           
    </div><br>
</c:forEach>
</div><br>
    <a id="btn_indietro" type="button" role="button" class="btn btn-danger btn-lg" href="Home" style="position:relative; left: 43%;">&lt;&lt; Torna indietro </a><br>
<br>

<img id="imgcarta" src="img/iconacarta.png" alt="Immagine carta">


<!-- INIZIO Modal MODIFICA -->
<div class="modal fade" id="myModalModificaCarta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <b> Modifica Carta </b>
                </h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form id="formModificaCarta" action="ModificaCarta" method="post">
                    <input type="hidden" id="numeroCartaModifica" name="numeroCarta">
                    <div class="form-group">
                        <label for="nomeCartaModifica"><b> Nome carta:* </b></label><br>
                        <input type="text" class="form-control" id="nomeCartaModifica" name="nomeCartaModifica">
                    </div>
                    <div class="form-group">
                        <label for="prezzoCartaModifica"><b> Prezzo:* </b></label><br>
                        <input type="number" class="form-control" id="prezzoCartaModifica" name="prezzoCartaModifica">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success"><b> Salva modifiche </b></button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Chiudi</button>
            </div>
        </div>
    </div>
</div>
<!-- FINE Modal MODIFICA -->


<!-- Finestra modale RICARICA -->
<div class="modal fade" id="confermaModificaModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Conferma modifica</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Sei sicuro di voler modifcare questa carta?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-danger conferma-modifica">Modifica</button>
      </div>
    </div>
  </div>
</div>

<!-- FINE Finestra modale RICARICA -->


<!-- Finestra modale ELIMINA-->
<div class="modal fade" id="confermaEliminazioneModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Conferma eliminazione</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Sei sicuro di voler eliminare questa carta?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-danger conferma-eliminazione">Elimina</button>
      </div>
    </div>
  </div>
</div>

<!-- FINE Finestra modale ELIMINA -->


<!-- FOOTER -->
<jsp:include page="/WEB-INF/components/footer.jsp" />
<!-- FINE FOOTER -->

<script>
    $(document).ready(function() {
    	
        // Verifica se la finestra modale deve essere aperta all'avvio della pagina
        var urlParams = new URLSearchParams(window.location.search);
        var numeroCartaParam = urlParams.get('numeroCarta');
        if (numeroCartaParam) {
            // Se il parametro numeroCarta è presente nell'URL, apro la finestra modale
            $("#myModalModificaCarta").modal("show");
        }
    	
        $(".modifica-carta").click(function() {
        	
            // Recupero i dettagli della carta dal pulsante
            var numeroCarta = $(this).data("numero");
            var nomeCarta = $(this).data("nome");
            var prezzoCarta = $(this).data("prezzo");

            // Aggiorno i valori dei campi della finestra modale con i dettagli della carta
            $("#numeroCartaModifica").val(numeroCarta);
            $("#nomeCartaModifica").val(nomeCarta);
            $("#prezzoCartaModifica").val(prezzoCarta);

            // Mostro la finestra modale
            $("#myModalModificaCarta").modal("show");
        });

        $(".ricarica-carta").click(function() {
            // Recupero il numero della carta dal pulsante
            var numeroCarta = $(this).data("numero");

            // Imposto il valore del numero della carta nel campo nascosto del form
            $("#numeroCarta").val(numeroCarta);
        });

        // Gestisco la conferma di modifica direttamente con una richiesta POST
        $(".conferma-modifica").click(function() {
        	
            // Recupero i dati dalla finestra modale
            var numeroCarta = $("#numeroCartaModifica").val();
            var nomeCarta = $("#nomeCartaModifica").val();
            var prezzoCarta = $("#prezzoCartaModifica").val();

            // Esegue la richiesta POST per modificare la carta
            $.post("ModificaCarta", {  
                numeroCarta: numeroCarta,
                nomeCarta: nomeCarta,
                prezzoCarta: prezzoCarta,
            }, function() {
                // Ricarica la pagina per visualizzare le modifiche
                location.reload(true);
            });

            // Chiudo la finestra modale
            $("#myModalModificaCarta").modal("hide");
        });
        
        $(".elimina-carta").click(function() {
            // Recupero il numero della carta dal pulsante
            var numeroCarta = $(this).data("numero");
            // Imposto il numero della carta nella finestra modale
            $("#confermaEliminazioneModal").data("numeroCarta", numeroCarta);
            // Mostro la finestra modale
            $("#confermaEliminazioneModal").modal("show");
        });

        // Gestisco la conferma di eliminazione
        $(".conferma-eliminazione").click(function() {
            // Recupero il numero della carta dalla finestra modale
            var numeroCarta = $("#confermaEliminazioneModal").data("numeroCarta");
            // Esegue la richiesta POST per eliminare la carta
            $.post("EliminaCarta", { numeroCarta: numeroCarta }, function() {
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
