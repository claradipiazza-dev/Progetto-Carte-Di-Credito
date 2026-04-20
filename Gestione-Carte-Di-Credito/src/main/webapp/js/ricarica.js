$(document).ready(function() {

	$.validator.addMethod("card_regex", function(value, element) {
		return this.optional(element) || /^\d{16}$/.test(value);
	});

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formRicarica").validate(
		{
			rules: {
				'card': {
					required: true,
					card_regex: true
				},
				'importo': {
					required: true,
				},
			},
			messages:
			{
				'card': {
					required: "Il numero della carta è obbligatorio",
					card_regex: "Il numero della carta deve essere di 16 cifre"
				},
				'importo': {
					required: "L'importo è obbligatorio"
				},
			}
		});
});
