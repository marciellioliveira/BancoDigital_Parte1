package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public abstract class ContaEntity {
	
	private String cpfClienteDaConta;
	private TiposDeConta tipoDeConta;
	private CategoriasDeConta categoriaDeConta;
	private float saldo;
	private ContasDoCliente contasDoClientePorCpf;
	
	
	public ContaEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta, ContasDoCliente contasDoClientePorCpf) {
		this.cpfClienteDaConta = cpfClienteDaConta;
		this.saldo = saldo;
		this.tipoDeConta = tipoDeConta;
		this.contasDoClientePorCpf = contasDoClientePorCpf;
		
		
	}
	
	public ContaEntity() {}
	
	public abstract float exibirSaldo();
	
	public abstract void fazerTransferenciaViaPix();		

	public String getCpfClienteDaConta() {
		return cpfClienteDaConta;
	}

	public void setCpfClienteDaConta(String cpfClienteDaConta) {
		this.cpfClienteDaConta = cpfClienteDaConta;
	}

	public TiposDeConta getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(TiposDeConta tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}		

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}	
	
	public CategoriasDeConta getCategoriaDeConta() {
		return categoriaDeConta;
	}

	public void setCategoriaDeConta(CategoriasDeConta categoriaDeConta) {
		this.categoriaDeConta = categoriaDeConta;
	}

	public ContasDoCliente getContasDoClientePorCpf() {
		return contasDoClientePorCpf;
	}

	public void setContasDoClientePorCpf(ContasDoCliente contasDoClientePorCpf) {
		this.contasDoClientePorCpf = contasDoClientePorCpf;
	}

	
	
	
}
