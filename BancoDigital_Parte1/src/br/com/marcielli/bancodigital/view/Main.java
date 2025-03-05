package br.com.marcielli.bancodigital.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.service.ClienteService;

public class Main { //VIEW

	
	public static void main(String[] args) {	
		
		int opcao = -1;
		Scanner input = new Scanner(System.in);	
		int cadastro = 3;
		ClienteService clienteService;
		
		String cep = "";
		String cidade = "";
		String estado = "";
		String rua = "";
		String numero = "";
		String bairro = "";
		String complemento = "";
		
		String cpf = "";
		String nome = "";
		LocalDate dataNascimentoDATE; 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 		
		String dataNascimento = "";

		
		
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setComplemento(complemento);
		clienteService = new ClienteService();
		
		do {
			
			System.out.println("1 - ADICIONAR\n2 - LISTAR\n3 - REMOVER\n0 - SAIR..: ");
			opcao = input.nextInt();
			
			switch (opcao) {
			case 1:
				System.out.println("\nADICIONAR\n");
				
				for(int i=0; i<cadastro; i++) {
					
					if(i == 0) {
						
						cep = "12.630-000";
						cep = cep.replace(".", "").replace("-", "");
						cidade = "Cachoeira Paulista";
						estado = "SP";
						rua = "12 de abril";
						numero = "321";
						bairro = "Margem Direita";
						complemento = "Casa";
						
						cpf = "935.125.857-45";
						cpf = cpf.replace(".", "").replace("-", "");
						
						nome = "Marcielli Oliveira";
						//dataNascimento = "01/12/1990";
						dataNascimentoDATE = LocalDate.of(1990, 12, 01);	
						dataNascimento = dataNascimentoDATE.format(formatter);
						
						endereco = new Endereco();
						endereco.setCep(cep);
						endereco.setCidade(cidade);
						endereco.setEstado(estado);
						endereco.setRua(rua);
						endereco.setNumero(numero);
						endereco.setBairro(bairro);
						endereco.setComplemento(complemento);
					}
					
					if(i == 1) {
						
						cep = "12.362-450";
						cep = cep.replace(".", "").replace("-", "");
						cidade = "Guaratinguetá";
						estado = "SP";
						rua = "9 de dezembro";
						numero = "21";
						bairro = "São Benedito";
						complemento = "Apartamento 23";
						
						cpf = "405.288.008-09"; //405.288.008-09
						cpf = cpf.replace(".", "").replace("-", "");
						
						nome = "João Mauricio"; //João Mauricio
						//dataNascimento = "05/10/2005";
						dataNascimentoDATE = LocalDate.of(2005, 10, 05);	
						dataNascimento = dataNascimentoDATE.format(formatter);
						
						endereco = new Endereco();
						endereco.setCep(cep);
						endereco.setCidade(cidade);
						endereco.setEstado(estado);
						endereco.setRua(rua);
						endereco.setNumero(numero);
						endereco.setBairro(bairro);
						endereco.setComplemento(complemento);
					}
					
					if(i == 2) {
						
						cep = "45.245-268";
						cep = cep.replace(".", "").replace("-", "");
						cidade = "Rio de Janeiro";
						estado = "RJ";
						rua = "8 de novembro";
						numero = "2";
						bairro = "Marcos José"; //Marcos José
						complemento = "";
						
						cpf = "996.660.620-38";
						cpf = cpf.replace(".", "").replace("-", "");
						
						nome = "Maria Luiza";
						//dataNascimento = "05/02/1987";
						dataNascimentoDATE = LocalDate.of(1987, 02, 05);	
						dataNascimento = dataNascimentoDATE.format(formatter);
						
						endereco = new Endereco();
						endereco.setCep(cep);
						endereco.setCidade(cidade);
						endereco.setEstado(estado);
						endereco.setRua(rua);
						endereco.setNumero(numero);
						endereco.setBairro(bairro);
						endereco.setComplemento(complemento);
					}
					
					clienteService.adicionarClienteEntityEmDao(cpf, nome, dataNascimento, endereco, i);
				}
				
				System.out.println("Clientes adicionados:\n");
			
				for(ClienteEntity c : clienteService.listarClientesDaoEmEntity()) {
					System.out.println("Nome: "+c.getNome()+"\nCPF: "+c.getCpf()+"\nData de Nascimento: "+c.getDataNascimento()+"\nEndereço: "+c.getEndereco()+"\n");
				}				
			break;
			case 2:
				System.out.println("\nLISTAR\n");
				System.out.println("\nClientes cadastrados: \n");
				for(ClienteEntity c : clienteService.listarClientesDaoEmEntity()) {
					System.out.println(c+"\n");
				}
					
				opcao = -1;
			break;
			case 3:
				System.out.println("REMOVER");
				System.out.println("\nClientes cadastrados: \n");		
				
				for(ClienteEntity c : clienteService.listarClientesDaoEmEntity()) {
					System.out.println(c+"\n");
				}
				
				System.out.println("Digite o CPF do cliente que deseja remover: ");
				String removerCPF = input.next();
			
				System.out.println("Tem certeza de que deseja remover cliente com CPF: "+removerCPF+" ?\nDigite S = SIM ou N = NÃO..: ");
				String confirmaRemocao = input.next().toLowerCase();
				
				if(confirmaRemocao.equals("s")) {
					if(clienteService.removerCliente(removerCPF)) {
						System.out.println("Conta do cliente titular do cpf n° "+removerCPF+" foi removida com sucesso!\n");
					} else {
						System.out.println("O cliente não foi removido! Tente novamente");
					}
					
				} else if (confirmaRemocao.equals("n")) {
					
					System.out.println("O cliente não foi removido!");
					
				}			
				
				opcao = -1;
			break;
			case 0:
				System.out.println("PROGRAMA FINALIZADO COM SUCESSO!");
			break;
			default:
				
			}
			
		} while (opcao != 0);
		
	}
}
