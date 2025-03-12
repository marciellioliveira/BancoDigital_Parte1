package br.com.marcielli.bancodigital.view;

import java.time.LocalDate;
import java.util.Scanner;


import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.exception.CpfComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.DataDeNascMenor18Exception;
import br.com.marcielli.bancodigital.exception.NomeMenor2EMaior100Exception;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;
import br.com.marcielli.bancodigital.service.CartaoDeCreditoService;
import br.com.marcielli.bancodigital.service.ClienteService;
import br.com.marcielli.bancodigital.service.ContaCorrenteService;
import br.com.marcielli.bancodigital.service.ContaPoupancaService;

public class Main { //VIEW
	
	public static void main(String[] args) throws TamanhoDoCpfException, CpfJaCadastradoException, IndexOutOfBoundsException, 
	ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException, CpfComNumerosIguaisException, ClienteNuloNoDaoException {	
		
		int opcao = -1;
		Scanner input = new Scanner(System.in);	
		int cadastro = 3;
		
		ClienteService clienteService;
		clienteService = new ClienteService();
		
		CategoriasDeConta categoriaCliente;
		categoriaCliente = CategoriasDeConta.COMUM;
		
		ContaCorrenteService contaCorrenteService = new ContaCorrenteService();
		ContaPoupancaService contaPoupancaService = new ContaPoupancaService();
		CartaoDeCreditoService cartaoDeCreditoService = new CartaoDeCreditoService();
		
		
		do {
			System.out.println();
			System.out.println("1 - ADICIONAR CLIENTE\n2 - LISTAR CLIENTES\n3 - REMOVER CLIENTES\n4 - ABRIR CONTA\n5 - VER TODAS AS CONTAS\n6 - VER TAXA/DESCONTO FUNCIONANDO\n7 - EMITIR CARTÃO\n0 - SAIR..: ");
			opcao = input.nextInt();
			
			switch (opcao) {
			case 1:
				System.out.println("\nADICIONAR\n");
				
				String cep1 = "12.630-000"; //12.630-000
				cep1 = cep1.replace(".", "").replace("-", "");
				String cidade1 = "Cachoeira Paulista";
				String estado1 = "SP";
				String rua1 = "12 de abril";
				String numero1 = "321";
				String bairro1 = "Margem Direita";
				String complemento1 = "Casa";
				
				String cpf1 = "926.579.490-96"; //926.579.490-96
				cpf1 = cpf1.replace(".", "").replace("-", "");
				String nome1 = "Marcielli Oliveira";
				LocalDate dataNascimentoDATE1  = LocalDate.of(1990, 12, 01); //1990, 12, 01
										
				Endereco endereco1 = new Endereco();
				endereco1 = new Endereco();
				endereco1.setCep(cep1);
				endereco1.setCidade(cidade1);
				endereco1.setEstado(estado1);
				endereco1.setRua(rua1);
				endereco1.setNumero(numero1);
				endereco1.setBairro(bairro1);
				endereco1.setComplemento(complemento1);
				
				clienteService.adicionarClienteEntityEmDao(cpf1, nome1, dataNascimentoDATE1, endereco1, 1, categoriaCliente);	
				
				String cep2 = "12.362-450";
				cep2 = cep2.replace(".", "").replace("-", "");
				String cidade2 = "Guaratinguetá";
				String estado2 = "SP";
				String rua2 = "9 de dezembro";
				String numero2 = "21";
				String bairro2 = "São Benedito";
				String complemento2 = "Apartamento 23";
				
				String cpf2 = "954.698.100-11"; //954.698.100-11
				cpf2 = cpf2.replace(".", "").replace("-", "");
				String 	nome2 = "João Mauricio"; //João Mauricio
				LocalDate dataNascimentoDATE2  = LocalDate.of(2005, 10, 05);	
										
				Endereco endereco2 = new Endereco();
				endereco2 = new Endereco();
				endereco2.setCep(cep2);
				endereco2.setCidade(cidade2);
				endereco2.setEstado(estado2);
				endereco2.setRua(rua2);
				endereco2.setNumero(numero2);
				endereco2.setBairro(bairro2);
				endereco2.setComplemento(complemento2);
				
				clienteService.adicionarClienteEntityEmDao(cpf2, nome2, dataNascimentoDATE2, endereco2, 2, categoriaCliente);
				
				String 	cep3 = "45.245-268"; //45.245-268
				cep3 = cep3.replace(".", "").replace("-", "");
				String 	cidade3 = "Rio de Janeiro";
				String 	estado3 = "RJ";
				String 	rua3 = "8 de novembro";
				String 	numero3 = "2";
				String 	bairro3 = "Marcos José"; //Marcos José
				String 	complemento3 = "";
				
				String 	cpf3 = "126.724.390-28"; //126.724.390-28
				cpf3 = cpf3.replace(".", "").replace("-", "");
				String 	nome3 = "Maria Luiza";
				LocalDate dataNascimentoDATE3  = LocalDate.of(1987, 02, 05);	
				
				Endereco endereco3 = new Endereco();
				endereco3 = new Endereco();
				endereco3.setCep(cep3);
				endereco3.setCidade(cidade3);
				endereco3.setEstado(estado3);
				endereco3.setRua(rua3);
				endereco3.setNumero(numero3);
				endereco3.setBairro(bairro3);
				endereco3.setComplemento(complemento3);
				
				clienteService.adicionarClienteEntityEmDao(cpf3, nome3, dataNascimentoDATE3, endereco3, 3, categoriaCliente);
		
				
				System.out.println("Clientes adicionados:\n");				
				
				for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
					System.out.println(c+"\n");
					
				
				}		
			break;
			case 2:
				System.out.println("\nLISTAR\n");
				System.out.println("\nClientes cadastrados: \n");
				for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
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
			case 4:
				System.out.println("ABRIR CONTA");
				System.out.println("\nClientes cadastrados: \n");	
				
				
				for(ClienteEntity c : clienteService.listarClientesDaoEmEntity()) {
					System.out.println(c+"\n");
				}
				
				System.out.println("Digite o CPF do cliente que deseja abrir uma conta: ");
				String abrirContaCpf = input.next();
				
				if(!contaCorrenteService.verSeCpfACadastrarJatemClienteCadastrado(abrirContaCpf)) {
					System.err.println("Você precisa digitar um CPF de um cliente já cadastrado no sistema para criar uma conta");
					
					opcao = -1;
					break;
				}
				
				System.out.println("Digite:\n1 - Abrir Conta Corrente\n2 - Abrir conta Poupança: ");
				int tipoDeContaEscolhida = input.nextInt();
				
				System.out.println("Digite o valor do primeiro depósito: ");
				float primeiroDeposito = input.nextFloat();				
			
				
				if(tipoDeContaEscolhida == 1) {
					//Conta Corrente escolhida
					TiposDeConta contaCorrente;
					contaCorrente = TiposDeConta.CONTA_CORRENTE;					
					
					contaCorrenteService.adicionarContaCorrenteEntityEmDao(abrirContaCpf, primeiroDeposito, contaCorrente, categoriaCliente);
					
					
					
				} else if(tipoDeContaEscolhida == 2) {
					//Conta Poupança escolhida
					TiposDeConta contaPoupanca;
					contaPoupanca = TiposDeConta.CONTA_POUPANCA;
					
					contaPoupancaService.adicionarContaPoupancaEntityEmDao(abrirContaCpf, primeiroDeposito, contaPoupanca, categoriaCliente);
					
				}		
				
				opcao = -1;
				
			break;
			case 5:
				System.out.println("VER TODAS AS CONTAS\n");		
				boolean naoExistemContas = false;
				
				for(ContaCorrenteEntity cc : contaCorrenteService.verContasCorrentesCadastradasDao()) {
					for(ContaPoupancaEntity cp : contaPoupancaService.verContasPoupancaCadastradasDao()) {
						if(contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0 || contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
							System.out.println("\nContas Cadastradas: \n");
						}
					}
				}			
				
				for(ContaCorrenteEntity cc : contaCorrenteService.verContasCorrentesCadastradasDao()) {
					if(contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0) {
	
						System.out.println("Contas Corrente:");
						System.out.println(cc);
						naoExistemContas = true;
					}
				}
				
				System.out.println();
				
				for(ContaPoupancaEntity cp : contaPoupancaService.verContasPoupancaCadastradasDao()) {
					if(contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
						
						System.out.println("Contas Poupança: ");
						System.out.println(cp);
						naoExistemContas = true;
					}					
				}
				
				if(naoExistemContas != true)
				System.err.println("Não existem contas cadastradas no momento.");
				System.out.println("Digite 1 para Adicionar um cliente. Após o cadastro do cliente, você poderá criar uma nova conta!\n");
				
				opcao = -1;
			break;
			case 6:
				System.out.println("VER FUNÇÃO DE TAXA E DESCONTO FUNCIONANDO");
				
				System.err.println("\n\n-----------------------------------------");
				System.out.println("Conta Corrente com valor descontado: ");
				for(ContaCorrenteEntity cc : contaCorrenteService.verContasCorrentesCadastradasDao()) {
					
					for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
						contaCorrenteService.descontarTaxaManutencaoMensal(c);
					}				
				}
				
				System.err.println("-------------------------------------------------");
				System.out.println("Conta Poupança com valor acrescentado: ");
				for(ContaPoupancaEntity cc : contaPoupancaService.verContasPoupancaCadastradasDao()) {
					
					for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
						contaPoupancaService.creditarTaxaVigenteMensal(c);
					}				
				}
				opcao = -1;
			break;
			case 7:
				System.out.println("\nEMITIR CARTÃO");
				System.out.println("Abaixo a lista de clientes que estão áptos a ter um cartão.");
				
