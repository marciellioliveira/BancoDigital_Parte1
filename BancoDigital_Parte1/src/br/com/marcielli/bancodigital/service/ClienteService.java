package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;

public class ClienteService {
	
	private ClienteDao clienteDao = new ClienteDao();	
	
	@SuppressWarnings("finally")
	public void adicionarClienteEntityEmDao(String cpf, String nome, String dataNascimento, Endereco endereco, int cod) throws TamanhoDoCpfException, 
	CpfJaCadastradoException, ValidarUltimosNumerosDoCpfException, TamanhoDoCepException {
		
		ClienteEntity clienteEntity = new ClienteEntity(cpf, nome, dataNascimento, endereco);	
		
		
			try {
				
				removerCaracteresEspeciaisCpf(cpf); 							
				verTamanhoCpf(cpf);
				verCpfDuplicado(cpf);
				validarUltimosNumerosDoCpf(cpf);
				
				validarCep(endereco);
			
			} catch (CpfJaCadastradoException e) {
				
				System.err.println("CPF duplicado: "+e.getMessage());
				
			} catch (TamanhoDoCpfException e) {
				
				removerCaracteresEspeciaisCpf(cpf); 
				System.err.println("Tamanho do CPF: "+e.getMessage());
				
			} catch (ValidarUltimosNumerosDoCpfException e) {
				
				System.err.println("CPF: "+e.getMessage());
			
			} catch (TamanhoDoCepException e) {
				removerCaracteresEspeciaisCep(endereco.getCep());
				System.err.println("CEP: "+e.getMessage());
				
			} finally {
				
				clienteEntity.setCpf(cpf);
				clienteEntity.setNome(nome);
				clienteEntity.setDataNascimento(dataNascimento);
				clienteEntity.setEndereco(endereco);

				clienteDao.addCliente(clienteEntity, cod);	
				
			}
		

			
				
		
		
//		try {			
//			
//			ClienteEntity clienteEntity = new ClienteEntity(cpf, nome, dataNascimento, endereco);		
//			
//			if(!validarCPF(cpf))
//			{	
//				
//				return false;
//			}
//			
//			if(!validarNome(nome)) {				
//				return true;				
//			}
//			
//			if(!validarDataDeNascimento(dataNascimento)) {
//				return true;
//			}
//			
//			if(!validarCep(endereco)) {
//			
//				return true;
//			}
//		
//			clienteEntity.setCpf(cpf);
//			clienteEntity.setNome(nome);
//			clienteEntity.setDataNascimento(dataNascimento);
//			clienteEntity.setEndereco(endereco);
//
//			clienteDao.addCliente(clienteEntity, cod);			
//
//			return true;
//			
//		
//		} catch (CpfException e) {	
//			
//			System.out.println("Teste cpf: "+e.getCpf());
//			System.err.println("Erro: "+e.getMessage()+"\n");
//			return true;
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return true;
//		} finally {
//			
//		}
	}
	

	//Validação de CPF
	private void removerCaracteresEspeciaisCpf(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
	}
	
	private void verTamanhoCpf(String cpf) throws TamanhoDoCpfException {
		if(cpf.length() < 11 || cpf.length() > 11){
			throw new TamanhoDoCpfException("Você digitou "+cpf.length()+" para CPF. \nO CPF deve ter tamanho 11.\nDigite novamente");
		} 
	}
	
	private void verCpfDuplicado(String cpf) throws CpfJaCadastradoException {
		if(clienteDao.temCpf(cpf)) {
			throw new CpfJaCadastradoException("O cpf "+cpf+" já está cadastrado com outro usuário.\nAdicione um CPF único para cada cliente.\n");
		}
	}
	
	private void validarUltimosNumerosDoCpf(String cpf) throws ValidarUltimosNumerosDoCpfException {
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
}
	
	//Fim Validação de CPF


	//Validar CEP
	
	private void removerCaracteresEspeciaisCep(String cep) {
		cep = cep.replace(".", "").replace("-", "");
	}
	
	private void validarCep(Endereco endereco) throws TamanhoDoCepException {
		
		String cep = endereco.getCep();		
		
			if(cep.length() != 8) {				
				throw new TamanhoDoCepException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 8 caracteres sem caracteres especiais.");		
			}				
	}
	//Fim Validação de CEP


	private boolean validarDataDeNascimento(String dataNascimento) {

		if(dataNascimento.length() >= 4) {
			String anoNascimento = dataNascimento.substring(dataNascimento.length() - 4);
			int idadeAtual = Integer.parseInt(anoNascimento);
			
			LocalDate dataAtual;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			String dataAtualNova = LocalDate.now().format(formatter);
			
			if(dataAtualNova.length() >= 4) {
				String anoAtualizado = dataAtualNova.substring(dataAtualNova.length() - 4);
				int anoAtualizado2 = Integer.parseInt(anoAtualizado);
				
				if((anoAtualizado2 - idadeAtual) >= 18) {					
					return true;
				} else {
					System.out.println("O cliente que você tentou cadastrar é menor de idade.\nTente adicionar um cliente com idade maior ou igual a 18 anos.\n");
					return false;
				}
			}
		}
		
		return true;
	}

	private boolean validarNome(String nome) {
		if(nome.length() < 2 || nome.length()> 100) {
			if(nome.length() < 2) {
				System.err.println("Você tentou cadastrar um cliente chamado '"+nome+"' com apenas "+nome.length()+" caracter no nome.\nTente cadastrar um cliente com um nome MAIOR.");
			}
			
			if(nome.length() > 100) {
				System.err.println("Você tentou cadastrar um cliente chamado '"+nome+"' com "+nome.length()+" caracteres no nome.\nTente cadastrar um cliente com um nome MENOR.");
			}			
			
			return false;
		} 
		

		if(nome.contains(",") || nome.contains(".") || nome.contains("!") || nome.contains("\\") || nome.contains("\"") || nome.contains("/") || nome.contains("#") || nome.contains("$") || nome.contains("%") || nome.contains("&") || nome.contains("*") || nome.contains(":") || nome.contains(";") || nome.contains("+") || nome.contains("<") || nome.contains(">") || nome.contains("=") || nome.contains("?") || nome.contains("@") || nome.contains("[") || nome.contains("]") || nome.contains("_") || nome.contains("{") || nome.contains("}") || nome.contains("|")) {
			System.err.println("O nome não pode ter pontuações: "+nome);
			return false;
			
		}
		for(int i=0; i<nome.length(); i++) {
			
			char letra = nome.charAt(i);
			Boolean flag = Character.isDigit(letra);
			
			if(flag) {
				System.err.println("O nome '"+nome+"' digitado possui um número.\nO nome de cliente não deve possuir números, tente adicionar novamente!");
				return false;
			}
		}
		
		return true;
	}

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
