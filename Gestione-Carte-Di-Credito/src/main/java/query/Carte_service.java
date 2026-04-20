package query;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.naming.NamingException;

import database.Database;
import model.Carte;
import model.Circuiti;

public class Carte_service {

	// METODO PER OTTENERE LA CARTA

	public static Carte ottieniCarte() throws NamingException, SQLException {

		Carte carta = null;
		Carte carte = new Carte();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carte ;";
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();

			while (result.next()) {
				carta = new Carte(result.getInt("id_carte"), result.getString("nome_carta"),
						result.getString("num_carta"), result.getInt("cvv"), result.getDouble("prezzo"),
						result.getInt("quantità"), result.getInt("circuito_id"));
				carte.setLista(carta);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Chiudo tutte le risorse in un blocco finally
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

		return carte;

	}

	// GENERA UN NUMERO CASUALE TRA MIN E MAX INCLUSI

	private static int generateRandomCVV(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	// METODO PER AGGIUNGERE CARTE

	public static void aggiungiCarte(String nome_carta, double prezzo, int quantità)
			throws NamingException, SQLException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();

			// Determina il circuito in base al nome della carta
			int circuito_id = 0;
			if (isMastercard(nome_carta)) {
				circuito_id = 1; // Imposta il circuito_id per Mastercard
			} else if (isVisa(nome_carta)) {
				circuito_id = 2; // Imposta il circuito_id per Visa
			}

			// Aggiungi una nuova carta per ogni quantità specificata
			for (int i = 0; i < quantità; i++) {
				int cvv = generateRandomCVV(100, 999); // Genera un CVV casuale di 3 cifre

				// Genera un nuovo numero di carta casuale
				String numero_carta = generateRandomCardNumber(circuito_id); // Funzione da implementare

				// Inserisci la carta nel database
				String sql = "INSERT INTO carte (`nome_carta`, `num_carta`,`cvv`, `prezzo`, `quantità`, `circuito_id`) VALUES (?, ?, ?, ?, ?, ?);";
				statement = connection.prepareStatement(sql);
				statement.setString(1, nome_carta);
				statement.setString(2, numero_carta);
				statement.setInt(3, cvv);
				statement.setDouble(4, prezzo);
				statement.setInt(5, 1); // Quantità sempre 1 per ogni carta
				statement.setInt(6, circuito_id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore SQL durante l'aggiunta della carta: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore durante l'aggiunta della carta: " + e.getMessage());
		} finally {
			// Chiudi la connessione e lo statement nel blocco finally per garantire il
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

	// METODO PER DETERMINARE SE UNA CARTA APPARTIENE AL CIRCUITO MASTERCARD

	public static boolean isMastercard(String nome_carta) {
		List<String> carteMastercard = Arrays.asList("PostePay", "Tinaba", "Hype", "Banco BPM", "ING",
				"Banca Mediolanum");
		return carteMastercard.contains(nome_carta);
	}

	// METODO PER DETERMINARE SE UNA CARTA APPARTIENE AL CIRCUITO VISA

	public static boolean isVisa(String nome_carta) {
		List<String> carteVisa = Arrays.asList("Fineco", "Revolut", "Wallester", "Wirex", "Sella");
		return carteVisa.contains(nome_carta);
	}

	// METODO PER VERIFICARE SE IL NOME DELLA CARTA APPARTIENE A UNO DEI CIRCUITI SUPPORTATI

	public static boolean isCartaSupportata(String nome_carta) {
	    List<String> carteMastercard = Arrays.asList("PostePay", "Tinaba", "Hype", "Banco BPM", "ING", "Banca Mediolanum");
	    List<String> carteVisa = Arrays.asList("Fineco", "Revolut", "Wallester", "Wirex", "Sella");

	    boolean isMastercard = carteMastercard.contains(nome_carta);
	    boolean isVisa = carteVisa.contains(nome_carta);

	    return isMastercard || isVisa;
	}


	// METODO PER GENERARE UN NUMERO CASUALE

	public static String generateRandomCardNumber(int circuito_id) {
		Random random = new Random();
		String numero_carta = "";

		// Determina il prefisso iniziale in base al circuito_id
		String prefisso;
		if (circuito_id == 1) { // Mastercard
			prefisso = "5";
		} else if (circuito_id == 2) { // Visa
			prefisso = "4";
		} else {
			// Gestire altri circuiti se necessario
			prefisso = "0"; // Prefisso predefinito
		}
		numero_carta += prefisso;

		// Completa il numero di carta con cifre casuali
		for (int i = 0; i < 15; i++) { // 15 cifre aggiuntive dopo il prefisso
			numero_carta += random.nextInt(10); // Aggiungi cifre casuali
		}

		return numero_carta;
	}

	// METODO PER ELIMINARE CARTE

	public static void eliminaCarte(int id_carta) throws NamingException, SQLException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();
			String sql = "DELETE FROM carte WHERE id_carte=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_carta);
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

	// METODO PER VERIFICARE L'ESISTENZA DELLA CARTA

	public static boolean checkCarta(String numero) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carte WHERE num_carta=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero);
			result = statement.executeQuery();

			while (result.next()) {
				// Se il ResultSet contiene almeno una riga, la carta esiste
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

	// METODO PER OTTENERE IL TIPO DI CIRCUITO

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

	
	// METODO PER OTTENERE LA QUANTITà DISPONIBILE DELLE CARTE

	public static int getQuantità(String numero_carta) throws SQLException, NamingException {

		int quantitaDisponibile = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carte WHERE num_carta=? ;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			result = statement.executeQuery();

			while (result.next()) {

				quantitaDisponibile = result.getInt("quantità");
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

		return quantitaDisponibile;

	}

	// METODO PER AGGIORNARE LA QUANTITà

	public static void aggiornaQuantita(String numero_carta, int nuova_quantità) throws SQLException, NamingException {

		// Controllo se i parametri sono nulli o vuoti
		if (numero_carta == null || numero_carta.isEmpty() || nuova_quantità <= 0) {

			throw new IllegalArgumentException("Parametri non validi per aggiornare la quantità della carta");
		}

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();
			String sql = "UPDATE carte SET quantità = ? WHERE num_carta= ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, nuova_quantità);
			statement.setString(2, numero_carta);
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

	// METODO PER OTTENERE LA CARTA TRAMITE IL NUMERO

	public static Carte ottieniCartaByNumero(String numero_carta) throws NamingException, SQLException {

		Carte carta = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carte WHERE num_carta=? ;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
			result = statement.executeQuery();

			while (result.next()) {
				carta = new Carte(result.getInt("id_carte"), result.getString("nome_carta"),
						result.getString("num_carta"), result.getInt("cvv"), result.getDouble("prezzo"),
						result.getInt("quantità"), result.getInt("circuito_id"));
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

		return carta;

	}

	// METODO PER ELIMINARE UNA CARTA

	public static void eliminaCarta(String num_carta) throws NamingException, SQLException, NoSuchAlgorithmException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();
			String sql = "DELETE FROM carte WHERE num_carta=?;";
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

	// METODO PER AGGIORNARE LE CARTE

	public static void aggiornaCarte(String numeroCarta, String nomeCartaModifica, double prezzoCartaModifica) throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();
			String sql = "UPDATE carte SET nome_carta = ?, prezzo = ? WHERE num_carta = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomeCartaModifica);
			statement.setDouble(2, prezzoCartaModifica);
			statement.setString(3, numeroCarta);
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

	// Metodo per ottenere la carta acquistata
	public static void ottieniCartaAcquistata(String nomeCarta, String numeroCarta, int cvv, double prezzo,
			int quantita, int idUtente) throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = Database.execute();
			// Query SQL per inserire l'acquisto nella tabella acquisti
			String sql = "INSERT INTO acquisti (nome_carta, numero_carta, cvv, prezzo, quantità, utente_id) VALUES (?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomeCarta);
			statement.setString(2, numeroCarta);
			statement.setInt(3, cvv);
			statement.setDouble(4, prezzo);
			statement.setInt(5, quantita);
			statement.setInt(6, idUtente);
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

	// MWTODO PER INSERIRE UNA CARTA ACQUISTATA NELLA TABELLA CARTEUTENTI

	public static void inserisciCartaAcquistata(String nomeCarta, String numeroCarta, int cvv, int idUtente,
			int circuitoId) throws SQLException, NamingException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			// Query SQL per inserire la carta acquistata nella tabella carteutenti
			String sql = "INSERT INTO carteutenti (nome_carta, num_carta, cvv, stato, utenti_id, circuito_id, credito) VALUES (?, ?, ?, 'sbloccata', ?, ?, 0)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomeCarta);
			statement.setString(2, numeroCarta);
			statement.setInt(3, cvv);
			statement.setInt(4, idUtente);
			statement.setInt(5, circuitoId);
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

	// METODO PER VERIFICARE L'ESISTENZA DEL CVV

	public static boolean checkCvv(int cvv) throws NamingException, SQLException {

		boolean esiste = false;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = Database.execute();
			String sql = "SELECT * FROM carte WHERE cvv=?;";
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

	// METODO PER ELIMINARE LA CARTA DOPO L'ACQUISTO

	public static void eliminaCartaDopoAcquisto(String numero_carta) throws SQLException, NamingException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = Database.execute();
			String sql = "DELETE FROM carte WHERE num_carta=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, numero_carta);
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

}