				for(ClienteEntity c : clienteService.listarClientesDaoEmEntity()) {
					System.out.println(c+"\n");
				}				
				
				System.out.println("Digite o CPF do cliente que deseja emitir um cartão: ");
				String cpfParaEmitirCartao = input.next();
				
				if(!cartaoDeCreditoService.verSeCpfACadastrarJatemClienteCadastrado(cpfParaEmitirCartao)) {
					System.err.println("Você precisa digitar um CPF de um cliente já cadastrado no sistema para emitir um cartão");
					
					opcao = -1;
					break;
				}
				
				System.out.println("Digite uma senha para o seu cartão: ");
				String senhaCartao = input.next();
				
				System.out.println("Digite:\n1 - Emitir Cartão de Crédito\n2 - Emitir Cartão de Débito: ");
				int cartaoEscolhido = input.nextInt();
				
				if(cartaoEscolhido == 1) {
					System.out.println("Você escolheu Cartão de Crédito: ");
					
					cartaoDeCreditoService.adicionarCartaoDeCreditoEntityEmDao(cpfParaEmitirCartao, cartaoEscolhido, senhaCartao);
				}
				
				if(cartaoEscolhido == 2) {
					System.out.println("Você escolheu Cartão de Débito: ");
				}
				
				//Ver se esse CPF já tem cliente e conta cadastrado pra poder emitir o cartão
				//Se sim, perguntar qual cartão ele quer "Credito ou débito" se não, retornar falando pra ele cadastrar um cliente e uma conta primeiro

				
				opcao = -1;
			break;
			case 8:
				System.out.println("EMITIR CARTÃO DE DÉBITO");
				
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
