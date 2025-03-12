package br.com.marcielli.bancodigital.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

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
			
			
			
			for(ClienteEntity c : clienteDao.buscarClientes()) {
				
				if(cpfClienteDaConta.equals(c.getCpf())) {
					
					String numeroDaConta = geraNumeroDaConta();		
					System.out.println("\nA conta número "+numeroDaConta+" foi cadastrada no cpf "+cpfClienteDaConta+" do titular:\n");
					
					ContasDoCliente contaDoCliente = new ContasDoCliente(c.getNome(), cpfClienteDaConta, categoriaDeConta, tipoDeConta, numeroDaConta);
					
					ContaCorrenteEntity contaCorrente = new ContaCorrenteEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, contaDoCliente, numeroDaConta);	
					contaCorrenteDao.adicionarContaCorrente(contaCorrente);
					
					System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" número "+numeroDaConta+" do cliente portador do cpf número "+cpfClienteDaConta+" foi cadastrada com sucesso!\n");			
					
					descontarTaxaManutencaoMensal(c);
				} 
				
			}
			
			
			
		} catch (Exception e) {
			System.err.println("Erro: "+e.getMessage());
		}	

	}
	
	public String geraNumeroDaConta() {
		
		int[] sequencia = new int[8];
		Random random = new Random();
		String minhaConta = "";
		
		
		for(int i=0; i<sequencia.length; i++) {			
			sequencia[i] = 1 + random.nextInt(8);
		}
		
		for(int i=0; i<sequencia.length; i++) {			
			minhaConta += Integer.toString(sequencia[i]);
		}		
		
		ContaEntity cent = new ContaCorrenteEntity();
		
		cent.setNumeroDaConta(minhaConta.concat("-cc"));
	
		
		return cent.getNumeroDaConta();
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
					System.out.println("Hoje ("+dataEsperada.getDayOfMonth()+") foi descontada uma taxa de "+cce.getTaxaManutencaoMensal()+" da conta número "+cce.getNumeroDaConta()+" do cliente "+cce.getContasDoClientePorCpf().getNomeDoCliente()+" portador do CPF "+cce.getContasDoClientePorCpf().getCpfDoCliente()+" porque está cadastrado na conta "+cce.getContasDoClientePorCpf().getCategoriaDaContaDoCpf());					
					System.out.println("Saldo novo: "+cce.exibirSaldo());
				}
			}
		}
	}
	
	
	
}

