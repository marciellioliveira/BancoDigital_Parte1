package br.com.marcielli.bancodigital.entity;

import java.time.LocalDate;

public class ClienteEntity { //IDÉIA DO CLIENTE
	
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private Endereco endereco;
	
	public ClienteEntity(String cpf, String nome, LocalDate dataNascimentoDATE, Endereco endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimentoDATE;
		this.endereco = endereco;
	}
	
	public ClienteEntity() {}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	

	@Override
	public String toString() {		
		
		return "CPF: " + cpf + ", NOME: " + nome + ", DATA DE NASCIMENTO: " + dataNascimento + "\nENDEREÇO "
				+ endereco;
	}
}
