package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoDeDebitoEntity;

public class CartaoDeDebitoDao {
	
	private static CartaoDeDebitoDao instancia;
	public ArrayList<CartaoDeDebitoEntity> listaDeCartoesDeDebito = new ArrayList<CartaoDeDebitoEntity>();
	
	private CartaoDeDebitoDao() {}
	
	public static CartaoDeDebitoDao getInstancia() {
		if(instancia == null) {
			instancia = new CartaoDeDebitoDao();
		}
		return instancia;
	}
	
	public void adicionarCartaoDeDebito(CartaoDeDebitoEntity cartaoDeDebito) {
		listaDeCartoesDeDebito.add(cartaoDeDebito);
	}	

	public ArrayList<CartaoDeDebitoEntity> getListaDeCartoesDeDebito() {
		return listaDeCartoesDeDebito;
	}

	public void setListaDeCartoesDeDebito(ArrayList<CartaoDeDebitoEntity> listaDeCartoesDeDebito) {
		this.listaDeCartoesDeDebito = listaDeCartoesDeDebito;
	}
	
	public ArrayList<CartaoDeDebitoEntity> buscarCartoesDeDebito() {
		return listaDeCartoesDeDebito;
	}
	
	

}
