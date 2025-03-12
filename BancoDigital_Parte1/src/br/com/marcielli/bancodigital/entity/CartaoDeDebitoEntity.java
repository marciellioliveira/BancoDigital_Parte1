package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeDebitoEntity extends CartaoEntity {
	
	private float limiteDiarioDeTransacao;

	public CartaoDeDebitoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha,  ContasDoCliente contasDoCliente) {
		super(numeroDoCartao, nomeDoDono, cpfDoDono, tipoDaConta, categoriaDaConta, tipoDeCartao, status, senha, contasDoCliente);
		
	}

	public float getLimiteDiarioDeTransacao() {
		return limiteDiarioDeTransacao;
	}

	public void setLimiteDiarioDeTransacao(float limiteDiarioDeTransacao) {
		this.limiteDiarioDeTransacao = limiteDiarioDeTransacao;
	}

	@Override
	public String alterarSenha() {
		
		return null;
	}

	@Override
	public boolean alterarStatus() {
		
		return false;
	}

	@Override
	public float ajustarLimite() {
		
		return 0;
	}

	@Override
	public float fazerPagamento() {
		
		return 0;
	}
	
	

}
