package it.polito.tdp.corsi.model;

public class Studente {
	private String nome;
	private String cognome;
	private String matricola;
	private String cds;
	public Studente(String nome, String cognome, String matricola, String cds) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.cds = cds;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getCds() {
		return cds;
	}
	public void setCds(String cds) {
		this.cds = cds;
	}
	@Override
	public String toString() {
		return  nome +  " " + cognome + " " + matricola + " " + cds + " ";
	}
	

}
