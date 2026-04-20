$(document).ready(function() {
	$(".blocca-carta, .sblocca-carta").click(function(event) {
		// Impedisci il comportamento predefinito dei pulsanti di submit
		event.preventDefault();
		
		// Seleziona il form relativo al pulsante su cui hai fatto clic
        var form = $("#formGestisciCarte");

		$.ajax({
			method: "POST",
			url: "GestisciCarte",
			data: $(form).serialize(),
			success: function(response, status, xhr) {
				if (status == "success") {
					console.log("Richiesta ajax effettuata con successo");
					// Mostra i messaggi di errore, se presenti
					var errorMessage = xhr.getResponseHeader("errorMessage");
					if (errorMessage) {
						$("#messaggi").html(errorMessage); // Aggiorna il contenuto con il messaggio di errore
					} else {
						$("html").html(response);
					}
				}
				else {
					console.log("Errore");
					alert("Errore nella richiesta AJAX per la gestione carte");
				}

			}
		});
	});
});
