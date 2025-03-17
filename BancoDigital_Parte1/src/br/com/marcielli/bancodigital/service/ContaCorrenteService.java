package br.com.marcielli.bancodigital.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import br.com.marcielli.bancodigital.dao.CartaoDeCreditoDao;
import br.com.marcielli.bancodigital.dao.CartaoDeDebitoDao;
import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoDeDebitoEntity;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.exception.CartoesRelacionadosContaTransfExistemException;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.exception.ContaAReceberNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaATransferirNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaComCPFExistenteException;
import br.com.marcielli.bancodigital.exception.CpfComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.EscolhaDosCartoesFalhouException;
import br.com.marcielli.bancodigital.exception.NumeroContasTransferenciasIguaisException;
import br.com.marcielli.bancodigital.exception.NumeroDaContaGeradoExistenteException;
import br.com.marcielli.bancodigital.exception.SemSaldoParaTransferenciaException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.TransferirValorMenorOuIgualAZeroException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;



public class ContaCorrenteService {
	
	ContaCorrenteDao contaCorrenteDao = ContaCorrenteDao.getInstancia();
	ContaPoupancaDao contaPoupancaDao = ContaPoupancaDao.getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();	
	
	CartaoDeCreditoDao cartaoDeCreditoDao = CartaoDeCreditoDao.getInstancia();
	CartaoDeDebitoDao cartaoDeDebitoDao = CartaoDeDebitoDao.getInstancia();
	
	
	public boolean adicionarContaCorrenteEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
		
