package br.com.marcielli.bancodigital.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoDeDebitoEntity;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.entity.Endereco;
import br.com.marcielli.bancodigital.exception.AnoNascimentoDiferente4Exception;
import br.com.marcielli.bancodigital.exception.CaracterEspecialNoNomeException;
import br.com.marcielli.bancodigital.exception.CidadeComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.ClienteNuloNoDaoException;
import br.com.marcielli.bancodigital.exception.ContaComCPFExistenteException;
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
import br.com.marcielli.bancodigital.service.CartaoDeDebitoService;
import br.com.marcielli.bancodigital.service.ClienteService;
import br.com.marcielli.bancodigital.service.ContaCorrenteService;
import br.com.marcielli.bancodigital.service.ContaPoupancaService;

public class Main { //VIEW
	
	public static void main(String[] args) throws TamanhoDoCpfException, CpfJaCadastradoException, IndexOutOfBoundsException, 
	ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, CaracterEspecialNoNomeException, CpfComNumerosIguaisException, ClienteNuloNoDaoException, AnoNascimentoDiferente4Exception, DiaEMesNascimentoDiferente2Exception, MesmosCaracteresEmStringException, CidadeComNumerosIguaisException, ContaComCPFExistenteException {	
		
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
		CartaoDeDebitoService cartaoDeDebitoService = new CartaoDeDebitoService();
		
		boolean emitirCartao = false, flagEscolhaConta = true, flagCpfAbrirConta = true, flagMenu = true, flagNome = true, flagCpf = true, flagAnoNascimento = true, flagMesNascimento = true, flagDiaNascimento = true, flagCep = true, flagCidade = true, flagEstado= true, flagRua= true, flagNumero= true, flagBairro= true, flagComplemento= true;
		String cpfParaEmitirCartao = null, escolhaConta = null, abrirContaCpf = null, nome = null, cpf = null, cep = null, cidade = null, estado = null, rua = null, numero = null, bairro = null, complemento = null;
		int cartaoEscolhido = 0, tipoDeContaEscolhida =0, anoNascimento = 0, mesNascimento = 0, diaNascimento = 0;
		Endereco endereco = new Endereco();
		 
		int i=1;
		do {
			
				try {
					System.out.println();
					System.out.println("1 - ADICIONAR CLIENTE\n2 - LISTAR CLIENTES\n3 - REMOVER CLIENTES\n4 - ABRIR CONTA\n5 - VER CONTAS/CARTÕES\n6 - VER TAXA/DESCONTO FUNCIONANDO\n7 - EMITIR CARTÃO\n0 - SAIR..: ");
					opcao = input.nextInt();				
					input.nextLine();
				} catch (Exception e) {
					opcao = -1;
					input.nextLine();
				}
			
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
						System.out.println("\nDigite o ano do seu nascimento: EX: 1990 ");	
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
						System.out.println("\nDigite o mês do seu nascimento: EX: 12 ");	
						String mes = input.nextLine();
						clienteService.validarMesNascimento(mes);
						mesNascimento = Integer.parseInt(mes);
						flagMesNascimento = false;
						
					}  catch (DiaEMesNascimentoDiferente2Exception e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagMesNascimento = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagMesNascimento = true;		
						input.nextLine();
					}	
				}
				
				while(flagDiaNascimento) { 
					try {						
						System.out.println("\nDigite o dia do seu nascimento: EX: 01 ");	
						String dia = input.nextLine();
						clienteService.validarDiaNascimento(dia);
						diaNascimento = Integer.parseInt(dia);
						flagDiaNascimento = false;
						
					}  catch (DiaEMesNascimentoDiferente2Exception e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagDiaNascimento = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagDiaNascimento = true;		
						input.nextLine();
					}	
				}
				
				LocalDate dataNascimentoDATE  = LocalDate.of(anoNascimento, mesNascimento, diaNascimento); //1990, 12, 01
				
