package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContasDoCliente {
	
	private String nomeDoCliente;
	private String cpfDoCliente;
	private CategoriasDeConta categoriaDaContaDoCpf;
	private TiposDeConta tipoDeContaDoCpf;
	
	public ContasDoCliente(String nomeDoCliente, String cpfDoCliente, CategoriasDeConta categoriaDaContaDoCpf, TiposDeConta tipoDeContaDoCpf) {
		this.nomeDoCliente = nomeDoCliente;
		this.cpfDoCliente = cpfDoCliente;
		this.categoriaDaContaDoCpf = categoriaDaContaDoCpf;
		this.tipoDeContaDoCpf = tipoDeContaDoCpf;
	}
	
	public ContasDoCliente() {}

	public String getNomeDoCliente() {
		return nomeDoCliente;
	}

	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}

	public String getCpfDoCliente() {
		return cpfDoCliente;
	}

	public void setCpfDoCliente(String cpfDoCliente) {
		this.cpfDoCliente = cpfDoCliente;
	}

	public CategoriasDeConta getCategoriaDaContaDoCpf() {
		return categoriaDaContaDoCpf;
	}

	public void setCategoriaDaContaDoCpf(CategoriasDeConta categoriaDaContaDoCpf) {
		this.categoriaDaContaDoCpf = categoriaDaContaDoCpf;
	}

	public TiposDeConta getTipoDeContaDoCpf() {
		return tipoDeContaDoCpf;
	}

	public void setTipoDeContaDoCpf(TiposDeConta tipoDeContaDoCpf) {
		this.tipoDeContaDoCpf = tipoDeContaDoCpf;
	}
	
	@Override
	public String toString() {			
		return "CONTAS DO CLIENTE: Nome "+getNomeDoCliente()+", CPF "+getCpfDoCliente()+", Categoria da Conta "+getCategoriaDaContaDoCpf()+", Tipo de Conta "+getTipoDeContaDoCpf();
	}
	

}
