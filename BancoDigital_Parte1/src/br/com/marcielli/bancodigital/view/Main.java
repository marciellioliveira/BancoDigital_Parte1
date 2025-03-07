package br.com.marcielli.bancodigital.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.DataDeNascMenor18Exception;
import br.com.marcielli.bancodigital.exception.NomeMenor2EMaior100Exception;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.service.ClienteService;

public class Main { //VIEW

	
	public static void main(String[] args) throws TamanhoDoCpfException, CpfJaCadastradoException, IndexOutOfBoundsException, 
	ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException {	
		
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
		LocalDate dataNascimentoDATE = LocalDate.of(1990, 10, 05);
		
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
						
						cep = "12.630-000"; //12.630-000
						cep = cep.replace(".", "").replace("-", "");
						cidade = "Cachoeira Paulista";
						estado = "SP";
						rua = "12 de abril";
						numero = "321";
						bairro = "Margem Direita";
						complemento = "Casa";
						
						cpf = "926.579.490-96"; //926.579.490-96
						cpf = cpf.replace(".", "").replace("-", "");
						nome = "Marcielli Oliveira";
						dataNascimentoDATE = LocalDate.of(1990, 12, 01); //1990, 12, 01	
						
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
						
						cpf = "954.698.100-11"; //954.698.100-11
						cpf = cpf.replace(".", "").replace("-", "");
						nome = "João Mauricio"; //João Mauricio
						dataNascimentoDATE = LocalDate.of(2005, 10, 05);	
						
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
						
						cpf = "126.724.390-28"; //126.724.390-28
						cpf = cpf.replace(".", "").replace("-", "");
						nome = "Maria Luiza";
						dataNascimentoDATE = LocalDate.of(1987, 02, 05);	
						
						endereco = new Endereco();
						endereco.setCep(cep);
						endereco.setCidade(cidade);
						endereco.setEstado(estado);
						endereco.setRua(rua);
						endereco.setNumero(numero);
						endereco.setBairro(bairro);
						endereco.setComplemento(complemento);
					}
					
					clienteService.adicionarClienteEntityEmDao(cpf, nome, dataNascimentoDATE, endereco, i);
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
