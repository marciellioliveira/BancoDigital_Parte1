package br.com.marcielli.bancodigital.entity;

import java.util.ArrayList;
import java.util.Random;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public abstract class ContaEntity {
	
	private String cpfClienteDaConta;
	private TiposDeConta tipoDeConta;
	private CategoriasDeConta categoriaDeConta;
	private float saldo;
	//private ContasDoCliente contasDoClientePorCpf;
	private String numeroDaConta;
	
	
	
//	private CartaoDeCreditoEntity cartaoDeCredito;
//	private CartaoDeDebitoEntity cartaoDeDebito;
	
	
	public ContaEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta,  String numeroDaConta) {
		this.cpfClienteDaConta = cpfClienteDaConta;
		this.saldo = saldo;
		this.tipoDeConta = tipoDeConta;
		//this.contasDoClientePorCpf = contasDoClientePorCpf;	
		this.numeroDaConta = numeroDaConta;
	}
	
	
	
	
	
//	public ContaEntity(CartaoDeCreditoEntity cartaoDeCredito) {
//		super();
//		this.cartaoDeCredito = cartaoDeCredito;
//	}
//	
//	public ContaEntity(CartaoDeDebitoEntity cartaoDeDebito) {
//		super();
//		this.cartaoDeDebito = cartaoDeDebito;
//	}



	



	public ContaEntity() {}
		
	
	public abstract float exibirSaldo();
	
	public abstract void fazerTransferenciaViaPix();	
	
	//public abstract ArrayList<CartaoEntity> salvarCartoesDaConta();
	
	
//
//	public CartaoDeCreditoEntity getCartaoDeCredito() {
//		return cartaoDeCredito;
//	}
//
//	public void setCartaoDeCredito(CartaoDeCreditoEntity cartaoDeCredito) {
//		this.cartaoDeCredito = cartaoDeCredito;
//	}
//
//	public CartaoDeDebitoEntity getCartaoDeDebito() {
//		return cartaoDeDebito;
//	}
//
//	public void setCartaoDeDebito(CartaoDeDebitoEntity cartaoDeDebito) {
//		this.cartaoDeDebito = cartaoDeDebito;
//	}
	



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

//	public ContasDoCliente getContasDoClientePorCpf() {
//		return contasDoClientePorCpf;
//	}
//
//	public void setContasDoClientePorCpf(ContasDoCliente contasDoClientePorCpf) {
//		this.contasDoClientePorCpf = contasDoClientePorCpf;
//	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}
	
	@Override
	public String toString() {
		return "\nNúmero da Conta: "+numeroDaConta;
	}	
}
