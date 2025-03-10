package br.com.marcielli.bancodigital.service;


import java.util.ArrayList;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContasDoCliente;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;



public class ContaCorrenteService {
	
	ContaCorrenteDao contaCorrenteDao = ContaCorrenteDao.getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	
	
	
	public void adicionarContaCorrenteEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
		
//		System.err.println("CPF: "+cpfClienteDaConta);
//		System.err.println("Saldo: "+saldo);
//		System.err.println("Tipo: "+tipoDeConta);
//		System.err.println("Categoria: "+categoriaDeConta);		
		
//		for(ClienteEntity c : clienteDao.buscarClientes()) {
//			System.err.println(c);			
//		}
//		
		//verSeCpfACadastrarJatemClienteCadastrado(cpfClienteDaConta); 
		
		try {
			
			System.out.println("\nA conta cadastrada no cpf "+cpfClienteDaConta+" do titular, tem:\n");
			
			
			
			for(ClienteEntity c : clienteDao.buscarClientes()) {
				
				if(cpfClienteDaConta.equals(c.getCpf())) {
					
					ContasDoCliente contaDoCliente = new ContasDoCliente(c.getNome(), cpfClienteDaConta, categoriaDeConta, tipoDeConta);
					
					ContaCorrenteEntity contaCorrente = new ContaCorrenteEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, contaDoCliente);	
					contaCorrenteDao.adicionarContaCorrente(contaCorrente);
					
					System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" do cpf "+cpfClienteDaConta+" cadastrada com sucesso!");			
					
					descontarTaxaManutencaoMensal(c);
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
	
	
	public ArrayList<ContaCorrenteEntity> verContasCorrentesCadastradasDao(){
		return contaCorrenteDao.verContasCorrenteAdicionadas();
	}
	
	
	public void descontarTaxaManutencaoMensal(ClienteEntity cliente) {

		for(ContaCorrenteEntity cce : contaCorrenteDao.verContasCorrenteAdicionadas()) {
			System.err.println("----------------------------------------------");
			System.out.println("Dentro de contas correntes adicionadas");
			System.out.println("CCE: "+cce.getContasDoClientePorCpf());
		
		}

		

	
	
	
}
}
