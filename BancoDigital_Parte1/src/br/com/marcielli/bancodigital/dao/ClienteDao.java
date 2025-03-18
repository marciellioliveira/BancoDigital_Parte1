package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ClienteEntity;

public class ClienteDao {	
	
	private static ClienteDao instancia;
	
	public ArrayList<ClienteEntity> listaDeClientes = new ArrayList<ClienteEntity>();
	
	private ClienteDao() {}
	
	public static ClienteDao getInstancia() {
		if (instancia == null) {
            instancia = new ClienteDao();
        }
        return instancia;
	}
	
//	public void adicionarCliente(ClienteEntity cliente) {
//		listaDeClientes.add(cliente);
//	}
	
	
	public boolean adicionarCliente(ClienteEntity cliente) {
		
		if(listaDeClientes.isEmpty()) {
			listaDeClientes.add(cliente);
			return true;
		}

		for(ClienteEntity c : listaDeClientes) {
			
			if(!cliente.getCpf().equals(c.getCpf())) {
				listaDeClientes.add(cliente);
				return true;
			}
		}		
		
		return false;
	}
	
	
	
	
	
	
	
	public ArrayList<ClienteEntity> verClientesCadastrados() {
		return listaDeClientes;
	}	
	
	public ClienteEntity buscarClienteComCpfCadastrado(String cpf) {	
		for(ClienteEntity c: listaDeClientes) {
			if(cpf.equals(c.getCpf())) {
				return c;
			}
		}		
		return null;
	}
	
	
	public boolean temCpf(String cpf) {
		for(ClienteEntity c : listaDeClientes) {
			
			if(cpf.equals(c.getCpf())) {				
				return true;
			}
		}		
	
		return false;
	}
	
	public ArrayList<ClienteEntity> buscarClientes() {
	
		for(ClienteEntity c: listaDeClientes) {
			if(c.getCpf() != null) {
				String cpfComCaracteres = c.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
				c.setCpf(cpfComCaracteres);
			}
		}
	
		return listaDeClientes;
	}
	
	
	
	
	
	
	public boolean removerCliente(String cpf) {

		for(ClienteEntity c : listaDeClientes) {
			
			if(cpf.equals(c.getCpf())) {
				listaDeClientes.remove(c);
				return true;
			}
		}		
		
		return false;
	}



	
	
}