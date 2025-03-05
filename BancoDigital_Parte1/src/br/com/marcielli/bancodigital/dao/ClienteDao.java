package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ClienteEntity;

public class ClienteDao {

	private ArrayList<ClienteEntity> listaDeClientes = new ArrayList<ClienteEntity>();

	public boolean addCliente(ClienteEntity cliente, int cod) {
		
		for(ClienteEntity c : listaDeClientes) {
			if(cliente.getCpf().equals(c.getCpf())) {
				return true;
			}
		}
		listaDeClientes.add(cod, cliente);
		return false;
	}
	
	public ArrayList<ClienteEntity> buscarClientes() {
	
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
