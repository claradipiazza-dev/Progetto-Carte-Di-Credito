$(document).ready(function() {

	$.validator.addMethod("password_regex", function(value, element) {
		return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%&]?)(.{8,20})$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#Accesso").validate(
		{
			rules: {
				'email': {
					required: true,
					email: true
				},
				'password': {
					required: true,
					password_regex: true
				},
			},
			messages:
			{
				'email': {
					required: "Il campo email è obbligatorio!",
					email: "L'email deve essere nel formato: abc@domain.tld"
				},
				'password': {
					required: "Il campo password è obbligatorio",
					password_regex: "La password deve contenere 8 o più caratteri,almeno una maiuscola,una lettera minuscola e un carattere numerico"
				},
			},
	
		});
});