				while(flagCep) { //Validar direito a logica
					try {						
						System.out.println("\nO CEP deve conter traço antes dos 3 últimos dígitos");
						System.out.println("Digite o cep - EX: 12630-000: ");	
						cep = input.nextLine();	
						clienteService.validarCep(cep);
						endereco.setCep(cep);
						flagCep = false;	
					} catch (TamanhoDoCepException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCep = true;	
					} catch (CpfComNumerosIguaisException  e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCep = true;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCep = true;		
						input.nextLine();
					}	
				}			
				
				
				while(flagCidade) { 
					try {						
						System.out.println("\nDigite sua cidade: ");	
						cidade = input.nextLine();	
						clienteService.validarCidade(cidade);
						endereco.setCidade(cidade);
						flagCidade = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCidade = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCidade = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagCidade = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagEstado) { 
					try {						
						System.out.println("\nDigite seu estado: ");	
						estado = input.nextLine();	
						clienteService.validarCidade(estado);
						endereco.setEstado(estado);
						flagEstado = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagEstado = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagEstado = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagEstado = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagRua) { 
					try {						
						System.out.println("\nDigite sua rua: ");	
						rua = input.nextLine();	
						clienteService.validarRua(rua);
						endereco.setRua(rua);
						flagRua = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagRua = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagRua = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagRua = true;		
						input.nextLine();
					}	
				}
				
				
				
				while(flagNumero) { 
					try {						
						System.out.println("\nDigite o número: ");	
						numero = input.nextLine();	
						clienteService.validarNumero(numero);
						endereco.setNumero(numero);
						flagNumero = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagNumero = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagNumero = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagNumero = true;		
						input.nextLine();
					}	
				}
				
				endereco.setNumero(numero);
				
				while(flagBairro) { 
					try {						
						System.out.println("\nDigite o bairro: ");	
						bairro = input.nextLine();	
						clienteService.validarBairro(bairro);
						endereco.setBairro(bairro);
						flagBairro = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagBairro = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagBairro = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagBairro = true;		
						input.nextLine();
					}	
				}
				
				while(flagComplemento) { 
					try {						
						System.out.println("\nDigite o complemento: ");	
						complemento = input.nextLine();	
						clienteService.validarComplemento(complemento);
						endereco.setComplemento(complemento);
						flagComplemento = false;		
					} catch (MesmosCaracteresEmStringException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagComplemento = true;	
					} catch (CidadeComNumerosIguaisException e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagComplemento = true;	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: "+e.getMessage());	
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
				
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					System.out.println("Cliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nCategoria da Conta: "+contasCliente.getCategoriaContaCliente()+"\nEndereço: "+contasCliente.getEndereco()+"\n");
				}
			break;
			case 2:
				System.out.println("\nLISTAR");
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
					System.out.println("\nClientes cadastrados: \n");
				
				
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					System.out.println("Cliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nCategoria da Conta: "+contasCliente.getCategoriaContaCliente()+"\nEndereço: "+contasCliente.getEndereco());
					if(!(contasCliente.getContaCorrente() == null)) {
						System.out.println(contasCliente.getContaCorrente());
					} 
					
					if(!(contasCliente.getContaPoupanca() == null)) {
						System.out.println(contasCliente.getContaPoupanca());
					} 
					
					if(!(contasCliente.getCartaoDeCredito() == null)) {
						System.out.println(contasCliente.getCartaoDeCredito());
					}
					
					if(!(contasCliente.getCartaoDeDebito() == null)) {
						System.out.println(contasCliente.getCartaoDeDebito());
					}
					
					System.out.println();
					
				}
				}else {
					System.err.println("Você precisa ter um cliente cadastrado para realizar essa ação!");
				}
				opcao = -1;
			break;
			case 3:
				System.out.println("REMOVER");
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {	
				
					System.out.println("\nClientes cadastrados: \n");		
					
					for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
						System.out.println("Cliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nCategoria da Conta: "+contasCliente.getCategoriaContaCliente()+"\nEndereço: "+contasCliente.getEndereco()+"\n");
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
				} else {
					System.err.println("Você precisa ter um cliente cadastrado para realizar essa ação!");
				}
				
				opcao = -1;
			break;
			case 4:
				System.out.println("ABRIR CONTA");
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {	
				System.out.println("\nClientes cadastrados: ");		
				
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					System.out.println("Cliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nEndereço: "+contasCliente.getEndereco());
					if(!(contasCliente.getContaCorrente() == null)) {
						System.out.println(contasCliente.getContaCorrente());
					} 
					
					if(!(contasCliente.getContaPoupanca() == null)) {
						System.out.println(contasCliente.getContaPoupanca());
					} 
					
					if(!(contasCliente.getCartaoDeCredito() == null)) {
						System.out.println(contasCliente.getCartaoDeCredito());
					}
					
					if(!(contasCliente.getCartaoDeDebito() == null)) {
						System.out.println(contasCliente.getCartaoDeDebito());
					}
					
					System.out.println();
					
				}
				
				
					try {						
						System.out.println("\nO CPF deve conter pontos e traços!");
						System.out.println("Digite o CPF do cliente que deseja abrir uma conta - EX: 353.321-854-21: ");	
						abrirContaCpf = input.nextLine();	
						contaCorrenteService.validarCpf(abrirContaCpf);
					} catch (TamanhoDoCpfException e) {
						System.err.println("\nErro: "+e.getMessage());
						break;
					} catch (CpfJaCadastradoException e) {
						System.err.println("\nErro: "+e.getMessage());	
						break;
					} catch (ValidarUltimosNumerosDoCpfException e) {
						System.err.println("\nErro: "+e.getMessage());	
						break;
					} catch (CpfComNumerosIguaisException  e) {
						System.err.println("\nErro: "+e.getMessage());					
						break;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: Digite um valor correspondente");								
						input.nextLine();
					} 
				

			
					try {					
						System.out.println("Digite:\n1 - Abrir Conta Corrente\n2 - Abrir conta Poupança: ");
						tipoDeContaEscolhida = input.nextInt();		
						
						if(tipoDeContaEscolhida == 1) {		
							
							contaCorrenteService.verSeTemContaComEsseCPF(abrirContaCpf, tipoDeContaEscolhida);
							
						} else if(tipoDeContaEscolhida == 2)  {
							
							contaPoupancaService.verSeTemContaComEsseCPF(abrirContaCpf, tipoDeContaEscolhida);
							
						} else if(tipoDeContaEscolhida > 2) {
							System.err.println("\nErro: Digite um valor correspondente\n");	
							break;							
						}
						
					}  catch (ContaComCPFExistenteException e) {
						System.err.println("\nErro: "+e.getMessage());	
						break;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: Digite um valor correspondente");						
						input.nextLine();
					}	
				
					System.out.println("Digite o valor do primeiro depósito: ");
					float primeiroDeposito = input.nextFloat();				
				
					
					if(tipoDeContaEscolhida == 1) {				
						TiposDeConta contaCorrente;
						contaCorrente = TiposDeConta.CONTA_CORRENTE;	
						
						contaCorrenteService.adicionarContaCorrenteEntityEmDao(abrirContaCpf, primeiroDeposito, contaCorrente, categoriaCliente);
					
						
						
					} else if(tipoDeContaEscolhida == 2) {				
						TiposDeConta contaPoupanca;
						contaPoupanca = TiposDeConta.CONTA_POUPANCA;
						
						contaPoupancaService.adicionarContaPoupancaEntityEmDao(abrirContaCpf, primeiroDeposito, contaPoupanca, categoriaCliente);		
					
					}			
				
				
				
				flagCpfAbrirConta = true;	
				flagEscolhaConta = true;
				} else {
					System.err.println("Você precisa ter um cliente cadastrado para criar uma conta!");
				}
				opcao = -1;
				
