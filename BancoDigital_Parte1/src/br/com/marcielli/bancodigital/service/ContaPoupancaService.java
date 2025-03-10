package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.ContasDoCliente;
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
					
					ContasDoCliente contaDoCliente = new ContasDoCliente(c.getNome(), cpfClienteDaConta, categoriaDeConta, tipoDeConta);
					ContaPoupancaEntity contaPoupanca = new ContaPoupancaEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, contaDoCliente);
					contaPoupancaDao.adicionarContaPoupanca(contaPoupanca);
					System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" do cpf "+cpfClienteDaConta+" cadastrada com sucesso!");		
					
					creditarTaxaVigenteMensal(c);
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
	
	public void creditarTaxaVigenteMensal(ClienteEntity cliente) {
		//saldoNovo = capitalInicial x (1 + taxaDeJuros)^qntVezesAcumulo = 
		//saldoNovo: O montante total 
		//capitalInicial: O capital inicial investido 
		//taxaDeJuros: A taxa de juros aplicada aos juros compostos 
		//qntVezesAcumulo: A quantidade de vezes que os juros serão acumulados	
		
		float m=0.0f;
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
