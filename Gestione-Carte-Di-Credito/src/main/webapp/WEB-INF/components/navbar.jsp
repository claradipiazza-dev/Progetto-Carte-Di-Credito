<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navbar</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/accedi.js"></script>
<script type="text/javascript" src="js/registrazione.js"></script>
<script type="text/javascript" src="js/recuperoPass.js"></script>
<script type="text/javascript" src="js/saldo.js"></script>

<style>
h1 {
	font-size: 50px;
	color: wheat;
	text-align: center;
	padding-right: 40%;
}

#navbar {
	color: black;
	opacity: 0.9;
}

.modal-title {
	color: red;
	font-size: 30px;
}

.form-group {
	position: relative;
	font-size: 16px;
	text-align: center;
}

#login, #avanti {
	position: relative;
	left: 45%;
}

#register {
	position: relative;
	left: 43%;
}

#passwDimenticata {
	text-decoration-line: underline;
	text-decoration-color: blue;
}

#recuperaPass {
	position: relative;
	left: 39%;
}

#show {
	text-decoration: underline;
	text-decoration-color: blue;
	color: blue;
}

label.error {
	color: red;
}

.input-group {
	position: relative;
}

.btn-password-toggle {
	position: absolute;
	/* Posizionamento assoluto per posizionare il pulsante all'interno dell'input */
	top: 50%; /* Posiziona il pulsante verticalmente al centro */
	transform: translateY(-50%);
	/* Spostalo verso l'alto di metà della sua altezza per centrarlo verticalmente */
	right: 12px;
	/* Posiziona il pulsante 10px dalla destra del campo di input */
	background: transparent;
	border: none;
	color: #666;
	cursor: pointer;
}
</style>
</head>

