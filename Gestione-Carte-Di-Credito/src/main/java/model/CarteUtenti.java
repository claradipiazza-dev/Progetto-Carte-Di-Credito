package model;

import java.util.LinkedList;
import java.util.List;

public class CarteUtenti {

	private int id_carteutenti;
	String nome_carta;
	private String num_carta;
	private int cvv;
	private String stato_carta = "sbloccata"; //la carta è sbloccata
	private double credito;
	private int userid;
	private int circuitoid;
	private List<CarteUtenti> carte;
	
	public CarteUtenti()
	{
		//super();
		this.carte= new LinkedList<CarteUtenti>();
	}
	
	public CarteUtenti (int id_carteutenti, String nome_carta, String num_carta, int cvv, String stato, double credito, int userid, int circuitoid)
	{
		this.id_carteutenti= id_carteutenti;
		this.nome_carta = nome_carta;
		this.num_carta= num_carta;
		this.cvv= cvv;
		this.stato_carta = stato;
		this.userid= userid;
		this.circuitoid= circuitoid;
		this.credito=credito;
		this.carte= new LinkedList<CarteUtenti>();
	}
			
	public CarteUtenti(List<CarteUtenti> carte) {
		this.carte = carte;
	}

/* ----- METODI SET --- */
	public void setId(int id_carteutenti)
	{
		this.id_carteutenti= id_carteutenti;
	}
	
	public void setNome(String nome_carta)
	{
		this.nome_carta= nome_carta;
	}
	
	public void setNumero(String num_carta)
	{
		this.num_carta= num_carta;
	}

	public void setCvv(int cvv)
	{
		this.cvv= cvv;
	}
	
	public void setCredito(double credito)
	{
		this.credito=credito;
	}
	
	public void setStato(String stato_carta)
	{
		this.stato_carta= stato_carta;
	}
		
	public void setUserid(int userid)
	{
		this.userid= userid;
	}
	
	public void setCircuitoId(int circuitoid)
	{
		this.circuitoid= circuitoid;
	}
	
	public void setLista(CarteUtenti carta) {
		carte.add(carta);
	}
	
/* ----- FINE METODI SET --- */

	
/* ----- METODI GET --- */
	
	public int getId()
	{
		return id_carteutenti;
	}
	
	public String getNome()
	{
		return nome_carta;
	}
	
	public String getNumero()
	{
		return num_carta;
	}
	
	public int getCvv()
	{
		return cvv;
	}	
	
	public double getCredito()
	{
		return credito;
	}
	
	public String getStato()
	{
		return stato_carta;
	}
		
	public int getUserid()
	{
		return userid;
	}
	
	public int getCircuitoId()
	{
		return circuitoid;
	}
		
	public List<CarteUtenti> getLista() {
		return this.carte;
	}

/* ----- FINE METODI GET --- */		
	
	
	
	
}
