package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;

public class ContaPoupancaDao {
	
	private static ContaPoupancaDao instancia;
	
	public ArrayList<ContaPoupancaEntity> listaDeContasPoupanca = new ArrayList<ContaPoupancaEntity>();

	public static ContaPoupancaDao getInstancia() {
		if(instancia == null) {
			instancia = new ContaPoupancaDao();
		}		
		return instancia;
	}
	
	public void atualizarSaldo(float saldoNovo) {
		for(ContaPoupancaEntity cc : listaDeContasPoupanca) {	
			cc.setSaldo(saldoNovo);
		}
	}
	
	
	
	public void adicionarContaPoupanca(ContaPoupancaEntity contaPoupanca) {
		listaDeContasPoupanca.add(contaPoupanca);
	}	
	
	public ArrayList<ContaPoupancaEntity> verContasPoupancaAdicionadas(){
		return listaDeContasPoupanca;
	}

	public static void setInstancia(ContaPoupancaDao instancia) {
		ContaPoupancaDao.instancia = instancia;
	}

	public ArrayList<ContaPoupancaEntity> getListaDeContasPoupanca() {
		return listaDeContasPoupanca;
	}

	public void setListaDeContasPoupanca(ArrayList<ContaPoupancaEntity> listaDeContasPoupanca) {
		this.listaDeContasPoupanca = listaDeContasPoupanca;
	}
	
	

}
