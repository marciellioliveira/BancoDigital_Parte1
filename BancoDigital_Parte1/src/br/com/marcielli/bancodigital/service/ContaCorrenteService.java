package br.com.marcielli.bancodigital.service;


import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;



public class ContaCorrenteService {
	
	private ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	
	
	
	public void adicionarContaCorrenteEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
		
		System.err.println("CPF: "+cpfClienteDaConta);
		System.err.println("Saldo: "+saldo);
		System.err.println("Tipo: "+tipoDeConta);
		System.err.println("Categoria: "+categoriaDeConta);
		
		//ClienteEntity cliente = new ClienteEntity();
		
		
		
		//System.err.println("lista de Clientes: "+clienteDao.buscarClientes().size());
		for(ClienteEntity c : clienteDao.buscarClientes()) {
			System.err.println(c);
			
		}
	
		
		
		
		
		
		
		//ContaCorrenteEntity criarContaCorrente = new ContaCorrenteEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta);
		
		/*Primeiro preciso conseguir ver se no clientedao tem alguem cadastrado com esse cpf, mas não estou conseguindo 
		 * Verificar se já tem algum cliente com esse CPF e adicionar conta a ele
		 * caso não tenha, pedir pra primeiro criar um cliente e depois criar uma conta pro cliente*/
		
		//System.out.println("Quero ver se imprime todos os clientes aqui");
		//System.out.println();
//		System.out.println("Teste: "+clienteDao.buscarClientesComCpf(cpfClienteDaConta));
		
//		try {
//			verSeClienteExisteNoDao(cpfClienteDaConta);
//			
//		
//		} catch (ClienteNuloNoDaoException e) {
//			
//			System.err.println("Cliente: "+e.getMessage());
//			
//		} catch (Exception e) {
//			
//			System.err.println("Erro: "+e.getMessage());
//
//		}
	}
	

	
	
	
}
	
