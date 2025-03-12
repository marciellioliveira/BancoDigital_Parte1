package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.ContasDoCliente;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.exception.ContaComCPFExistenteException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaPoupancaService {

	ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao().getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	public boolean adicionarContaPoupancaEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
		
		try {						
			verSeTemContaComEsseCPF(cpfClienteDaConta);
//			for(ClienteEntity c : clienteDao.buscarClientes()) {
//				
//				if(cpfClienteDaConta.equals(c.getCpf())) {
//					
//					String numeroDaConta = geraNumeroDaConta();	
//					System.out.println("\nA conta número "+numeroDaConta+" foi cadastrada no cpf "+cpfClienteDaConta+" do titular:\n");
//					
//					ContasDoCliente contaDoCliente = new ContasDoCliente(c.getNome(), cpfClienteDaConta, categoriaDeConta, tipoDeConta, numeroDaConta);
//					ContaPoupancaEntity contaPoupanca = new ContaPoupancaEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, contaDoCliente, numeroDaConta);
//					contaPoupancaDao.adicionarContaPoupanca(contaPoupanca);
//					System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" número "+numeroDaConta+" do cliente portador do cpf número "+cpfClienteDaConta+" foi cadastrada com sucesso!\n");				
//					
//					creditarTaxaVigenteMensal(c);
//				} 
//				
//			}
		} catch (ContaComCPFExistenteException e) {
			
			System.err.println("CPF: "+e.getMessage());
			return false;
			
		} catch (Exception e) {
			System.err.println("Erro: "+e.getMessage());
			return false;
		}
		
		for(ClienteEntity c : clienteDao.buscarClientes()) {
			
			if(cpfClienteDaConta.equals(c.getCpf())) {
				
				String numeroDaConta = geraNumeroDaConta();	
				System.out.println("\nA conta número "+numeroDaConta+" foi cadastrada no cpf "+cpfClienteDaConta+" do titular:\n");
				
				ContasDoCliente contaDoCliente = new ContasDoCliente(c.getNome(), cpfClienteDaConta, categoriaDeConta, tipoDeConta, numeroDaConta);
				ContaPoupancaEntity contaPoupanca = new ContaPoupancaEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, contaDoCliente, numeroDaConta);
				contaPoupancaDao.adicionarContaPoupanca(contaPoupanca);
				System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" número "+numeroDaConta+" do cliente portador do cpf número "+cpfClienteDaConta+" foi cadastrada com sucesso!\n");				
				
				creditarTaxaVigenteMensal(c);
			} 
			
		}
		return true;
		
	}
	
	private boolean verSeTemContaComEsseCPF(String cpf) throws ContaComCPFExistenteException {
		
		for(ContaPoupancaEntity cc : contaPoupancaDao.listaDeContasPoupanca) {
			if(clienteDao.temCpf(cpf)) {
				if(cpf.equals(cc.getCpfClienteDaConta())) {
					throw new ContaComCPFExistenteException("Esse CPF "+cpf+" já possui uma conta poupança.\nVocê só pode ter uma conta poupança por CPF.\n");
				}				
			}
		}
		
		return false;
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
		
		cent.setNumeroDaConta(minhaConta.concat("-cp"));
	
		
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
	
	
	public ArrayList<ContaPoupancaEntity> verContasPoupancaCadastradasDao(){
		return contaPoupancaDao.verContasPoupancaAdicionadas();
	}
	
	public void creditarTaxaVigenteMensal(ClienteEntity cliente) {
		//saldoNovo = capitalInicial x (1 + taxaDeJuros)^qntVezesAcumulo = 
		//saldoNovo: O montante total 
		//capitalInicial: O capital inicial investido 
		//taxaDeJuros: A taxa de juros aplicada aos juros compostos 
		//qntVezesAcumulo: A quantidade de vezes que os juros serão acumulados	
		
		double m=0.0f;
		float saldoNovo = 0.0f;
		LocalDate dataDeAgora = LocalDate.now();
		LocalDate dataEsperada = LocalDate.of(dataDeAgora.getYear(), dataDeAgora.getMonth(), 01); //Deixei como padrão aqui 1, mas em programa real eu teria que ver o primeiro dia útil do mês

		if(dataEsperada.getDayOfMonth() == 01 ) {
			for(ContaPoupancaEntity cpe1 : contaPoupancaDao.verContasPoupancaAdicionadas()) {
				
				if(cliente.getCpf().equals(cpe1.getContasDoClientePorCpf().getCpfDoCliente())) {
					 m += (float) (cpe1.getSaldo() * Math.pow(1 + cpe1.getTaxaMensal(), 12));
						saldoNovo += m + cpe1.getAcrescimoTaxaRendimento();
										
					contaPoupancaDao.atualizarSaldo(saldoNovo);
					System.out.println("Hoje ("+dataEsperada.getDayOfMonth()+") foi acrescentada uma taxa de "+cpe1.getAcrescimoTaxaRendimento()+" da conta do cliente "+cpe1.getContasDoClientePorCpf().getNomeDoCliente()+" portador do CPF "+cpe1.getContasDoClientePorCpf().getCpfDoCliente()+" porque está cadastrado na conta "+cpe1.getCategoriaDeConta());					
					System.out.println("Saldo novo: "+cpe1.exibirSaldo());
				
					
				
				}
			}
		}
		
	
	
	}
}
