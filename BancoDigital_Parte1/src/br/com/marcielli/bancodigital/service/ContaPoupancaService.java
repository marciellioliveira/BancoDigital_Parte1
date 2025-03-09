package br.com.marcielli.bancodigital.service;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaPoupancaService {

	ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao().getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	public void adicionarContaPoupancaEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
		
		try {
			
			System.out.println("\nA conta cadastrada no cpf "+cpfClienteDaConta+" do titular, tem:\n");
			
			for(ClienteEntity c : clienteDao.buscarClientes()) {
				
				if(cpfClienteDaConta.equals(c.getCpf())) {
					
					ContaPoupancaEntity contaPoupanca = new ContaPoupancaEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta);
					contaPoupancaDao.adicionarContaPoupanca(contaPoupanca);
					System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" do cpf "+cpfClienteDaConta+" cadastrada com sucesso!");				
				} 
				
			}
			
		} catch (Exception e) {
			System.err.println("Erro: "+e.getMessage());
		}	
		
	}
	
	
	public boolean verSeCpfACadastrarJatemClienteCadastrado(String cpf) {	
		
		for(ClienteEntity c : clienteDao.buscarClientes()) {
			if(cpf.equals(c.getCpf())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public ArrayList<ContaPoupancaEntity> verContasPoupancaCadastradasDao(){
		return contaPoupancaDao.verContasPoupancaAdicionadas();
	}
	
	
	
}
