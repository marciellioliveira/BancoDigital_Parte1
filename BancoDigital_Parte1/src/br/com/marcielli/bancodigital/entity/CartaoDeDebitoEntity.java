package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeDebitoEntity extends CartaoEntity {
	
	private float limiteDiarioDeTransacao;
	private String numeroContaVinculada;
	private float totalGastoMesDebito;

	public CartaoDeDebitoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha, String numeroContaVinculada) {
		super(numeroDoCartao, nomeDoDono, cpfDoDono, tipoDaConta, categoriaDaConta, tipoDeCartao, status, senha);
		
		this.numeroContaVinculada = numeroContaVinculada;
		
		if(categoriaDaConta.equals(CategoriasDeConta.COMUM)) {
			this.limiteDiarioDeTransacao = 1000f;			
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.SUPER)) {
			this.limiteDiarioDeTransacao = 5000f;
			//setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);
		}
		
		if(categoriaDaConta.equals(CategoriasDeConta.PREMIUM)) {
			this.limiteDiarioDeTransacao = 10000f;
			//setLimiteDeCreditoPreAprovado(limiteDeCreditoPreAprovado);		
		}
	}

	public float getLimiteDiarioDeTransacao() {
		return limiteDiarioDeTransacao;
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
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfDoDono())) {
				texto = getTipoDeCartao().getDescricaoDoTipoDeCartao()+": número "+getNumeroDoCartao()+" vinculado à conta "+getNumeroContaVinculada()+" e ao cpf "+getCpfDoDono()+", cadastrado com limite diário de transações de R$ "+getLimiteDiarioDeTransacao();			
			}
		}		
		return texto;
		
	}		
	
	

}
