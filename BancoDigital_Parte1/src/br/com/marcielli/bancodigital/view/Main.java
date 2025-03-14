package br.com.marcielli.bancodigital.view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.AnoNascimentoDiferente4Exception;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.CidadeComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
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
import br.com.marcielli.bancodigital.helpers.TiposDeConta;
import br.com.marcielli.bancodigital.service.CartaoDeCreditoService;
import br.com.marcielli.bancodigital.service.ClienteService;
import br.com.marcielli.bancodigital.service.ContaCorrenteService;
import br.com.marcielli.bancodigital.service.ContaPoupancaService;

public class Main { //VIEW
	
	public static void main(String[] args) throws TamanhoDoCpfException, CpfJaCadastradoException, IndexOutOfBoundsException, 
	ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException, CpfComNumerosIguaisException, ClienteNuloNoDaoException, AnoNascimentoDiferente4Exception, DiaEMesNascimentoDiferente2Exception, MesmosCaracteresEmStringException, CidadeComNumerosIguaisException {	
		
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
		
		boolean flagNome = true, flagCpf = true, flagAnoNascimento = true, flagMesNascimento = true, flagDiaNascimento = true, flagCep = true, flagCidade = true, flagEstado= true, flagRua= true, flagNumero= true, flagBairro= true, flagComplemento= true;
		String nome = null, cpf = null, cep = null, cidade = null, estado = null, rua = null, numero = null, bairro = null, complemento = null;
		int anoNascimento = 0, mesNascimento = 0, diaNascimento = 0;
		Endereco endereco = new Endereco();
		
		int i=1;
		do {
			System.out.println();
			System.out.println("1 - ADICIONAR CLIENTE\n2 - LISTAR CLIENTES\n3 - REMOVER CLIENTES\n4 - ABRIR CONTA\n5 - VER TODAS AS CONTAS\n6 - VER TAXA/DESCONTO FUNCIONANDO\n7 - EMITIR CARTÃO\n0 - SAIR..: ");
			opcao = input.nextInt();
			input.nextLine();
			
			switch (opcao) {
			case 1:
				System.out.println("\nADICIONAR\n");
				
				while(flagNome) {
					try {						
						System.out.println("Digite o nome: ");	
						nome = input.nextLine();	
						clienteService.validarNome(nome);
						flagNome = false;
					} catch (NomeMenor2EMaior100Exception e) {
						System.err.println("\nErro: "+e.getMessage());
						flagNome = true;
						
					} catch (CaracterEspecialNoNomeException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagNome = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagNome = true;		
						input.nextLine();
					}	
				}
				
				while(flagCpf) {
					try {						
						System.out.println("\nO CPF deve conter pontos e traços!");
						System.out.println("Digite o cpf - EX: 353.321-854-21: ");	
						cpf = input.nextLine();	
						clienteService.validarCpf(cpf);
						flagCpf = false;
					} catch (TamanhoDoCpfException e) {
						System.err.println("\nErro: "+e.getMessage());
						flagCpf = true;
						
					} catch (CpfJaCadastradoException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCpf = true;
					} catch (ValidarUltimosNumerosDoCpfException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCpf = true;
					} catch (CpfComNumerosIguaisException  e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCpf = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCpf = true;		
						input.nextLine();
					}	
				}
				
				while(flagAnoNascimento) { 
					try {						
						System.out.println("\nDigite o ano do seu nascimento: ");	
						String ano = input.nextLine();
						clienteService.validarAnoNascimento(ano);
						anoNascimento = Integer.parseInt(ano);
						flagAnoNascimento = false;
						
					} catch (AnoNascimentoDiferente4Exception e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagAnoNascimento = true;					
					}  catch (DataDeNascMenor18Exception e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagAnoNascimento = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagAnoNascimento = true;		
						input.nextLine();
					} finally {
						
					}					
				}
				
				while(flagMesNascimento) { 
					try {						
						System.out.println("Digite o mês do seu nascimento: EX: 12 ");	
						String mes = input.nextLine();
						clienteService.validarMesNascimento(mes);
						mesNascimento = Integer.parseInt(mes);
						flagMesNascimento = false;
						
					}  catch (DiaEMesNascimentoDiferente2Exception e) {
						System.err.println("Erro: "+e.getMessage());	
						flagMesNascimento = true;
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagMesNascimento = true;		
						input.nextLine();
					}	
				}
				
				while(flagDiaNascimento) { 
					try {						
						System.out.println("Digite o dia do seu nascimento: EX: 01 ");	
						String dia = input.nextLine();
						clienteService.validarDiaNascimento(dia);
						diaNascimento = Integer.parseInt(dia);
						flagDiaNascimento = false;
						
					}  catch (DiaEMesNascimentoDiferente2Exception e) {
						System.err.println("Erro: "+e.getMessage());	
						flagDiaNascimento = true;
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagDiaNascimento = true;		
						input.nextLine();
					}	
				}
				
				LocalDate dataNascimentoDATE  = LocalDate.of(anoNascimento, mesNascimento, diaNascimento); //1990, 12, 01
				
				while(flagCep) { //Validar direito a logica
					try {						
						System.out.println("O CEP deve conter traço antes dos 3 últimos dígitos");
						System.out.println("Digite o cep - EX: 12630-000: ");	
						cep = input.nextLine();	
						clienteService.validarCep(cep);
						endereco.setCep(cep);
						flagCep = false;	
					} catch (TamanhoDoCepException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCep = true;	
					} catch (CpfComNumerosIguaisException  e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCep = true;
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCep = true;		
						input.nextLine();
					}	
				}			
				
				
				while(flagCidade) { 
					try {						
						System.out.println("Digite sua cidade: ");	
						cidade = input.nextLine();	
						clienteService.validarCidade(cidade);
						endereco.setCidade(cidade);
						flagCidade = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCidade = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCidade = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagCidade = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagEstado) { 
					try {						
						System.out.println("Digite seu estado: ");	
						estado = input.nextLine();	
						clienteService.validarCidade(estado);
						endereco.setEstado(estado);
						flagEstado = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagEstado = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagEstado = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagEstado = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagRua) { 
					try {						
						System.out.println("Digite sua rua: ");	
						rua = input.nextLine();	
						clienteService.validarRua(rua);
						endereco.setRua(rua);
						flagRua = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagRua = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagRua = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagRua = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagNumero) { 
					try {						
						System.out.println("Digite o número: ");	
						numero = input.nextLine();	
						clienteService.validarNumero(numero);
						endereco.setNumero(numero);
						flagNumero = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagNumero = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagNumero = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagNumero = true;		
						input.nextLine();
					}	
				}
				
				endereco.setNumero(numero);
				
				while(flagBairro) { 
					try {						
						System.out.println("Digite o bairro: ");	
						bairro = input.nextLine();	
						clienteService.validarBairro(bairro);
						endereco.setBairro(bairro);
						flagBairro = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagBairro = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagBairro = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagBairro = true;		
						input.nextLine();
					}	
				}
				
				while(flagComplemento) { 
					try {						
						System.out.println("Digite o complemento: ");	
						complemento = input.nextLine();	
						clienteService.validarBairro(complemento);
						endereco.setComplemento(complemento);
						flagComplemento = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagComplemento = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagComplemento = true;	
					} catch (InputMismatchException e) {
						System.err.println("Erro: "+e.getMessage());	
						flagComplemento = true;		
						input.nextLine();
					}	
				}
				
				if(clienteService.adicionarClienteEntityEmDao(cpf, nome, dataNascimentoDATE, endereco, categoriaCliente)) {
					flagNome = true;
					flagCpf = true;
					flagAnoNascimento = true;
					flagMesNascimento = true;
					flagDiaNascimento = true;
					flagCep = true;
					flagCidade = true;
					flagEstado= true;
					flagRua= true;
					flagNumero= true;
					flagBairro= true;
					flagComplemento= true;
				}
											
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
				
				System.out.println();
				
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
				
				if(naoExistemContas != true) {
					System.err.println("Não existem contas cadastradas no momento.");
					System.out.println("Digite 1 para Adicionar um cliente. Após o cadastro do cliente, você poderá criar uma nova conta!\n");
				}
				
				
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