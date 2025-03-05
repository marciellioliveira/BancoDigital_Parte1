package br.com.marcielli.bancodigital.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.Endereco;

public class ClienteService {
	
	private ClienteDao clienteDao = new ClienteDao();
	
	public boolean adicionarClienteEntityEmDao(String cpf, String nome, String dataNascimento, Endereco endereco, int cod) {
		
		try {
			
			//Instanciar o entity para pegar o que o usuario digitou como parametro
			ClienteEntity clienteEntity = new ClienteEntity(cpf, nome, dataNascimento, endereco);
			
			//Setando os dados que recebi como parametro
			if(!validarCPF(cpf))
			{	
				if(cpf.length() > 11) {
					System.err.println("Você digitou o cpf '"+cpf+"' com "+cpf.length()+" dígitos.\nCPF deve conter 11 dígitos! Digite um CPF válido.");
				} else if (cpf.length() < 11) {
					System.err.println("Você digitou o cpf '"+cpf+"' com APENAS "+cpf.length()+" dígitos.\nCPF deve conter 11 dígitos! Digite um CPF válido.");
				}
				return false;
			}
			
			if(!validarNome(nome)) {				
				return false;				
			}
			
			if(!validarDataDeNascimento(dataNascimento)) {
				return false;
			}
			
			if(!validarCep(endereco)) {
			
				return false;
			}
			
			
		
			clienteEntity.setCpf(cpf);
			clienteEntity.setNome(nome);
			clienteEntity.setDataNascimento(dataNascimento);
			clienteEntity.setEndereco(endereco);
			
			//Adiciona os dados do endereço
			clienteDao.addCliente(clienteEntity, cod);			
			//clienteDao.buscarClientes();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}		
	}
	
	private boolean validarCep(Endereco endereco) {
		String cep=endereco.getCep();		
		String cepPart1 = "";
		String line = "-";
		String cepPart2="";
		String novoCep = "";
		
			if(cep.length() != 8) {
				System.err.println("Você digitou um cep com "+cep.length()+", sendo que um CEP deve ter 8 caracteres.");
				
				return false;
				
			}
			
			cepPart1 = cep.substring(0,5);
			cepPart2 = cep.substring(5,8);
			
			novoCep = cepPart1.concat(line).concat(cepPart2);
			
		
		
		return true;
	}

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

	private boolean validarCPF(String cpf) {	
		
		String novoCpf = validarCaracteresEspeciais(cpf);	
		
		try {
			if(!validarCpfCompleto(novoCpf)) {
	
					return false;
				}
			
			for(ClienteEntity c : clienteDao.buscarClientes()) {
				if(cpf.equals(c.getCpf())) {
					System.err.println("O cpf "+cpf+" já está cadastrado com outro usuário.\nAdicione um CPF único para cada cliente.\n");
					return false;
				}
			}
		
			return true;	
		} catch (Exception e) {
			System.err.println("Erro: "+e.getLocalizedMessage());
		}
		
		
		
		return true;
	}

	private boolean validarCpfCompleto(String novoCpf) {
		int valor = 0;
		int j = 10;
	
		if(novoCpf.length() < 11 || novoCpf.length() > 11) {
			return false;
		}
		// Fazer a validação do penultimo digito		
		
		for(int i=0; i<novoCpf.length(); i++) { 
		
			while(j>1) { 
				
				char letra = novoCpf.charAt(i);
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
			
			char penultimoDig = novoCpf.charAt(9);			
			int penultimoDigito = penultimoDig - '0';
			
			
			if(resultado == penultimoDigito) {
		
				//Validar o segundo digito				
			
				int b = 11;
			
				int valor2 = 0;
				
				for(int a=0; a<10; a++) { 
					while(b>1) { 
						char letra2 = novoCpf.charAt(a); 
						int caracter2 = letra2 - '0';
						
						valor2 += caracter2 * b; 
						
						b--;
						
						break;
					}
				}
			
			
			int resultado2 = (valor2 * 10) % 11;
			
			char ultimoDig = novoCpf.charAt(10);
			int ultimoDigito = ultimoDig - '0';
			if(resultado2 == ultimoDigito) {
				return true;
			}			
			} 
			
		}
		return false;
	}

	private String validarCaracteresEspeciais(String cpf) {
		if(cpf.contains(".") || cpf.contains("-")) {
			cpf = cpf.replace(".", "").replace("-", "");
			return cpf;
		} 		
		
		return cpf;
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
