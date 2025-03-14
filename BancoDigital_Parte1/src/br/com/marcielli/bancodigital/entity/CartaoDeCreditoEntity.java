package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeCreditoEntity extends CartaoEntity {
	
	private float limiteDeCreditoPreAprovado;
	private float taxaDeUtilizacao;
	private float taxaSeguroViagem;

	public CartaoDeCreditoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha) {
		super(numeroDoCartao, nomeDoDono, cpfDoDono, tipoDaConta, categoriaDaConta, tipoDeCartao, status, senha);		
		
		if(categoriaDaConta.equals(CategoriasDeConta.COMUM)) {
			this.limiteDeCreditoPreAprovado = 1.000f;
			setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.SUPER)) {
			this.limiteDeCreditoPreAprovado = 5.000f;
			setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.PREMIUM)) {
			this.limiteDeCreditoPreAprovado = 10.000f;
			setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);		
		}
		
		/*
		 * - **Seguro Viagem:** Gratuito para clientes Premium; opcional para clientes Comum e Super, com taxa de R$ 50,00 por mês.
		 * 
		 * - **Seguro de Fraude:** Cobertura automática para todos os cartões, com um valor de apólice base de R$ 5.000,00.
		 * */
	}
	
	public float taxaDeUtilizacao() {
		//- **Taxa de Utilização:** 5% sobre o total gasto no mês, aplicável apenas se o total de gastos exceder 80% do limite de crédito.
		return 0.0f;
	}

	public float getLimiteDeCreditoPreAprovado() {
		return limiteDeCreditoPreAprovado;
	}

	public void setLimiteDeCreditoPreAprovado(float limiteDeCreditoPreAprovado) {
	
		this.limiteDeCreditoPreAprovado = limiteDeCreditoPreAprovado;
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
	
	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfDoDono())) {
				texto = "Cartão de Crédito de: "+cli.getNome()+" - "+getTipoDeCartao()+" número "+getNumeroDoCartao()+" do cpf "+getCpfDoDono()+" cadastrado com sucesso. Limite inicial de R$ "+getLimiteDeCreditoPreAprovado()+" e taxa de utilização de "+taxaDeUtilizacao;
			}
		}		
		return texto;
		
	}		
}
