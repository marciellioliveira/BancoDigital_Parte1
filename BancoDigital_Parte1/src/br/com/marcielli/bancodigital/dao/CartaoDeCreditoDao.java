package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoEntity;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;

public class CartaoDeCreditoDao {

	private static CartaoDeCreditoDao instancia;	
	
	public ArrayList<CartaoDeCreditoEntity> listaDeCartoesDeCredito = new ArrayList<CartaoDeCreditoEntity>();
	
	private CartaoDeCreditoDao() {}
	
	public static CartaoDeCreditoDao getInstancia() {
		if (instancia == null) {
            instancia = new CartaoDeCreditoDao();
        }
        return instancia;
	}
	
	public void adicionarCartaoDeCredito(CartaoDeCreditoEntity cartaoDeCredito) {
		listaDeCartoesDeCredito.add(cartaoDeCredito);
	}	

	public ArrayList<CartaoDeCreditoEntity> getListaDeCartoesDeCredito() {
		return listaDeCartoesDeCredito;
	}

	public void setListaDeCartoesDeCredito(ArrayList<CartaoDeCreditoEntity> listaDeCartoesDeCredito) {
		this.listaDeCartoesDeCredito = listaDeCartoesDeCredito;
	}	
	
	public ArrayList<CartaoDeCreditoEntity> buscarCartoesDeCredito() {
		return listaDeCartoesDeCredito;
	}
	
	

}
