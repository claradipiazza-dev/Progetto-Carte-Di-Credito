$(document).ready(function() {

    $.validator.addMethod("cvv_regex", function(value, element) {
        return this.optional(element) || /^\d{3}$/.test(value);
    });

	$.validator.addMethod("submit", function(value, element) {
		return this.optional(element) || /[^ ]/i.test(value);
	});

	$("#formPagamento").validate(
		{
			rules: {
				'cvv': {
					required: true,
					cvv_regex: true
				},
			},
			messages: {
				'cvv': {
					required: "Il campo CVV è obbligatorio",
					cvv_regex: "Il CVV deve essere di 3 cifre"
				},
			}
		});
});
