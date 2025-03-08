package br.com.marcielli.bancodigital.entity;

import java.time.LocalDate;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;

public class ClienteEntity { //IDÉIA DO CLIENTE
	
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private CategoriasDeConta categoriaDeConta;
	
	public ClienteEntity(String cpf, String nome, LocalDate dataNascimentoDATE, Endereco endereco, CategoriasDeConta categoriaDeConta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimentoDATE;
		this.endereco = endereco;
		this.categoriaDeConta = categoriaDeConta;
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
	
	public CategoriasDeConta getCategoriaContaCliente() {
		return categoriaDeConta;
	}
	
	

	@Override
	public String toString() {		
		
		return "NOME: " + nome + ", CPF: "+cpf+", DATA DE NASCIMENTO: " + dataNascimento + ", CATEGORIA DE CONTA: "+getCategoriaContaCliente()+"\nENDEREÇO "
				+ endereco;
	}
}
