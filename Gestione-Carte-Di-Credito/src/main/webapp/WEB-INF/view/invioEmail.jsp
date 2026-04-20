<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recupero password</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/invioEmail.js"></script>
<style>

.container1 {
	background-color: black;
	opacity: 0.8;
	color: wheat;
	text-align: center;
	padding: 5%;
	width: 90%;
	margin: auto;
}

.testa {
	font-size: 35px;
}

label.error {
	color: red;
}

</style>
</head>

<body><br>
<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" />
<!-- FINE NAVBAR -->
<br>

	<div id="contenutoPass"></div>
	<div class="container1" id="contenitore">

		<h2 class="testa" id="header">
			<b> Email inviata correttamente! </b>
		</h2>
		<br>
		<h2 class="testa">
			<b> Puoi tornare all'Home page oppure cambiare password </b>
		</h2>
		<br> <a type="button" role="button" class="btn btn-danger btn-lg"
			href="index.jsp"> Torna all'Home </a> <a type="button" role="button"
			class="btn btn-danger btn-lg" data-bs-toggle="modal"
			data-bs-target="#myModalCambioPass" id="modificaPass"> Modifica password </a><br>
		<br>
	</div>
	
	<!-- INIZIO Modal MODIFICA PASSWORD -->
	<div class="modal fade" id="myModalCambioPass">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Modifica password </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>


				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM MODIFICA PASSWORD-->
					<div id="contenitorePass">
						<form name="formPass" id="formPass" action="ModificaPassword"
							method="post">
							<div class="form-group">
								<label for="emailPass"><b> Email:* </b></label><br> <input
									type="email" class="form-control" id="emailPass" name="emailPass"
									placeholder="Inserire l'email" required="required">
							</div>
							<br>
							<div class="form-group">
								<label for="passTemp"><b> Password temporanea:* </b></label><br>
								<div class="input-group">
									<input type="password" class="form-control" id="passTemp"
										name="passTemp" placeholder="Inserire la password"
										required="required" autocomplete="on"><br>
								</div>
							<input type="checkbox"
							onclick="myFunction()"> Show Password
							</div>
							<br>
							<div class="form-group">
								<label for="newPass"><b> Nuova password:* </b></label><br>
								<div class="input-group">
									<input type="password" class="form-control" id="newPass"
										name="newPass" placeholder="Inserire la nuova password"
										required="required" autocomplete="on"><br>
								<div class="form-group" ><input type="hidden" name="operazione" value="modifica"></div>
								</div>
								<input type="checkbox"
									onclick="myFunction2()"> Show Password
								<p id="errori" style="color: red;"></p>
							</div><br>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="avanti" class="btn btn-success btn-lg">
								Modifica</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM MODIFICA PASSWORD  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal MODIFICA PASSWORD -->

	<script>
	function myFunction() {
		var x = document.getElementById("passTemp");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
		// Impedisce l'evento di propagarsi
		event.stopPropagation();
	}

	function myFunction2() {
		var x = document.getElementById("newPass");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
		// Impedisce l'evento di propagarsi
		event.stopPropagation();
	}

	</script>

</body>
</html>