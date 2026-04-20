package model;

import java.util.LinkedList;
import java.util.List;


public class Acquisti {
	
	private int id_acquisti;
	private String nome_carta;
	private String numero_carta;
	private int cvv;
	private double prezzo;
	private int quantità;
	private int utenti_id;
	private List<Acquisti> acquisti;
	
	public Acquisti()
	{
		super();
		this.acquisti= new LinkedList<Acquisti>();
	}
	
	public Acquisti (int id_acquisti, String nome_carta, String numero_carta, int cvv, double prezzo, int quantità, int utenti_id)
	{
		this.id_acquisti= id_acquisti;
		this.nome_carta = nome_carta;
		this.numero_carta= numero_carta;
		this.cvv = cvv;
		this.prezzo = prezzo;
		this.quantità = quantità;
		this.utenti_id= utenti_id;	
		this.acquisti= new LinkedList<Acquisti>(); 
	}
	
	public Acquisti(List<Acquisti> acquisto) {
		this.acquisti = acquisto;
	}
		

/* ----- METODI SET --- */
	public void setId(int id_acquisti)
	{
		this.id_acquisti= id_acquisti;
	}
	
	public void setNome(String nome_carta)
	{
		this.nome_carta= nome_carta;
	}
	
	public void setNumero(String numero_carta)
	{
		this.numero_carta= numero_carta;
	}
	
	public void setCvv(int cvv)
	{
		this.cvv= cvv;
	}
	
	public void setPrezzo(double prezzo) {
		
		this.prezzo= prezzo;
	}
	
	public void setQuantità(int quantità) {
		
		this.quantità= quantità;
	}
	
	public void setUenteId(int utenti_id)
	{
		this.utenti_id= utenti_id;
	}
	
	public void setLista(Acquisti acquisto) {
		acquisti.add(acquisto);
	}
	
/* ----- FINE METODI SET --- */

	
/* ----- METODI GET --- */
	
	public int getId()
	{
		return id_acquisti;
	}
	
	public String getNome()
	{
		return nome_carta;
	}
	
	public String getNumero()
	{
		return numero_carta;
	}
	
	public int getCvv()
	{
		return cvv;
	}
	
	public double getPrezzo()
	{
		return prezzo;
	}
	
	public int getQuantità()
	{
		return quantità;
	}
	
	public int getUtenteid()
	{
		return utenti_id;
	}
		
	public List<Acquisti> getLista() {
		return this.acquisti;
	}

/* ----- FINE METODI GET --- */		


}
