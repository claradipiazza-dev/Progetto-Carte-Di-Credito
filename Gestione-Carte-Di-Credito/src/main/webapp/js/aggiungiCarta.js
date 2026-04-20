$(document).ready(function() {

	$.validator.addMethod("card_regex", function(value, element) {
		return this.optional(element) || /^\d{16}$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formAggiungiCarte").validate(
		{
			rules: {
				'nomecarta': {
					required: true
				},
				'card': {
					required: true,
					card_regex: true
				},
				'prezzo': {
					required: true
				},
				'quantità': {
					required: true
				},
			},
			messages:
			{
				'nomecarta': {
					required: "Il nome della carta è obbligatorio"
				},
				'card': {
					required: "Il numero della carta è obbligatorio",
					card_regex: "Il numero della carta deve essere di 16 cifre"
				},
				'prezzo': {
					required: "Il prezzo è obbligatorio"
				},
				'quantità': {
					required: "La quantità è obbligatoria"
				},
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "AggiungiCarta",
					data: $(form).serialize() + "&operazione=aggiungi",
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#error_message").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								$("html").html(response);
							}
						}
						else {
							console.log("Errore");
							alert("Errore nella richiesta AJAX per il completamento della registrazione");
						}

					}
				});
			}

		});
});
