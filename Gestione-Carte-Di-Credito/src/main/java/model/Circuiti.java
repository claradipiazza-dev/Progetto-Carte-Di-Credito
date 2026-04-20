package model;

import java.util.LinkedList;
import java.util.List;

public class Circuiti {

	private int id_circuito;
	private String nome;
	
	private List<Circuiti> circuito;
	
	
	public Circuiti()
	{
		super();
		this.circuito= new LinkedList<Circuiti>();
	}
	
	
	public Circuiti (int id_circuito, String nome) {
		
		this.id_circuito = id_circuito;
		this.nome = nome;
		
	}
	
	/* ----- METODI SET --- */
	
	public void setId(int id_circuito)
	{
		this.id_circuito= id_circuito;
	}
	
	public void setNome(String nome)
	{
		this.nome= nome;
	}
	
	
	public void setLista(Circuiti circuiti) {
		circuito.add(circuiti);
	}
	
	
	/* ----- METODI GET --- */
	
	public int getId()
	{
		return id_circuito;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	
	public List<Circuiti> getLista() {
		return this.circuito;
	}
	
}
