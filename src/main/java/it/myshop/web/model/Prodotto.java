package it.myshop.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prodotto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome")
	private String nome;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "prezzo_unitario")
	private int prezzoUnitario;
	@Column(name = "unita_di_misura")
	private String unitaDiMisura;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(int prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}
	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}
	
	
	
}
