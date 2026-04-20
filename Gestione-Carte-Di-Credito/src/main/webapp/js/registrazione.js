$(document).ready(function() {

	$.validator.addMethod("tel", function(value, element) {
		return this.optional(element) || /^[0-9]{3}-[0-9]{7}$/i.test(value);
	});

	$.validator.addMethod("password_regex", function(value, element) {
		return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&]?)(.{8,20})$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formRegistrazione").validate(
		{
			rules: {
				'nome': {
					required: true,
					minlength: 2
				},
				'cognome': {
					required: true
				},
				'numero': {
					required: true,
				},
				'email': {
					required: true,
					email: true
				},
				'pass1': {
					required: true,
					password_regex: true
				}
			},
			messages:
			{
				'nome': {
					required: "Il campo nome è obbligatorio!",
					minlength: "Scegli un nome di almeno 3 lettere!"
				},
				'cognome': {
					required: "Il campo cognome è obbligatorio!"
				},
				'numero': {
					required: "Il campo telefono è obbligatorio!",
					tel: "Il telefono deve essere scritto nel formato 'xxx-yyyyyyy'"
				},
				'email': {
					required: "Il campo email è obbligatorio!",
					email: "L'email deve essere nel formato: abc@domain.tld"
				},
				'pass1': {
					required: "Il campo password è obbligatorio",
					password_regex: "La password deve contenere 8 o più caratteri,almeno una maiuscola,una lettera minuscola e un carattere numerico"
				},
			},
			submitHandler: function(form) {
				$.ajax({
					method: "POST",
					url: "Registrazione",
					data: $(form).serialize() + "&operazione=registrati",
					success: function(response, status, xhr) {
						if (status == "success") {
							console.log("Richiesta ajax effettuata con successo");
							// Mostra i messaggi di errore, se presenti
							var errorMessage = xhr.getResponseHeader("errorMessage");
							if (errorMessage) {
								$("#errore").html(errorMessage); // Aggiorna il contenuto della finestra modale con il messaggio di errore
							} else {
								$('#myModalRegistrazione').modal('hide');
								$("#registrazioneModal").html(response);
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
