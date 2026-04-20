package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utenti;
import query.Utenti_service;

@WebServlet("/ModificaPassword")
public class ModificaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModificaPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String email = request.getParameter("emailPass");
			String passTemporanea = request.getParameter("passTemp");
			String nuovaPassword = request.getParameter("newPass");
			String operazione = request.getParameter("operazione");
			String address = "";
			response.setContentType("text/plain");
			boolean emailEsistente = Utenti_service.checkMailExistence(email);
			boolean passwordEsistente = Utenti_service.checkPassword(nuovaPassword);

			if (operazione.equals("modifica")) {
				if (emailEsistente) {
					String passwordTempHash = Utenti_service.passwordHash(passTemporanea);
					Utenti utente = Utenti_service.accesso(email, passwordTempHash);
					if (utente != null) {
						if (!nuovaPassword.equals(passTemporanea)) {
							if (!passwordEsistente) {
								utente.setPassword(Utenti_service.passwordHash(nuovaPassword));
								Utenti_service.aggiornaUtente(utente);
								address = "/WEB-INF/view/viewPassModificata.jsp";

								RequestDispatcher dispatcher = request.getRequestDispatcher(address);
								dispatcher.forward(request, response);
							} else {
								String message = "Devi utilizzare un'altra password in quanto già esistente!";
								response.setHeader("errorMessage", message);
								request.setAttribute("messaggio", message);
							}
						} else {
							String message = "La nuova password deve essere diversa dalla password temporanea!";
							response.setHeader("errorMessage", message);
							request.setAttribute("messaggio", message);
						}
					} else {
						String message = "La password temporanea non è corretta!";
						response.setHeader("errorMessage", message);
						request.setAttribute("messaggio", message);
					}
				} else {
					String message = "L'indirizzo email fornito non esiste nel nostro sistema!";
					response.setHeader("errorMessage", message);
					request.setAttribute("messaggio", message);
				}
			}

		} catch (NoSuchAlgorithmException | SQLException |

				NamingException e) {
			e.printStackTrace(); 
		}
	}

}
