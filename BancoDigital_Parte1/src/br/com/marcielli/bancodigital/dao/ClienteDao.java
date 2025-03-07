package br.com.marcielli.bancodigital.dao;

import java.util.ArrayList;

import br.com.marcielli.bancodigital.entity.ClienteEntity;

public class ClienteDao {

	private ArrayList<ClienteEntity> listaDeClientes = new ArrayList<ClienteEntity>();

	public boolean addCliente(ClienteEntity cliente, int cod) {
		
		listaDeClientes.add(cod, cliente);
		return false;
	}
	
	public ArrayList<ClienteEntity> buscarClientes() {
	
		return listaDeClientes;
	}
	
	public boolean temCpf(String cpf) {
		for(ClienteEntity c : listaDeClientes) {
			
			if(cpf.equals(c.getCpf())) {				
				return true;
			}
		}		
		
		return false;
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
