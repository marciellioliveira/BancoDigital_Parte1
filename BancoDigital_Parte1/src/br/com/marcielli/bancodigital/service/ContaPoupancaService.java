package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.exception.ContaAReceberNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaATransferirNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaComCPFExistenteException;
import br.com.marcielli.bancodigital.exception.CpfComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.ExisteContaCadastradaException;
import br.com.marcielli.bancodigital.exception.SemSaldoParaTransferenciaException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaPoupancaService {

	ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao().getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	CartaoDeCreditoDao cartaoDeCreditoDao = CartaoDeCreditoDao.getInstancia();
	CartaoDeDebitoDao cartaoDeDebitoDao = CartaoDeDebitoDao.getInstancia();
	
	public boolean adicionarContaPoupancaEntityEmDao(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta,  CategoriasDeConta categoriaDeConta) throws ClienteNuloNoDaoException {
			
		for(ClienteEntity c : clienteDao.buscarClientes()) {
			
			if(cpfClienteDaConta.equals(c.getCpf())) {
				
				String numeroDaConta = geraNumeroDaConta();	
				System.out.println("\nA conta número "+numeroDaConta+" foi cadastrada no cpf "+cpfClienteDaConta+" do titular:\n");
				
				ContaPoupancaEntity contaPoupanca = new ContaPoupancaEntity(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta,  numeroDaConta);
				
				c.setContaPoupanca(contaPoupanca);
				c.setCategoriaDeConta(saldo);
								
				clienteDao.atualizarCategoriaDaConta(cpfClienteDaConta, categoriaDeConta);
				contaPoupancaDao.adicionarContaPoupanca(contaPoupanca);
				System.out.println("\n"+tipoDeConta.getDescricaoDaConta()+" número "+numeroDaConta+" do cliente portador do cpf número "+cpfClienteDaConta+" foi cadastrada com sucesso!\n");				
	
			} 
			
		}
		return true;
		
	}
	
	public boolean temContaPoupanca(String cpf) throws ExisteContaCadastradaException{
		
			for(ContaPoupancaEntity contaPoupancaEnviar : contaPoupancaDao.verContasPoupancaAdicionadas()) {				
			if(!cpf.equals(contaPoupancaEnviar.getCpfClienteDaConta())) {
				throw new ExisteContaCadastradaException("Você não pode fazer a transferência porque não tem uma conta poupança cadastrada.");
			}
		}
		
		return true;
	}

	
	public boolean enviarPix(String cpfEnviarPix, float valor) throws SemSaldoParaTransferenciaException {
		
		if(valor <= 0) {
			throw new SemSaldoParaTransferenciaException("Você digitou um valor inválido para transferência");
		}
			
		for(ClienteEntity clienteEnviar : clienteDao.buscarClientes()) {
			
			if(cpfEnviarPix.equals(clienteEnviar.getCpf())) {
				
				if(clienteEnviar.getContaPoupanca().exibirSaldo() <= 0) {
					throw new SemSaldoParaTransferenciaException("Você não tem saldo suficiente para fazer essa transferência.");
				}				
				
				if(valor > clienteEnviar.getContaPoupanca().exibirSaldo()) {
					throw new SemSaldoParaTransferenciaException("Você não tem saldo suficiente para fazer essa transferência.");
				}
				
				
				float valorEnviaAntigo = clienteEnviar.getContaPoupanca().getSaldo();
				clienteEnviar.getContaPoupanca().atualizaCategoria(valorEnviaAntigo, valor, 1); //1 = envia pix
				clienteEnviar.getContaPoupanca().enviarPix(valor);	
				
			
				
			}
		}
	return true;
	}
	
	public boolean receberPix(String cpfReceberPix, float valor) throws ExisteContaCadastradaException{	
		
		for(ClienteEntity clienteReceber : clienteDao.buscarClientes()) {
			
			if(cpfReceberPix.equals(clienteReceber.getCpf())) {				
			
				float valorEnviaAntigo = clienteReceber.getContaPoupanca().getSaldo();
				clienteReceber.getContaPoupanca().atualizaCategoria(valorEnviaAntigo, valor, 2); //1 = envia pix
				clienteReceber.getContaPoupanca().receberPix(valor);
				
		
			}
		}		
		
		return true;
	}
	
	
	public void verSeTemContaComEsseCPF(String cpf, int tipoDeContaEscolhida) throws ContaComCPFExistenteException {
		
		/*
		 * tipoDeContaEscolhida = 1 : Conta Corrente
		 * tipoDeContaEscolhida = 2 : Conta Poupanca
		 * */
		
		if(tipoDeContaEscolhida == 2) {	
			if(contaPoupancaDao.existeContaCadastradaNoCPF(cpf, tipoDeContaEscolhida)) {
				throw new ContaComCPFExistenteException("O CPF "+cpf+" já possui uma conta corrente cadastrada.");
			}		
		} else if(tipoDeContaEscolhida == 1) {				
			TiposDeConta contaPoupanca;
			contaPoupanca = TiposDeConta.CONTA_CORRENTE;
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
				float saldoAntigo = cpe1.exibirSaldo();
				if(cliente.getCpf().equals(cpe1.getCpfClienteDaConta())) {
					 m += (float) (cpe1.getSaldo() * Math.pow(1 + cpe1.getTaxaMensal(), 12));
					 saldoNovo += m + cpe1.getAcrescimoTaxaRendimento();
					 contaPoupancaDao.atualizarSaldo(saldoNovo);
					 System.out.println("Cliente: "+cliente.getNome());
					 System.out.println("Hoje ("+dataEsperada.getDayOfMonth()+") foi acrescentada uma taxa de "+cpe1.getAcrescimoTaxaRendimento()+" da conta número "+cpe1.getNumeroDaConta()+" do cliente "+cliente.getNome()+" portador do CPF "+cpe1.getCpfClienteDaConta()+" porque está cadastrado na conta "+cpe1.getCategoriaDeConta().getTipoDaCategoria());					
					 System.out.println("Saldo Antigo: "+saldoAntigo);
					 System.out.println("Saldo novo: "+cpe1.exibirSaldo()+"\n");
				}
	
			}
		}
		
	
	
	}
}
