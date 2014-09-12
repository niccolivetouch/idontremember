package com.br.nicco.model;


//@DatabaseTable(tableName="contato")
public class Contato {
	
//	@DatabaseField(generatedId=true)
	private int id;
	
//	@DatabaseField
	private String nome;
	
//	@DatabaseField
	private String telefone;
	
	public int getId() {
		return id;
	}
	public void setId(long l) {
		this.id = (int) l;
	}
	public Contato(){}
	public Contato(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
