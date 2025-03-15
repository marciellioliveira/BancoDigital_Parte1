package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoDeDebitoEntity;
import br.com.marcielli.bancodigital.entity.CartaoEntity;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.ContasDoCliente;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.AnoNascimentoDiferente4Exception;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.CidadeComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.DataDeNascMenor18Exception;
import br.com.marcielli.bancodigital.exception.DiaEMesNascimentoDiferente2Exception;
import br.com.marcielli.bancodigital.exception.MesmosCaracteresEmStringException;
import br.com.marcielli.bancodigital.exception.NomeMenor2EMaior100Exception;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ClienteService {
	
	//private ClienteDao clienteDao = new ClienteDao();	
	ClienteDao clienteDao = ClienteDao.getInstancia();
	
	@SuppressWarnings("finally")
	public boolean adicionarClienteEntityEmDao(String cpf, String nome, LocalDate dataNascimentoDATE, Endereco endereco, CategoriasDeConta categoriaCliente) throws TamanhoDoCpfException, 
	CpfJaCadastradoException, ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException, CpfComNumerosIguaisException {


		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setCpf(cpf);
		clienteEntity.setNome(nome);
		clienteEntity.setDataNascimento(dataNascimentoDATE);
		clienteEntity.setEndereco(endereco);
		clienteEntity.setCategoriaDeConta(categoriaCliente);
		
		//Começando as contas com dados inicias 
		
//		CartaoDeCreditoEntity cartaoDeCreditoCad = new CartaoDeCreditoEntity("", nome, cpf, TiposDeConta.CONTA_CADASTRO,
//				categoriaCliente, TipoDeCartao.CARTAO_CADASTRO, false, "0");
//		clienteEntity.setCartaoDeCredito(cartaoDeCreditoCad);
//		
//		CartaoDeDebitoEntity cartaoDeDebitoCad = new CartaoDeDebitoEntity("", nome, cpf, TiposDeConta.CONTA_CADASTRO, categoriaCliente, TipoDeCartao.CARTAO_CADASTRO, false, "0");
//		clienteEntity.setCartaoDeDebito(cartaoDeDebitoCad);
		
	
		
		if(clienteDao.buscarClientes().size() == 0) {
			clienteDao.adicionarCliente(clienteEntity);
			
			return true;
		}
	
		for(ClienteEntity cli : clienteDao.buscarClientes()) {
			String cpfParaValidar = cli.getCpf().replace("-", "").replace(".", "");
			if(!cpf.equals(cpfParaValidar)) {
				clienteDao.adicionarCliente(clienteEntity);
				return true;
				
				
			}else {
				return false;
//				System.err.println("CPF: "+cpf);
//				System.err.println("CLI GETCPF: "+cpfParaValidar);
			}
		}
		
		return false;
						
	}
	
	public ArrayList<ClienteEntity> verClientesCadastradosDao() {
		return clienteDao.verClientesCadastrados();
	}
	
	
	
	
	//public void verExisteClienteComCpf(String cpf) {	
		
		
		//System.err.println("CPF "+cpf);
//		for(ClienteEntity c : clienteDao.buscarClientes()) {
//			if(cpf.equals(c.getCpf())) {
//				System.err.println("CPF: "+cpf);
//				System.err.println("C CPF: "+c.getCpf());
//			}
//		}
//		System.err.println("Teste");
		
		//return clienteDao.buscarClienteComCpfCadastrado(cpf);	
	//}
	
//	public ClienteEntity verExisteClienteComCpf(String cpf) {		
//		return clienteDao.buscarClienteComCpfCadastrado(cpf);	
//	}
	
	public boolean clienteExisteNoDao(String cpf) {
		if(clienteDao.temCpf(cpf)) {			
			return true;
		}
		return false;
	}
	
	public ArrayList<ContaEntity> clienteTemContasNoDao(String cpf) {
		
		ArrayList<ContaEntity> contasDoCliente = new ArrayList<ContaEntity>();	
		
		for(ClienteEntity c : ClienteDao.getInstancia().buscarClientes()) {
			
			if(cpf.equals(c.getCpf())) {
				if(c.getContaCorrente() != null) {
					contasDoCliente.add(c.getContaCorrente());
				}
				if(c.getContaPoupanca() != null) {
					contasDoCliente.add(c.getContaPoupanca());
				}
			}
			
			
		}
		
		return contasDoCliente;
	}
	
	public ArrayList<ContaEntity> clienteTemContasNoDao(String cpf, String conta) {
		
		ArrayList<ContaEntity> contasDoCliente = new ArrayList<ContaEntity>();	
		
		for(ClienteEntity c : ClienteDao.getInstancia().buscarClientes()) {
			
			if(cpf.equals(c.getCpf())) {
				
				if(c.getContaCorrente() != null && conta.equals(c.getContaCorrente().getNumeroDaConta())) {
					contasDoCliente.add(c.getContaCorrente());
				}
				if(c.getContaPoupanca() != null && conta.equals(c.getContaPoupanca().getNumeroDaConta())) {
					contasDoCliente.add(c.getContaPoupanca());
				}
			}
			
			
		}
		
		return contasDoCliente;
	}
	
	
	
	
	
	public void validarCpfSemAutenticar(String cpf) throws TamanhoDoCpfException, ValidarUltimosNumerosDoCpfException, CpfComNumerosIguaisException  {
		
		if(!cpf.contains(".") && cpf.contains("-")) {
			if(cpf.length() < 14 || cpf.length() > 14){
				throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14..\nDigite novamente o CPF com pontos e traços. EX: 12.630-000");
			} 
		}
		
		if(cpf.length() < 14 || cpf.length() > 14){
			throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14.\nDigite novamente");
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
	
	
	public void validarCpf(String cpf) throws TamanhoDoCpfException, CpfJaCadastradoException, ValidarUltimosNumerosDoCpfException, CpfComNumerosIguaisException  {
				
		if(!cpf.contains(".") && cpf.contains("-")) {
			if(cpf.length() < 14 || cpf.length() > 14){
				throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14..\nDigite novamente o CPF com pontos e traços. EX: 12.630-000");
			} 
		}
		
		if(cpf.length() < 14 || cpf.length() > 14){
			throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 14.\nDigite novamente");
		} 
		
		if(clienteDao.temCpf(cpf)) {
			throw new CpfJaCadastradoException("O cpf "+cpf+" já está cadastrado com outro usuário.\nAdicione um CPF único para cada cliente.\n");
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
	
	//Fim Validação de CPF


	//Validar CEP
	
	public void validarCep(String cep) throws TamanhoDoCepException, CpfComNumerosIguaisException {
	
			//cep = cep.replace(".", "").replace("-", "");
		
		if(!cep.contains("-")) {
			if(cep.length() != 9) {				
				throw new TamanhoDoCepException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 9 caracteres com traço no final.");		
			}	
		} 
		
		if(cep.length() != 9) {				
			throw new TamanhoDoCepException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 9 caracteres com traço no final.");		
		}	
			
//			if(cep.length() != 8) {				
//				throw new TamanhoDoCepException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 8 caracteres sem caracteres especiais.");		
//			}	
			
			
			int numOcorrencias = 1;		
			HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
			String novoCep = "";
			
			
			char[] meuCep = cep.toCharArray();
			
			for(int i=0; i<meuCep.length; i++) {
				if(!findDuplicated.containsKey(meuCep[i])) {
					findDuplicated.put(meuCep[i], 1);
					novoCep += meuCep[i];
					
				} else {
					findDuplicated.put(meuCep[i], 1);
					numOcorrencias++; 
				}
			}
			
			if(numOcorrencias >= 8) {
				throw new CpfComNumerosIguaisException("O CEP '"+cep+"' digitado não é válido. Por favor, digite um CEP válido.");
			}
	}
	
	public void validarCidade(String cidade) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(cidade.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar uma cidade existente!");
		}
		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novaCidade = "";
		
		
		char[] minhaCidade = cidade.toCharArray();
		
		for(int i=0; i<minhaCidade.length; i++) {
			if(!findDuplicated.containsKey(minhaCidade[i])) {
				findDuplicated.put(minhaCidade[i], 1);
				novaCidade += minhaCidade[i];
				
			} else {
				findDuplicated.put(minhaCidade[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("A cidade '"+cidade+"' digitada não é válida. Por favor, digite uma cidade válida.");
		}		
	}
	
	public void validarEstado(String estado) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(estado.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar um estado existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoEstado = "";
		
		
		char[] meuEstado = estado.toCharArray();
		
		for(int i=0; i<meuEstado.length; i++) {
			if(!findDuplicated.containsKey(meuEstado[i])) {
				findDuplicated.put(meuEstado[i], 1);
				novoEstado += meuEstado[i];
				
			} else {
				findDuplicated.put(meuEstado[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("O estado '"+estado+"' digitada não é válido. Por favor, digite um estado válido.");
		}		
	}
	
	public void validarRua(String rua) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(rua.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar uma rua existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novaRua = "";
		
		
		char[] minhaRua = rua.toCharArray();
		
		for(int i=0; i<minhaRua.length; i++) {
			if(!findDuplicated.containsKey(minhaRua[i])) {
				findDuplicated.put(minhaRua[i], 1);
				novaRua += minhaRua[i];
				
			} else {
				findDuplicated.put(minhaRua[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("A rua'"+rua+"' digitada não é válida. Por favor, digite uma rua válida.");
		}		
	}
	
	public void validarNumero(String numero) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(numero.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar uma número existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoNumero = "";
		
		
		char[] meuNumero = numero.toCharArray();
		
		for(int i=0; i<meuNumero.length; i++) {
			if(!findDuplicated.containsKey(meuNumero[i])) {
				findDuplicated.put(meuNumero[i], 1);
				novoNumero += meuNumero[i];
				
			} else {
				findDuplicated.put(meuNumero[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("O número '"+numero+"' digitado não é válido. Por favor, digite um número válido.");
		}		
	}
	
	public void validarBairro(String bairro) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(bairro.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar um bairro existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoBairro = "";
		
		
		char[] meuBairro = bairro.toCharArray();
		
		for(int i=0; i<meuBairro.length; i++) {
			if(!findDuplicated.containsKey(meuBairro[i])) {
				findDuplicated.put(meuBairro[i], 1);
				novoBairro += meuBairro[i];
				
			} else {
				findDuplicated.put(meuBairro[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("O bairro '"+bairro+"' digitado não é válido. Por favor, digite um bairro válido.");
		}		
	}
	
	public void validarComplemento(String complemento) throws MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {
		String pattern = "([a-zA-Z])\\1*";
		if(complemento.matches(pattern)) {
			throw new MesmosCaracteresEmStringException("Tente cadastrar um complemento existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoComplemento = "";
		
		
		char[] meuComplemento = complemento.toCharArray();
		
		for(int i=0; i<meuComplemento.length; i++) {
			if(!findDuplicated.containsKey(meuComplemento[i])) {
				findDuplicated.put(meuComplemento[i], 1);
				novoComplemento += meuComplemento[i];
				
			} else {
				findDuplicated.put(meuComplemento[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			throw new CidadeComNumerosIguaisException("O complemento '"+complemento+"' digitado não é válido. Por favor, digite um complemento válido.");
		}		
	}

	//Fim Validação de CEP
	
	
	
	//Validar Data de Nascimento

	public void validarAnoNascimento(String anoNac) throws DataDeNascMenor18Exception, AnoNascimentoDiferente4Exception {	
		
		if(anoNac.length() != 4) {
			throw new AnoNascimentoDiferente4Exception("Você digitou o número: "+anoNac+". O ano deve ter 4 dígitos. Exemplo: 1990");
		}
		
		int anoDeNascimento = Integer.parseInt(anoNac);
		
		int anoAtual = LocalDate.now().getYear();
		
		int minhaIdade = anoAtual - anoDeNascimento;
		
		
		if(minhaIdade < 18) {
			throw new DataDeNascMenor18Exception("O cliente que você tentou cadastrar é menor de idade.\\nTente adicionar um cliente com idade maior ou igual a 18 anos.\n");
		}
		
		if(anoDeNascimento < 1900) {
			throw new DataDeNascMenor18Exception("\nIdade avançada. ");
		}

	}
	
	public void validarMesNascimento (String mesNac) throws DiaEMesNascimentoDiferente2Exception {		
		
		if(mesNac.length() != 2) {
			throw new DiaEMesNascimentoDiferente2Exception("Você digitou o número: "+mesNac+". O mês de nascimento deve ter 2 dígitos. Exemplo: 01");
		}

	}
	
	public void validarDiaNascimento (String diaNasc) throws DiaEMesNascimentoDiferente2Exception {	
		
		if(diaNasc.length() != 2) {
			throw new DiaEMesNascimentoDiferente2Exception("Você digitou o número: "+diaNasc+". O dia de nascimento deve ter 2 dígitos. Exemplo: 01");
		}

	}
	//Fim Validação Data de Nascimento
	
	//Validação do Nome	
	public void validarNome(String nome) throws CaracterEspecialNoNomeException, NomeMenor2EMaior100Exception {
		
		for(int i=0; i<nome.length(); i++) {			
			char letra = nome.charAt(i);
			Boolean flag = Character.isDigit(letra);
			
			if(flag) {
				throw new CaracterEspecialNoNomeException("O nome '"+nome+"' digitado possui número.\nO nome de cliente deve conter apenas letras, tente adicionar novamente!");
			}
		}			
		
		if(nome.contains(",") || nome.contains(".") || nome.contains("!") || nome.contains("\\") || nome.contains("\"") || nome.contains("/") || nome.contains("#") || nome.contains("$") || nome.contains("%") || nome.contains("&") || nome.contains("*") || nome.contains(":") || nome.contains(";") || nome.contains("+") || nome.contains("<") || nome.contains(">") || nome.contains("=") || nome.contains("?") || nome.contains("@") || nome.contains("[") || nome.contains("]") || nome.contains("_") || nome.contains("{") || nome.contains("}") || nome.contains("|")) {
			throw new CaracterEspecialNoNomeException("O nome '"+nome+"' digitado possui caracter especial.\nO nome de cliente deve conter apenas letras, tente adicionar novamente!");
		}	
		
		String tam = "";
		
		if(nome.length() < 2 || nome.length()> 100) {
			
			if(nome.length() < 2) {
				tam = "MAIOR";
			}
			
			if(nome.length() > 2) {
				tam = "MENOR";
			}
			
			throw new NomeMenor2EMaior100Exception("Você tentou cadastrar um cliente chamado '"+nome+"' com "+nome.length()+" caracter no nome.\nTente cadastrar um cliente com um nome "+tam+".");
		}	
		
	}	
	//Fim Validação do Nome
		

	public ArrayList<ClienteEntity> listarClientesDaoEmEntity() {
		
			return clienteDao.buscarClientes();
		
	}
	
	public boolean removerCliente(String cpf) {
		if(clienteDao.removerCliente(cpf)) {
			return true;
		}
		return false;
	}
}