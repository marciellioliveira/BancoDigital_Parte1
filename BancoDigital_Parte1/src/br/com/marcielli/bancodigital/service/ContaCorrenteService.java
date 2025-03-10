package br.com.marcielli.bancodigital.service;


import java.time.LocalDate;
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
		
		float saldoNovo = 0.0f;
		LocalDate dataDeAgora = LocalDate.now();
		LocalDate dataEsperada = LocalDate.of(dataDeAgora.getYear(), dataDeAgora.getMonth(), 01); //Deixei como padrão aqui 1, mas em programa real eu teria que ver o primeiro dia útil do mês

		if(dataEsperada.getDayOfMonth() == 01 ) {
			for(ContaCorrenteEntity cce : contaCorrenteDao.verContasCorrenteAdicionadas()) {
				
				if(cliente.getCpf().equals(cce.getContasDoClientePorCpf().getCpfDoCliente())) {
					saldoNovo += cce.getSaldo() - cce.getTaxaManutencaoMensal();			
					
					contaCorrenteDao.atualizarSaldo(saldoNovo);
					System.out.println("Hoje ("+dataEsperada.getDayOfMonth()+") foi descontada uma taxa de "+cce.getTaxaManutencaoMensal()+" da conta do cliente "+cce.getContasDoClientePorCpf().getNomeDoCliente()+" portador do CPF "+cce.getContasDoClientePorCpf().getCpfDoCliente()+" porque está cadastrado na conta "+cce.getContasDoClientePorCpf().getCategoriaDaContaDoCpf());					
					System.out.println("Saldo novo: "+cce.exibirSaldo());
				}
			}
		}
	}
	
	
	
}

