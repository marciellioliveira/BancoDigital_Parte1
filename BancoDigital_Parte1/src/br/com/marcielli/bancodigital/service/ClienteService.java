package br.com.marcielli.bancodigital.service;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.DataDeNascMenor18Exception;
import br.com.marcielli.bancodigital.exception.NomeMenor2EMaior100Exception;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;

public class ClienteService {
	
	private ClienteDao clienteDao = new ClienteDao();	
	
	@SuppressWarnings("finally")
	public void adicionarClienteEntityEmDao(String cpf, String nome, LocalDate dataNascimentoDATE, Endereco endereco, int cod) throws TamanhoDoCpfException, 
	CpfJaCadastradoException, ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException {
		
		ClienteEntity clienteEntity = new ClienteEntity(cpf, nome, dataNascimentoDATE, endereco);	
		
		
			try {
				
				removerCaracteresEspeciaisCpf(cpf); 
				
				verTamanhoCpf(cpf);
				
				verCpfDuplicado(cpf);
				
				validarUltimosNumerosDoCpf(cpf);
				
				validarCep(endereco);
				
				validarDataDeNascimento(dataNascimentoDATE);
				
				validarTamanhoNome(nome);
				
				validarCaracteresEspeciaisNome(nome);
			
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
				
			} catch (DataDeNascMenor18Exception e) {
				
				System.err.println("Data de Nascimento: "+e.getMessage());
			
			} catch (NomeMenor2EMaior100Exception e) {
				
				System.err.println("Nome: "+e.getMessage());
				
			} catch (CaracterEspecialNoNomeException e) {
				
				System.err.println("Nome: "+e.getMessage());
				
			} finally {
				
				clienteEntity.setCpf(cpf);
				clienteEntity.setNome(nome);
				clienteEntity.setDataNascimento(dataNascimentoDATE);
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
	
	
	
	//Validar Data de Nascimento
	private void validarDataDeNascimento(LocalDate dataNascimentoDATE) throws DataDeNascMenor18Exception {		
		
		int anoDeNascimento = dataNascimentoDATE.getYear();
		int anoAtual = LocalDate.now().getYear();
		
		if(anoAtual - anoDeNascimento < 18) {
			throw new DataDeNascMenor18Exception("O cliente que você tentou cadastrar é menor de idade.\\nTente adicionar um cliente com idade maior ou igual a 18 anos.\n");
		}
	
	}
	//Fim Validação Data de Nascimento
	
	//Validação do Nome
	private void validarTamanhoNome(String nome) throws NomeMenor2EMaior100Exception {
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
	
	private void validarCaracteresEspeciaisNome(String nome) throws CaracterEspecialNoNomeException {
		
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
