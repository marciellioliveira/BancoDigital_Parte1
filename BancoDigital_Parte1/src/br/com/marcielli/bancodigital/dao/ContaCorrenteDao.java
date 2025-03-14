package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContasDoCliente;

public class ContaCorrenteDao {
	
	private static ContaCorrenteDao instancia;
	
	public  ArrayList<ContaCorrenteEntity> listaDeContasCorrente = new ArrayList<ContaCorrenteEntity>();
	
	private ContaCorrenteDao() {}
	
	public static ContaCorrenteDao getInstancia() {
		if(instancia == null) {
			instancia = new ContaCorrenteDao();
		}
		
		return instancia;
	}
	
	public void atualizarSaldo(float SaldoNovo) {
		for(ContaCorrenteEntity cc : listaDeContasCorrente) {
			cc.setSaldo(SaldoNovo);
		}
	}	
	
	public void adicionarContaCorrente(ContaCorrenteEntity contaCorrente) {
		listaDeContasCorrente.add(contaCorrente);
	}	
	
	public ArrayList<ContaCorrenteEntity> verContasCorrenteAdicionadas(){
		return listaDeContasCorrente;
	}

	public ArrayList<ContaCorrenteEntity> getListaDeContasCorrente() {
		return listaDeContasCorrente;
	}

	public void setListaDeContasCorrente(ArrayList<ContaCorrenteEntity> listaDeContasCorrente) {
		this.listaDeContasCorrente = listaDeContasCorrente;
	}

	

	

	
	
	
	
	

}