		for(ClienteEntity c : clienteDao.buscarClientes()) {
		
		if(cpfClienteDaConta.equals(c.getCpf())) {
			
			String numeroDaConta = geraNumeroDaConta();		
			System.out.println("\nA conta número "+numeroDaConta+" foi cadastrada no cpf "+cpfClienteDaConta+" do titular:\n");			
			
			ContaCorrenteEntity contaCorrente = new ContaCorrenteEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, numeroDaConta);	
			
			//Adicionando Conta Corrente no Cliente			
			c.setContaCorrente(contaCorrente);
			
			contaCorrenteDao.adicionarContaCorrente(contaCorrente);
			
			
			System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" número "+numeroDaConta+" do cliente portador do cpf número "+cpfClienteDaConta+" foi cadastrada com sucesso!\n");			
			
			
			
			
			//descontarTaxaManutencaoMensal(c);
		} 
		
	}
		return true;
	}
	
	
	public void enviarPix(String cpfEnviarPix, float valor) throws SemSaldoParaTransferenciaException {
		
		if(valor <= 0) {
			throw new SemSaldoParaTransferenciaException("Você digitou um valor inválido para transferência");
		}
			
		for(ClienteEntity clienteEnviar : clienteDao.buscarClientes()) {
			
			
			if(cpfEnviarPix.equals(clienteEnviar.getCpf())) {
				
				if(clienteEnviar.getContaCorrente().exibirSaldo() <= 0) {
					throw new SemSaldoParaTransferenciaException("Você não tem saldo suficiente para fazer essa transferência.");
				}				
				
				if(valor > clienteEnviar.getContaCorrente().exibirSaldo()) {
					throw new SemSaldoParaTransferenciaException("Você não tem saldo suficiente para fazer essa transferência.");
				}
				
				
				clienteEnviar.getContaCorrente().enviarPix(valor);				
				
			}
		}
	
	}
	
	public void receberPix(String cpfReceberPix, float valor) {	
		
		for(ClienteEntity clienteReceber : clienteDao.buscarClientes()) {
			
			if(cpfReceberPix.equals(clienteReceber.getCpf())) {				
			
				clienteReceber.getContaCorrente().receberPix(valor);
		
			}
		}		
	}


	
	public void verSeTemContaComEsseCPF(String cpf, int tipoDeContaEscolhida) throws ContaComCPFExistenteException {
		
		/*
		 * tipoDeContaEscolhida = 1 : Conta Corrente
		 * tipoDeContaEscolhida = 2 : Conta Poupanca
		 * */
		
		if(tipoDeContaEscolhida == 1) {	
			if(contaCorrenteDao.existeContaCadastradaNoCPF(cpf, tipoDeContaEscolhida)) {
				throw new ContaComCPFExistenteException("O CPF "+cpf+" já possui uma conta corrente cadastrada.");
			}		
		} else if(tipoDeContaEscolhida == 2) {				
			TiposDeConta contaPoupanca;
			contaPoupanca = TiposDeConta.CONTA_POUPANCA;
		}
		
		

	}
	
	public void validarCpf(String cpf) throws TamanhoDoCpfException, CpfJaCadastradoException, ValidarUltimosNumerosDoCpfException, CpfComNumerosIguaisException  {
		//cpf = cpf.replace(".", "").replace("-", "");
		
		if(!cpf.contains(".") && cpf.contains("-")) {
			if(cpf.length() < 14 || cpf.length() > 14){
				throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14..\nDigite novamente o CPF com pontos e traços. EX: 12.630-000");
			} 
		}
		
		if(cpf.length() < 14 || cpf.length() > 14){
			throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14.\nDigite novamente");
		} 
		
		if(!clienteDao.temCpf(cpf)) {
			throw new CpfJaCadastradoException("O "+cpf+" não está vinculado a nenhum cliente.\nAdicione um cliente e cadastre o CPF nele para criar uma conta.\n");
		}
		
//		if(clienteDao.temCpf(cpf)) {
//			throw new CpfJaCadastradoException("O cpf "+cpf+" já está cadastrado com outro usuário.\nAdicione um CPF único para cada cliente.\n");
//		}
		
		//Validar ultimos numeros do cpf
		int valor = 0;
		int j = 10;


		for(int i=0; i<cpf.length(); i++) { 
		
			while(j>1) { 
				
				char letra = cpf.charAt(i);
				int caracter = letra - '0';
				
				valor += caracter * j; 			
				j--;
				break;
				
			}		
		}
				
		int resultado = (valor * 10) % 11;
			
		if(resultado == 10) {
			resultado = 0;
		} else {
			
			char penultimoDig = cpf.charAt(9);			
			int penultimoDigito = penultimoDig - '0';
			
			
			if(resultado == penultimoDigito) {			
			
				int b = 11;
			
				int valor2 = 0;
				
				for(int a=0; a<10; a++) { 
					while(b>1) { 
						char letra2 = cpf.charAt(a); 
						int caracter2 = letra2 - '0';
						
						valor2 += caracter2 * b; 
						
						b--;
						
						break;
					}
				}
			
			
			int resultado2 = (valor2 * 10) % 11;
			
			char ultimoDig = cpf.charAt(10);
			int ultimoDigito = ultimoDig - '0';		
			
			if(!(resultado2 == ultimoDigito)) {
				throw new ValidarUltimosNumerosDoCpfException("O CPF '"+cpf+"' digitado não é válido. Por favor, digite um CPF válido.");
			}	
			
			} 		
		}
		
		
		//Validar cpf com numeros iguais
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoCpf = "";
		
		//Usar o hashmap para contar os caracteres duplicados da string cpf
		
		char[] meuCpf = cpf.toCharArray();
		
		for(int i=0; i<meuCpf.length; i++) {
			if(!findDuplicated.containsKey(meuCpf[i])) {
				findDuplicated.put(meuCpf[i], 1);
				novoCpf += meuCpf[i];
				
			} else {
				findDuplicated.put(meuCpf[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CpfComNumerosIguaisException("O CPF '"+cpf+"' digitado não é válido. Por favor, digite um CPF válido.");
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
				float saldoAntigo = cce.exibirSaldo();
				if(cliente.getCpf().equals(cce.getCpfClienteDaConta())) {
					saldoNovo += cce.getSaldo() - cce.getTaxaManutencaoMensal();			
					
					contaCorrenteDao.atualizarSaldo(saldoNovo);
					System.out.println("Cliente: "+cliente.getNome());
					System.out.println("Hoje ("+dataEsperada.getDayOfMonth()+") foi descontada uma taxa de "+cce.getTaxaManutencaoMensal()+" da conta número "+cce.getNumeroDaConta()+" do cliente "+cliente.getNome()+" portador do CPF "+cce.getCpfClienteDaConta()+" porque está cadastrado na conta "+cce.getCategoriaDeConta().getTipoDaCategoria());					
					System.out.println("Saldo Antigo: "+saldoAntigo);
					System.out.println("Saldo novo: "+cce.exibirSaldo()+"\n");
				}

			}
		}
	}
	
	
	
}

