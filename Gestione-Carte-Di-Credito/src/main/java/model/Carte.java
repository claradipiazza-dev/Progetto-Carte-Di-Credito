package model;

import java.util.LinkedList;
import java.util.List;


public class Carte {
	
	private int id;
	private String nome_carta;
	private String num_carta;
	private int cvv;
	private double prezzo;
	private int quantità;
	private int circuito_id;
	private List<Carte> carte;
	
	public Carte()
	{
		super();
		this.carte= new LinkedList<Carte>();
	}
	
	public Carte (int id, String nome_carta, String num_carta, int cvv, double prezzo, int quantità, int circuito_id)
	{
		this.id= id;
		this.nome_carta = nome_carta;
		this.num_carta= num_carta;
		this.cvv = cvv;
		this.prezzo = prezzo;
		this.quantità = quantità;
		this.circuito_id= circuito_id;	
		this.carte= new LinkedList<Carte>(); 
	}
	
	public Carte(List<Carte> carta) {
		this.carte = carta;
	}
		

/* ----- METODI SET --- */
	public void setId(int id)
	{
		this.id= id;
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
	
	public void setPrezzo(double prezzo) {
		
		this.prezzo= prezzo;
	}
	
	public void setQuantità(int quantità) {
		
		this.quantità= quantità;
	}
	
	public void setCircuitoId(int circuito_id)
	{
		this.circuito_id= circuito_id;
	}
	
	public void setLista(Carte carta) {
		carte.add(carta);
	}
	
/* ----- FINE METODI SET --- */

	
/* ----- METODI GET --- */
	
	public int getId()
	{
		return id;
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
	
	public double getPrezzo()
	{
		return prezzo;
	}
	
	public int getQuantità()
	{
		return quantità;
	}
	
	public int getCircuitoid()
	{
		return circuito_id;
	}
		
	public List<Carte> getLista() {
		return this.carte;
	}

/* ----- FINE METODI GET --- */		

}
