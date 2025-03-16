package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeCreditoEntity extends CartaoEntity {
	
	private float limiteDeCreditoPreAprovado;
	private float taxaDeUtilizacao;
	private float taxaSeguroViagem;
	private String numeroContaVinculada;
	private float totalGastoMesCredito;

	public CartaoDeCreditoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha, String numeroContaVinculada) {
		super(numeroDoCartao, nomeDoDono, cpfDoDono, tipoDaConta, categoriaDaConta, tipoDeCartao, status, senha);		
		
		if(categoriaDaConta.equals(CategoriasDeConta.COMUM)) {
			this.limiteDeCreditoPreAprovado = 1000f;
			//setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);			
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.SUPER)) {
			this.limiteDeCreditoPreAprovado = 5000f;
			//setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.PREMIUM)) {
			this.limiteDeCreditoPreAprovado = 10000f;
			//setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);		
		}
		
		this.numeroContaVinculada = numeroContaVinculada;
		/*
		 * - **Seguro Viagem:** Gratuito para clientes Premium; opcional para clientes Comum e Super, com taxa de R$ 50,00 por mês.
		 * 
		 * - **Seguro de Fraude:** Cobertura automática para todos os cartões, com um valor de apólice base de R$ 5.000,00.
		 * */
	}
	
	public float taxaDeUtilizacao() {
		//Acho que ta errado
	
//		float porcentoDeTotalGasto = (80 * getLimiteDeCreditoPreAprovado())/100;
//		
//		if(totalGastoMes > porcentoDeTotalGasto) {
//			float res = totalGastoMes - (5 * totalGastoMes)/100;
//		}
//		
		//Saber o total gasto no mês (Taxa tira do limite do crédito ou do saldo da conta do cartão?)
		//Se total gasto no mês for maior que 80%, aplicar 5% sobre o total		
		
		//- **Taxa de Utilização:** 5% sobre o total gasto no mês, aplicável apenas se o total de gastos exceder 80% do limite de crédito.
		return 0.0f;
	}

	public float getLimiteDeCreditoPreAprovado() {
		return limiteDeCreditoPreAprovado;
	}

	public void setLimiteDeCreditoPreAprovado(float limiteDeCreditoPreAprovado) {
	
		this.limiteDeCreditoPreAprovado = limiteDeCreditoPreAprovado;
	}
	
	

	public String getNumeroContaVinculada() {
		return numeroContaVinculada;
	}

	public void setNumeroContaVinculada(String numeroContaVinculada) {
		this.numeroContaVinculada = numeroContaVinculada;
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
		texto = getTipoDeCartao().getDescricaoDoTipoDeCartao()+": número "+getNumeroDoCartao()+" vinculado à conta "+getNumeroContaVinculada()+" e ao cpf "+getCpfDoDono()+", cadastrado com limite de crédito pré-aprovado de R$ "+getLimiteDeCreditoPreAprovado()+" e taxa de utilização de "+taxaDeUtilizacao;
		return texto;
		
	}	
	
//	@Override
//	public String toString() {
//		String texto = "";
//		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
//			if(cli.getCpf().equals(getCpfDoDono())) {
//				texto = getTipoDeCartao().getDescricaoDoTipoDeCartao()+": número "+getNumeroDoCartao()+" vinculado à conta "+getNumeroContaVinculada()+" e ao cpf "+getCpfDoDono()+", cadastrado com limite inicial de R$ "+getLimiteDeCreditoPreAprovado()+" e taxa de utilização de "+taxaDeUtilizacao;
//			}
//		}		
//		return texto;
//		
//	}		
}
