<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Registrazione completata </title>
<script type="text/javascript" src="js/accedi.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<style>

  /* Stile del contenitore principale */
  .container {
    color: green;
    text-align: center;
    padding: 3%; 
    font-size: 20px;
    border: 2px solid #ccc; /* Aggiunto bordo */
    border-radius: 10px; /* Angoli arrotondati */
    background-color: #f9f9f9; /* Sfondo */
  }

  /* Stile del titolo */
  #h2 {
    font-size: 50px;
    text-align: center;
    color: green
    font-family: Arial, sans-serif; /* Font */
    margin-bottom: 20px; /* Spazio inferiore */
  }

  /* Immagine di registrazione */
  #imgregistrazione {
    width: 100px; /* Dimensione */
    height: 100px; /* Dimensione */
    border: 3px solid green; /* Aggiunto bordo */
    border-radius: 40%; /* Forma circolare */
    box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Ombreggiatura */
    margin-bottom: 20px; /* Spazio inferiore */
  }
  
  /* Stile dei paragrafi */
  .container p {
    color: green; /* Colore del testo */
    font-size: 20px; /* Dimensione del testo */
  }
  
    .container p span {
    color: #4B0082; /* Colore del testo viola scuro */
  }
  
</style>

</head>
<body style="background: url(img/sfondo2.jpg) no-repeat center fixed; background-size: cover;">

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/components/navbar.jsp" /><br><br><br><br>
<!-- FINE NAVBAR -->

<div class="container">
<h2 id="h2"><b><img id="imgregistrazione" src="img/registrazione.jpg" alt="Immagine registrazione"> Registrazione completata! </b></h2>
<hr>
		<p><b> Nome: <span>${utente.nome}</span> </b></p>
		<p><b> Cognome: <span>${utente.cognome}</span></b></p>
		<p><b> Email: <span>${utente.email}</span></b></p>
		  <br>
		  <a type="button" role="button" class="btn btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#myModalAccesso"> Effettua il login </a>
	<br>
	</div>

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
								<label for="password"><b> Password:* </b></label><br> <input
									type="password" class="form-control" id="password"
									name="password" placeholder="Inserire password"
									required="required" autocomplete="on" /><br>
							</div>
							<input type="checkbox"
							onclick="myFunction()"> Show Password
							<br><br>
							<a href="" data-bs-toggle="modal"
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
	</script>

</body>
</html>