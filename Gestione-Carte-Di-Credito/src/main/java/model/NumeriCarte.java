package model;

import java.util.LinkedList;
import java.util.List;

public class NumeriCarte {

	private int id;
	private String num_carta;
	private double credito;
	private int circuito_id;
	private List<NumeriCarte> carte;
	
	public NumeriCarte()
	{
		super();
		this.carte= new LinkedList<NumeriCarte>();
	}
	
	public NumeriCarte (int id, String num_carta, int credito, int circuito_id)
	{
		this.id= id;
		this.num_carta= num_carta;
		this.credito= credito;
		this.circuito_id= circuito_id;	
		this.carte= new LinkedList<NumeriCarte>(); 
	}
	
	public NumeriCarte(List<NumeriCarte> carta) {
		this.carte = carta;
	}
		

/* ----- METODI SET --- */
	public void setId(int id)
	{
		this.id= id;
	}
		
	public void setNumero(String num_carta)
	{
		this.num_carta= num_carta;
	}
		
	public void setCredito(double credito)
	{
		this.credito=credito;
	}
	
	public void setCircuitoId(int circuito_id)
	{
		this.circuito_id= circuito_id;
	}
	
	public void setLista(NumeriCarte carta) {
		carte.add(carta);
	}
	
/* ----- FINE METODI SET --- */

	
/* ----- METODI GET --- */
	
	public int getId()
	{
		return id;
	}
		
	public String getNumero()
	{
		return num_carta;
	}
	
	public double getCredito()
	{
		return credito;
	}
		
	public int getCircuitoid()
	{
		return circuito_id;
	}
		
	public List<NumeriCarte> getLista() {
		return this.carte;
	}

/* ----- FINE METODI GET --- */		

	
}
