$(document).ready(function() {

	$.validator.addMethod("card_regex", function(value, element) {
		return this.optional(element) || /^\d{16}$/.test(value);
	});

	$.validator.addMethod("cvv_regex", function(value, element) {
		return this.optional(element) || /^\d{3}$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formCartaUtente").validate(
		{
			rules: {
				'nomecarta': {
					required: true
				},
				'card': {
					required: true,
					card_regex: true
				},
				'cvv': {
					required: true,
					cvv_regex: true
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
				'cvv': {
					required: "Il campo CVV è obbligatorio",
					cvv_regex: "Il CVV deve essere di 3 cifre"
				},
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "AggiungiCartaUtente",
					data: $(form).serialize() + "&operazione=aggiungi",
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#messaggio_errore").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								console.log(response);
								$('#myModalCarta').modal('hide');
								$("#divRadice").html(response);
								$("body").css("background", "url(img/sfondo2.jpg) no-repeat center fixed");
								$("body").css("background-size","cover");							
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