			break;
			case 5:
				System.out.println("\nVER CONTAS/CARTÕES");	
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
				
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					
					if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
						System.out.println("Cliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nCategoria da Conta: "+contasCliente.getCategoriaContaCliente()+"\nEndereço: "+contasCliente.getEndereco());
					} 
					
					if(!(contasCliente.getContaCorrente() == null)) {
						System.out.println(contasCliente.getContaCorrente());
					} 
					
					if(!(contasCliente.getContaPoupanca() == null)) {
						System.out.println(contasCliente.getContaPoupanca());
					} 
									
					if(!(contasCliente.getCartaoDeCredito() == null)) {
						System.out.println(contasCliente.getCartaoDeCredito());
					}
					
					if(!(contasCliente.getCartaoDeDebito() == null)) {
						System.out.println(contasCliente.getCartaoDeDebito());
					}
					
					System.out.println();					
				}				
	
				}else {
					System.err.println("Você precisa ter clientes e contas cadastradas para realizar essa ação!");
				}
				opcao = -1;
			break;
			case 6:
				System.out.println("\nVER FUNÇÃO DE TAXA E DESCONTO FUNCIONANDO");
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
				
				System.out.println("\nConta Corrente com valor descontado: ");
				System.out.println("---------------------------------------");
				for(ContaCorrenteEntity cc : contaCorrenteService.verContasCorrentesCadastradasDao()) {
					
					for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
						contaCorrenteService.descontarTaxaManutencaoMensal(c);
					}				
				}
				
				
				System.out.println("\nConta Poupança com valor acrescentado: ");
				System.out.println("---------------------------------------");
				for(ContaPoupancaEntity cc : contaPoupancaService.verContasPoupancaCadastradasDao()) {
					
					for(ClienteEntity c : clienteService.verClientesCadastradosDao()) {
						contaPoupancaService.creditarTaxaVigenteMensal(c);
					}				
				}
				}else {
					System.err.println("Você precisa ter clientes e contas cadastradas para realizar essa ação!");
				}
				opcao = -1;
			break;
			case 7:
				System.out.println("\nEMITIR CARTÃO\n");
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
											
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
						System.out.println("\nCliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nCategoria da Conta: "+contasCliente.getCategoriaContaCliente()+"\nEndereço: "+contasCliente.getEndereco());
					} 
					if(!(contasCliente.getContaCorrente() == null)) {
						System.out.println(contasCliente.getContaCorrente());
					} 
					
					if(!(contasCliente.getContaPoupanca() == null)) {
						System.out.println(contasCliente.getContaPoupanca());
					} 									
					
					if(!(contasCliente.getCartaoDeCredito() == null)) {						
		
						cartaoDeCreditoService.buscarCartoesDeCredito(contasCliente.getCpf());
					}
					
					if(!(contasCliente.getCartaoDeCredito() == null)) {						
					
						cartaoDeDebitoService.buscarCartoesDeDebito(contasCliente.getCpf());
					}


					System.out.println();					
				}
				
				if(opcao != -1) {
					
				
				try {						
					System.out.println("\nO CPF deve conter pontos e traços!");
					System.out.println("Digite o CPF do cliente que deseja emitir um cartão: - EX: 353.321-854-21: ");	
					cpfParaEmitirCartao = input.nextLine();	
					
					clienteService.validarCpfSemAutenticar(cpfParaEmitirCartao);
					
					if(clienteService.clienteExisteNoDao(cpfParaEmitirCartao)) { //O cliente existe		
						
						//Se o CPF que me passou para abrir conta é igual ao CPF da conta que ele ja tem cadastrado	
						
						if(!clienteService.clienteTemContasNoDao(cpfParaEmitirCartao).isEmpty()) { 						
							emitirCartao = true;	
							
							System.out.println("\nO cliente portador do CPF "+cpfParaEmitirCartao+" pode emitir um cartão para: ");
							for(ContaEntity contaPermitida : clienteService.clienteTemContasNoDao(cpfParaEmitirCartao)) {								
								System.out.println("\nConta: "+contaPermitida.getCategoriaDeConta().getTipoDaCategoria()+"\nNúmero da Conta: "+contaPermitida.getNumeroDaConta()+"\nTipo da Conta: "+contaPermitida.getTipoDeConta().getDescricaoDaConta()+"\nSado: "+contaPermitida.getSaldo());
							}
						}		
					}
					
					if(emitirCartao == false) {
						System.err.println("\nVocê precisa ter um cliente e uma conta cadastrada para emitir um cartão.");
						break;
					}			
					
					System.out.println("\nDigite a conta que deseja emitir o Cartão: ");
					String contaEmitirCartao = input.nextLine();
					
					
					if(!clienteService.clienteTemContasNoDao(cpfParaEmitirCartao, contaEmitirCartao).isEmpty()) { 	
						
						for(ContaEntity contaPermitida : clienteService.clienteTemContasNoDao(cpfParaEmitirCartao, contaEmitirCartao)) {								
							System.out.println("\nConta: "+contaPermitida.getCategoriaDeConta().getTipoDaCategoria()+"\nNúmero da Conta: "+contaPermitida.getNumeroDaConta()+"\nTipo da Conta: "+contaPermitida.getTipoDeConta().getDescricaoDaConta()+"\nSado: "+contaPermitida.getSaldo());
						}
					} else {
						System.err.println("Essa conta não existe! Adicione um número de conta existente!");
						opcao = -1;
						break;
					}
					
					System.err.println("\nA senha padrão do Cartão é: 0000. Para sua segurança:");
					System.out.println("Digite uma nova senha para o seu cartão: ");
					String senhaCartao = input.next();	
					input.nextLine();
					
					System.out.println("\nDigite:\n1 - Emitir Cartão de Crédito\n2 - Emitir Cartão de Débito: ");
					cartaoEscolhido = input.nextInt();		
					
					if(cartaoEscolhido == 1) {										
						cartaoDeCreditoService.adicionarCartaoDeCreditoEntityEmDao(cpfParaEmitirCartao, cartaoEscolhido, senhaCartao, contaEmitirCartao);
						
					} else if(cartaoEscolhido == 2)  {
						cartaoDeDebitoService.adicionarCartaoDeDebitoEntityEmDao(cpfParaEmitirCartao, cartaoEscolhido, senhaCartao, contaEmitirCartao);	
				
						
					} else if(tipoDeContaEscolhida > 2) {
						System.err.println("\nErro: Digite um valor correspondente\n");	
						break;							
					}
					
					
				} catch (TamanhoDoCpfException e) {
					
					System.err.println("\nErro: "+e.getMessage());
					
				} catch (ValidarUltimosNumerosDoCpfException e) {
					
					System.err.println("\nErro: "+e.getMessage());	
				
				} catch (CpfComNumerosIguaisException  e) {
					
					System.err.println("\nErro: "+e.getMessage());	
				
				} catch (InputMismatchException e) {
					
					System.err.println("\nErro: "+e.getMessage());						
					input.nextLine();					
				}					
				
				}

				}else {
					System.err.println("Você precisa ter clientes e contas cadastradas para realizar essa ação!");
				}
				
				opcao = -1;
			break;
			case 8:
				System.out.println("EMITIR CARTÃO DE DÉBITO");
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
					
				} else {
					System.err.println("Você precisa ter clientes e contas cadastradas para realizar essa ação!");
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