<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top"
		id="navbar">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img src="img/iconacarta.png"
				alt="Logo" style="width: 40px;" class="rounded-pill"></a>
			<div class="container-fluid">
				<ul class="navbar-nav">
					<c:if
						test="${not empty sessionScope.utente and sessionScope.utente.ruolo eq 'utente'}">
						<li class="nav-item"><a class="nav-link" href="ProfiloUtente"><i
								class="fa fa-fw fa-user"></i><b>Profilo</b></a></li>
					</c:if>
					<c:choose>
						<c:when test="${empty sessionScope.utente}">
							<li class="nav-item"><button class="nav-link"
									data-bs-toggle="modal" data-bs-target="#myModalAccesso">
									<b>Accedi</b>
								</button></li>
							<li class="nav-item"><button class="nav-link"
									data-bs-toggle="modal" data-bs-target="#myModalRegistrazione">
									<b>Registrati</b>
								</button></li>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty sessionScope.utente}">
								<li class="nav-item"><a class="nav-link" href="Logout"
									style="position: absolute; left: 90%;"><b>Logout</b></a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
					<li class="nav-item"><button class="nav-link"
							data-bs-toggle="modal" data-bs-target="#myModalSaldo">
							<b>Saldo</b>
						</button></li>
				</ul>
			</div>
		</div>
		<h1>
			<b>WEBCARDS</b>
		</h1>
	</nav>
	<br>


	<!-- INIZIO Modal ACCESSO -->
	<div class="modal fade" id="myModalAccesso">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Accedi </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM ACCESSO-->
					<div id="contenitoreAccesso">
						<form name="formAccesso" id="Accesso" action="Accesso"
							method="post">
							<div class="form-group">
								<label for="email"><b> Email:* </b></label><br> <input
									type="email" class="form-control" id="email" name="email"
									placeholder="Inserire Email" required="required"
									autocomplete="on" />
							</div>
							<br>
							<div class="form-group">
								<label for="password"><b> Password:* </b></label><br>
								<div class="input-group">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="Inserire password"
										required="required" autocomplete="on" /><br>
								</div>
								<br><br> <label class="error" for="password"
									style="position: absolute; width: 100%; bottom: -5px; right: 0.5%;"></label>
							</div>
							<input type="checkbox"
									onclick="myFunction()"> Show Password
							<br><br> <a href="" data-bs-toggle="modal"
								data-bs-target="#myModalPassDimenticata" id="passDimenticata">Password
								dimenticata?</a>
							<p id="messaggi"></p>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="login" class="btn btn-success btn-lg">Accedi</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM ACCESSO  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal ACCESSO -->

	<!-- INIZIO Modal REGISTRAZIONE -->
	<div class="modal fade" id="myModalRegistrazione">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			id="registratiModal">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header" id="registratiModalLabel">
					<h4 class="modal-title">
						<b> Registrati </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM REGISTRAZIONE-->
					<div id="contenitoreRegistrazione">
						<form name="formRegistrazione" id="formRegistrazione"
							action="Registrazione" method="post">
							<div class="form-group">
								<label for="nome"><b> Nome:* </b></label><br> <input
									type="text" class="form-control" id="nome" name="nome"
									placeholder="Inserire Nome" required="required" />
							</div>
							<br>
							<div class="form-group">
								<label for="cognome"><b> Cognome:*</b></label><br> <input
									type="text" class="form-control" id="cognome" name="cognome"
									placeholder="Inserire Cognome" required="required" />
							</div>
							<br>
							<div class="form-group">
								<label for="numero"><b> Telefono:* </b></label><br> <input
									type="tel" class="form-control" id="numero" name="numero"
									placeholder="Inserire Telefono" required="required" />
							</div>
							<br>
							<div class="form-group">
								<label for="email2"><b> Email:* </b></label><br> <input
									type="email" class="form-control" id="email2" name="email"
									placeholder="Inserire Email" required="required"
									autocomplete="on" />
							</div>
							<br>
							<div class="form-group" id="Pass">
								<label for="pass1"><b>Password:*</b></label><br> <input
									type="password" class="form-control" id="pass1" name="pass1"
									placeholder="Inserire password" required="required"
									autocomplete="on" /><br> <input type="checkbox"
									onclick="myFunction2()"> Show Password
								<div class="form-group">
									<input type="hidden" name="operazione" value="registrati">
								</div>
								<p id="errore" style="color: red;"></p>
							</div>
							<br> <br>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="register"
								class="btn btn-success btn-lg">Registrati</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM REGISTRAZIONE  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal REGISTRAZIONE -->

	<div id="registrazioneModal"></div>

	<!-- INIZIO Modal SALDO -->
	<div class="modal fade" id="myModalSaldo">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Saldo </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM SALDO-->
					<div id="contenitoreSaldo">
						<form name="formSaldo" id="formSaldo" action="Credito"
							method="post">
							<div class="form-group">
								<label for="Card"><b> Numero della carta:* </b></label><br>
								<input type="text" class="form-control" id="Card"
									name="numerocarta" placeholder="Inserire il numero della carta"
									required="required">
							<p id="errore_messaggi" style="color: red;"></p>
							</div>
							<br>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="avanti" class="btn btn-success btn-lg">
								Avanti</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM SALDO  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal SALDO -->
	
	<div id="saldoModal"></div>

	<!-- INIZIO Modal PASSWORD DIMENTICATA -->
	<div class="modal fade" id="myModalPassDimenticata">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b> Recupera Password </b>
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!--INIZIO FORM RECUPERA PASSWORD-->
					<div id="contenitoreRecuperaPassword">
						<form name="formRecuperaPassword" id="formRecuperoPassword"
							action="RecuperoPassword" method="post">
							<div class="form-group">
								<label for="email3"><b> Email:* </b></label><br> <input
									type="email" class="form-control" id="email3" name="email3"
									placeholder="Inserire Email" required="required"
									autocomplete="on" />
								<div class="form-group">
									<input type="hidden" name="operazione" value="recupera">
								</div>
								<p id="messaggio" style="color: red;"></p>
							</div>
							<br>
							<!-- EVENTUALI MESSAGGI -->
							<button type="submit" id="recuperaPass" class="btn btn-success">
								Recupera Password</button>
							<br>
						</form>
					</div>
					<!-- FINE FORM RECUPERA PASSWORD  -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FINE Modal PASSWORD DIMENTICATA -->
	
	<br>
	<script>
		function myFunction() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
			// Impedisci l'evento di propagarsi
			event.stopPropagation();
		}

		function myFunction2() {
			var x = document.getElementById("pass1");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
			// Impedisci l'evento di propagarsi
			event.stopPropagation();
		}
	</script>

</body>
</html>