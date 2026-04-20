$(document).ready(function() {

	$.validator.addMethod("password_regex", function(value, element) {
		return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&]?)(.{8,20})$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formPass").validate(
		{
			rules: {
				'emailPass': {
					required: true,
					email: true
				},
				'passTemp': {
					required: true,
					password_regex: true
				},
				'newPass': {
					required: true,
					password_regex: true
				},
			},
			messages:
			{
				'emailPass': {
					required: "Il campo email è obbligatorio!",
					email: "L'email deve essere nel formato: abc@domain.tld"
				},
				'passTemp': {
					required: "Il campo password è obbligatorio",
					password_regex: "La password deve contenere 8 o più caratteri,almeno una maiuscola,una lettera minuscola e un carattere numerico"
				},
				'newPass': {
					required: "Il campo password è obbligatorio",
					password_regex: "La password deve contenere 8 o più caratteri,almeno una maiuscola,una lettera minuscola e un carattere numerico"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "ModificaPassword",
					data: $(form).serialize() + "&operazione=modifica",
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#errori").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								console.log(response);
								$('#myModalCambioPass').modal('hide');
								$("#contenitore").hide();
								$(".modal-backdrop").hide();
								$("#contenutoPass").html(response);							
							}
						}
						else {
							console.log("Errore");
							alert("Errore nella richiesta AJAX per la modifica password");
						}
					}
				});
			}

		});
});

