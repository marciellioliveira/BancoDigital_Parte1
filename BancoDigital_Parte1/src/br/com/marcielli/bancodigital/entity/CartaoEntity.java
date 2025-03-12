package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public abstract class CartaoEntity {
	
	private String numeroDoCartao;
	private String nomeDoDono;
	private String cpfDoDono;
	private TiposDeConta tipoDaConta;
	private CategoriasDeConta categoriaDaConta;
	private TipoDeCartao tipoDeCartao;
	private boolean status;
	public String senha;
	private ContasDoCliente contasDoCliente;
	
	public CartaoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha, ContasDoCliente contasDoCliente) {
		super();
		this.numeroDoCartao = numeroDoCartao;
		this.nomeDoDono = nomeDoDono;
		this.cpfDoDono = cpfDoDono;
		this.tipoDaConta = tipoDaConta;
		this.categoriaDaConta = categoriaDaConta;
		this.tipoDeCartao = tipoDeCartao;
		this.status = status;
		this.senha = senha;
		this.contasDoCliente = contasDoCliente;
	}	
	
	/* **
	 * Cartões de Crédito e Débito:** Emitir cartões vinculados às contas dos clientes, 
	 * permitindo operações de pagamento, alteração de senha, ativação/desativação e ajuste de limites.
	 * 
	 * - Cartão de Crédito: Deve ter um limite pré-aprovado e bloquear novos pagamentos ao atingir o limite.
	 * - Cartão de Débito: Deve ter um limite diário de transações, podendo ser ajustado pelo usuário.		 
	 * **/
	
	public abstract String alterarSenha();
	
	public abstract boolean alterarStatus();
	
	public abstract float ajustarLimite();
	
	public abstract float fazerPagamento();
	

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(String numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public String getNomeDoDono() {
		return nomeDoDono;
	}

	public void setNomeDoDono(String nomeDoDono) {
		this.nomeDoDono = nomeDoDono;
	}

	public String getCpfDoDono() {
		return cpfDoDono;
	}

	public void setCpfDoDono(String cpfDoDono) {
		this.cpfDoDono = cpfDoDono;
	}

	public TiposDeConta getTipoDaConta() {
		return tipoDaConta;
	}

	public void setTipoDaConta(TiposDeConta tipoDaConta) {
		this.tipoDaConta = tipoDaConta;
	}

	public CategoriasDeConta getCategoriaDaConta() {
		return categoriaDaConta;
	}

	public void setCategoriaDaConta(CategoriasDeConta categoriaDaConta) {
		this.categoriaDaConta = categoriaDaConta;
	}

	public TipoDeCartao getTipoDeCartao() {
		return tipoDeCartao;
	}

	public void setTipoDeCartao(TipoDeCartao tipoDeCartao) {
		this.tipoDeCartao = tipoDeCartao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ContasDoCliente getContasDoCliente() {
		return contasDoCliente;
	}

	public void setContasDoCliente(ContasDoCliente contasDoCliente) {
		this.contasDoCliente = contasDoCliente;
	}
	
	@Override
	public String toString() {
		return "\nNúmero o Cartão: "+numeroDoCartao;
	}	
	
	
	
}
