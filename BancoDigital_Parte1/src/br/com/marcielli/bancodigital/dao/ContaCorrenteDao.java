package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;

public class ContaCorrenteDao {
	
	private ArrayList<ContaCorrenteEntity> listaDeContasCorrente = new ArrayList<ContaCorrenteEntity>();
	

	public ArrayList<ContaCorrenteEntity> getListaDeContasCorrente() {
		return listaDeContasCorrente;
	}

	public void setListaDeContasCorrente(ArrayList<ContaCorrenteEntity> listaDeContasCorrente) {
		this.listaDeContasCorrente = listaDeContasCorrente;
	}
	
	//Acesso ao Dao do cliente
	public void teste(String cpf) {
		String cpfNovo = cpf.replace(".", "").replace("-", "");
		System.err.println("CPF "+cpfNovo);	
	}
}
