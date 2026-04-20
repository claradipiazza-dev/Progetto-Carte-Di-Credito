$(document).ready(function() {

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formRecuperoPassword").validate(
		{
			rules: {
				'email': {
					required: true,
					email: true
				},
			},
			messages:
			{
				'email': {
					required: "Il campo email è obbligatorio!",
					email: "L'email deve essere nel formato: abc@domain.tld"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "RecuperoPassword",
					data: $(form).serialize() + "&operazione=recupera",
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#messaggio").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								console.log(response);
								$('#myModalAccesso').modal('hide');
								$('#myModalPassDimenticata').modal('hide');
								$("#contenutoRecuperoPass").html(response);
							}
						}
						else {
							console.log("Errore");
							alert("Errore nella richiesta AJAX per il recupero password");
						}
					}
				});
			}
		});
});