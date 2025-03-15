package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeDebitoEntity extends CartaoEntity {
	
	private float limiteDiarioDeTransacao;

	public CartaoDeDebitoEntity(String numeroDoCartao, String nomeDoDono, String cpfDoDono, TiposDeConta tipoDaConta,
			CategoriasDeConta categoriaDaConta, TipoDeCartao tipoDeCartao, boolean status, String senha) {
		super(numeroDoCartao, nomeDoDono, cpfDoDono, tipoDaConta, categoriaDaConta, tipoDeCartao, status, senha);
		
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
	
	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfDoDono())) {
				texto = getTipoDeCartao().getDescricaoDoTipoDeCartao()+": "+cli.getNome()+" - número "+getNumeroDoCartao()+" do cpf "+getCpfDoDono()+" cadastrado com limite diário de transações de R$ "+getLimiteDiarioDeTransacao();
			}
		}		
		return texto;
		
	}		
	
	

}
