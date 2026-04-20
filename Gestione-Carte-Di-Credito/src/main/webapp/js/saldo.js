$(document).ready(function() {

	$.validator.addMethod("card_regex", function(value, element) {
		return this.optional(element) || /^\d{16}$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formSaldo").validate(
		{
			rules: {
				'numerocarta': {
					required: true,
					card_regex: true
				},
			},
			messages:
			{
				'numerocarta': {
					required: "Il campo Numero della carta è obbligatorio!",
					card_regex: "Il numero della carta deve essere di 16 cifre"
				},
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "Credito",
					data: $(form).serialize(),
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#errore_messaggi").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								$('#myModalSaldo').modal('hide');
								$("#saldoModal").html(response);
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

