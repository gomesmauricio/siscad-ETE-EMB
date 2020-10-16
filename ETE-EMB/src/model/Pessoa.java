package model;

import java.sql.Date;

public class Pessoa {
	
	private int id;
	private String nome;
	private String sexo;
	private String endereco;
	private String cargo;
	private Date dataNascimento;
	private Date dataCadastro;
	private int ativo;
	
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}	
	
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	
	public Pessoa(int id, String nome, String sexo, String endereco, String cargo, int ativo, Date dataNascimento, Date dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.endereco = endereco;
		this.cargo = cargo;
		this.ativo = ativo;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}
	
	
	public Pessoa() {
		
	}
	
	
	

}
