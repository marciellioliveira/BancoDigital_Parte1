package br.com.marcielli.bancodigital.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimeZone;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;

public class ClienteEntity { //IDÉIA DO CLIENTE
	
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private CategoriasDeConta categoriaDeConta;

	private ContaCorrenteEntity contaCorrente;
	private ContaPoupancaEntity contaPoupanca;
	private CartaoDeCreditoEntity cartaoDeCredito;
	private CartaoDeDebitoEntity cartaoDeDebito;
	
	
	public ClienteEntity(String cpf, String nome, LocalDate dataNascimentoDATE, Endereco endereco, CategoriasDeConta categoriaDeConta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimentoDATE;
		this.endereco = endereco;
		this.categoriaDeConta = categoriaDeConta;		
	}
	
	public ClienteEntity(ContaCorrenteEntity contaCorrente) {
		super();
		this.contaCorrente = contaCorrente;
	}
	
	public ClienteEntity(ContaPoupancaEntity contaPoupanca) {
		super();
		this.contaPoupanca = contaPoupanca;
	}

	public ClienteEntity(CartaoDeCreditoEntity cartaoDeCredito) {
		super();
		this.cartaoDeCredito = cartaoDeCredito;
	}	

	public ClienteEntity(CartaoDeDebitoEntity cartaoDeDebito) {
		super();
		this.cartaoDeDebito = cartaoDeDebito;
	}

	public ClienteEntity() {}	
	
	
	public ContaCorrenteEntity getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrenteEntity contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public ContaPoupancaEntity getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(ContaPoupancaEntity contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	public CartaoDeCreditoEntity getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(CartaoDeCreditoEntity cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public CartaoDeDebitoEntity getCartaoDeDebito() {
		return cartaoDeDebito;
	}

	public void setCartaoDeDebito(CartaoDeDebitoEntity cartaoDeDebito) {
		this.cartaoDeDebito = cartaoDeDebito;
	}

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
	
	public CategoriasDeConta getCategoriaDeConta() {
		return categoriaDeConta;
	}

	public void setCategoriaDeConta(CategoriasDeConta categoriaDeConta) {
		this.categoriaDeConta = categoriaDeConta;
	}

	@Override
	public String toString() {			
		
		return "NOME: " + nome + ", CPF: "+cpf+", DATA DE NASCIMENTO: " + dataNascimento + ", CATEGORIA DE CONTA: "+getCategoriaContaCliente()+"\nENDEREÇO "
				+ endereco;
	}

}
