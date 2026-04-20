package query;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import database.Database;
import model.CarteUtenti;
import model.Circuiti;

public class CarteUtenti_service {

	// METODO PER OTTENETE LE CARTE DELL'UTENTE

	public static CarteUtenti ottieniCarteUtenti(String num_carta, int id_utente) {

		CarteUtenti carta = null;
		CarteUtenti carte = new CarteUtenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE num_carta=? and utenti_id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, num_carta);
			statement.setInt(2, id_utente);
			rs = statement.executeQuery();

			while (rs.next()) {
				carta = new CarteUtenti(rs.getInt("id_carteutenti"), rs.getString("nome_carta"),
						rs.getString("num_carta"), rs.getInt("cvv"), rs.getString("stato"), rs.getDouble("credito"),
						rs.getInt("utenti_id"), rs.getInt("circuito_id"));
				carte.setLista(carta);
			}

		} catch (SQLException | NamingException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il result set nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carte;

	}

	// METODO PER LA GESTIONE DELLE CARTE(BLOCCO\SBLOCCO)

	public static void gestisciCarta(String num_carta, String azione)
			throws NamingException, SQLException, NoSuchAlgorithmException {

		String stato = (azione.equals("blocca")) ? "bloccata" : "sbloccata";
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "UPDATE carteutenti SET stato=? WHERE num_carta=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, stato);
			statement.setString(2, num_carta);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per garantire il
			// rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// METODO PER OTTENERE LO STATO DELLA CARTA

	public static String stato(String num_carta) {

		String stato = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT stato FROM carteutenti WHERE num_carta=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, num_carta);
			rs = statement.executeQuery();
			if (rs.next()) {
				stato = rs.getString("stato");
			}

		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il result set nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return stato;
	}

	// METODO PER VERIFICARE L'ESISTENZA DELLA CARTA

	public static boolean checkCarta(String numero) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE num_carta=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			result = statement.executeQuery();

			while (result.next()) {

				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il result set nel blocco finally per
			// garantire il rilascio delle risorse
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER AGGIUNGERE UNA CARTA

	public static void aggiungiCarta(String nome_carta, String num_carta, int cvv, int utenti_id, int circuito_id)
			throws NamingException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			double credito = 0.0;
			String stato = "sbloccata";
			String sql = "INSERT INTO carteutenti (`nome_carta`, `num_carta`, `cvv`, `stato`, `credito`, `utenti_id`, `circuito_id`) VALUES (?, ?, ?, ?, ?, ?, ?);";
			statement = connection.prepareStatement(sql);
			statement.setString(1, nome_carta);
			statement.setString(2, num_carta);
			statement.setInt(3, cvv);
			statement.setString(4, stato);
			statement.setDouble(5, credito);
			statement.setInt(6, utenti_id);
			statement.setInt(7, circuito_id);
			statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per garantire il
			// rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// METODO PER STABILIRE IL TIPO DI CIRCUITO

	public static Circuiti getTipoCircuito(String numero_carta) {

		Circuiti tipoCircuito = null;

		try {
			// Controlla se il numero della carta inizia con "4" per Visa o "5" per
			// Mastercard
			if (numero_carta.startsWith("4")) {

				tipoCircuito = new Circuiti(2, "Visa"); // Visa

			} else if (numero_carta.startsWith("5")) {

				tipoCircuito = new Circuiti(1, "Mastercard"); // Mastercard
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipoCircuito;
	}

	// RECUPERA LE CARTE DELL'UTENTE TRAMITE L'ID

	public static CarteUtenti ottieniCarte(int id_utenti) {

		CarteUtenti carta = null;
		CarteUtenti carte = new CarteUtenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE utenti_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_utenti);
			rs = statement.executeQuery();

			while (rs.next()) {
				carta = new CarteUtenti(rs.getInt("id_carteutenti"), rs.getString("nome_carta"),
						rs.getString("num_carta"), rs.getInt("cvv"), rs.getString("stato"), rs.getDouble("credito"),
						rs.getInt("utenti_id"), rs.getInt("circuito_id"));
				carte.setLista(carta);
			}

		} catch (SQLException | NamingException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carte;

	}

	// RECUPERA LE CARTE DELL'UTENTE TRAMITE IL NUMERO DELLA CARTA

	public static CarteUtenti ottieniCarta(String numero_carta) {

		CarteUtenti carta = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE num_carta=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			rs = statement.executeQuery();

			while (rs.next()) {
				carta = new CarteUtenti(rs.getInt("id_carteutenti"), rs.getString("nome_carta"),
						rs.getString("num_carta"), rs.getInt("cvv"), rs.getString("stato"), rs.getDouble("credito"),
						rs.getInt("utenti_id"), rs.getInt("circuito_id"));
			}

		} catch (SQLException | NamingException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carta;

	}

	// METODO PER OTTENERE IL CREDITO

	public static CarteUtenti ottieniCreditoUtente(int id_utente, String numero_carta)
			throws SQLException, NamingException, NoSuchAlgorithmException {

		// Controllo se i parametri sono nulli o vuoti
		if (id_utente <= 0 || numero_carta == null || numero_carta.isEmpty()) {

			throw new IllegalArgumentException("Parametri non validi per ottenere il credito dell'utente");
		}

		CarteUtenti carte = new CarteUtenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT credito FROM carteutenti WHERE utenti_id=? AND num_carta=? ;";
			connection = Database.execute();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_utente);
			statement.setString(2, numero_carta);
			rs = statement.executeQuery();

			if (rs.next()) {
				carte.setCredito(rs.getDouble("credito"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudi la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carte;
	}

	// METODO PER DIMINUIRE IL CREDITO
	public static void diminuisciCreditoUtente(String numero_carta, double prezzo_prodotto, int quantita, int cvv)
			throws SQLException, NamingException {

		// Controllo se i parametri sono nulli o vuoti
		if (numero_carta == null || numero_carta.isEmpty() || prezzo_prodotto <= 0 || quantita <= 0) {
			throw new IllegalArgumentException("Parametri non validi per diminuire il credito dell'utente");
		}

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			// Calcola l'importo totale basato sulla quantità e sul prezzo del prodotto
			double importoTotale = prezzo_prodotto * quantita;
			// Aggiorna il credito con l'importo totale
			String sql = "UPDATE carteutenti SET credito = credito - ? WHERE num_carta=? AND cvv=?;";
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, importoTotale);
			statement.setString(2, numero_carta);
			statement.setInt(3, cvv);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per garantire il
			// rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}



	// METODO PER VERIFICARE L'ESISTENZA DELLA CARTA CON IL CVV

	public static boolean checkCartaCvv(String numero, int cvv) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE num_carta=? AND cvv=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			statement.setInt(2, cvv);
			result = statement.executeQuery();

			while (result.next()) {

				esiste = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER ELIMINARE UNA CARTA

	public static void eliminaCarta(String num_carta) throws NamingException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "DELETE FROM carteutenti WHERE num_carta=?; ";
			statement = connection.prepareStatement(sql);
			statement.setString(1, num_carta);
			statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione e lo statement nel blocco finally per
			// garantire il rilascio delle risorse
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// METODO PER OTTENERE LE CARTE

	public static CarteUtenti ottieniCarte() throws NamingException, SQLException {

		CarteUtenti carta = null;
		CarteUtenti carte = new CarteUtenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti ;";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				carta = new CarteUtenti(rs.getInt("id_carteutenti"), rs.getString("nome_carta"),
						rs.getString("num_carta"), rs.getInt("cvv"), rs.getString("stato"), rs.getDouble("credito"),
						rs.getInt("utenti_id"), rs.getInt("circuito_id"));
				carte.setLista(carta);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carte;

	}

	// METODO PER VERIFICARE L'ESISTENZA DEL CVV

	public static boolean checkCvv(int cvv) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = Database.execute();
			String sql = "SELECT * FROM carteutenti WHERE cvv=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cvv);
			result = statement.executeQuery();

			while (result.next()) {

				esiste = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return esiste; // falso se non esiste, vero se esiste
	}

	// METODO PER OTTENERE IL SALDO

	public static CarteUtenti getCreditoCarta(String numero_carta)
			throws SQLException, NamingException, NoSuchAlgorithmException {

		CarteUtenti carte = new CarteUtenti();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT credito FROM carteutenti WHERE num_carta=? ;";
			connection = Database.execute();
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			rs = statement.executeQuery();

			if (rs.next()) {

				carte.setCredito(rs.getDouble("credito"));
			}

			else {
				System.out.println("Nessuna carta trovata: ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo la connessione, lo statement e il ResultSet nel blocco finally per
			// garantire il rilascio delle risorse
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return carte;
	}

}
