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
import br.com.marcielli.bancodigital.exception.ContaAReceberNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaATransferirNaoExisteException;
import br.com.marcielli.bancodigital.exception.ContaComCPFExistenteException;
import br.com.marcielli.bancodigital.exception.CpfComNumerosIguaisException;
import br.com.marcielli.bancodigital.exception.CpfJaCadastradoException;
import br.com.marcielli.bancodigital.exception.DataDeNascMenor18Exception;
import br.com.marcielli.bancodigital.exception.DiaEMesNascimentoDiferente2Exception;
import br.com.marcielli.bancodigital.exception.DiaNascMenor0OuMaior31Exception;
import br.com.marcielli.bancodigital.exception.EscolhaDosCartoesFalhouException;
import br.com.marcielli.bancodigital.exception.ExisteContaCadastradaException;
import br.com.marcielli.bancodigital.exception.MesNascMenor0OuMaior12Exception;
import br.com.marcielli.bancodigital.exception.MesmosCaracteresEmStringException;
import br.com.marcielli.bancodigital.exception.NomeMenor2EMaior100Exception;
import br.com.marcielli.bancodigital.exception.NumeroContasTransferenciasIguaisException;
import br.com.marcielli.bancodigital.exception.SemSaldoParaTransferenciaException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCepException;
import br.com.marcielli.bancodigital.exception.TamanhoDoCpfException;
import br.com.marcielli.bancodigital.exception.TransferirValorMenorOuIgualAZeroException;
import br.com.marcielli.bancodigital.exception.ValidarUltimosNumerosDoCpfException;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;
import br.com.marcielli.bancodigital.service.CartaoDeCreditoService;
import br.com.marcielli.bancodigital.service.CartaoDeDebitoService;
import br.com.marcielli.bancodigital.service.ClienteService;
import br.com.marcielli.bancodigital.service.ContaCorrenteService;
import br.com.marcielli.bancodigital.service.ContaPoupancaService;
import br.com.marcielli.bancodigital.service.ContaService;

public class Main { //VIEW
	
	public static void main(String[] args) throws TamanhoDoCpfException, CpfJaCadastradoException, IndexOutOfBoundsException, 
	ValidarUltimosNumerosDoCpfException, TamanhoDoCepException, DataDeNascMenor18Exception, NomeMenor2EMaior100Exception, 
	CaracterEspecialNoNomeException, CpfComNumerosIguaisException, ClienteNuloNoDaoException, AnoNascimentoDiferente4Exception, 
	DiaEMesNascimentoDiferente2Exception, MesmosCaracteresEmStringException, CidadeComNumerosIguaisException, ContaComCPFExistenteException, 
	ContaATransferirNaoExisteException, ContaAReceberNaoExisteException, NumeroContasTransferenciasIguaisException, EscolhaDosCartoesFalhouException, 
	TransferirValorMenorOuIgualAZeroException, SemSaldoParaTransferenciaException, ExisteContaCadastradaException, MesNascMenor0OuMaior12Exception,
	DiaNascMenor0OuMaior31Exception {	
		
		int opcao = -1;
		Scanner input = new Scanner(System.in);	
		int cadastro = 3;
		
		ClienteService clienteService;
		clienteService = new ClienteService();
		
		CategoriasDeConta categoriaCliente;
		categoriaCliente = CategoriasDeConta.COMUM;
		
		ContaService contaService = new ContaService();
		ContaCorrenteService contaCorrenteService = new ContaCorrenteService();
		ContaPoupancaService contaPoupancaService = new ContaPoupancaService();
		CartaoDeCreditoService cartaoDeCreditoService = new CartaoDeCreditoService();
		CartaoDeDebitoService cartaoDeDebitoService = new CartaoDeDebitoService();
		
		boolean emitirCartao = false, flagEscolhaConta = true, flagCpfAbrirConta = true, flagMenu = true, flagNome = true, flagCpf = true, flagAnoNascimento = true, flagMesNascimento = true, flagDiaNascimento = true, flagCep = true, flagCidade = true, flagEstado= true, flagRua= true, flagNumero= true, flagBairro= true, flagComplemento= true;
		String contaAReceber = null, cpfParaEmitirCartao = null, escolhaConta = null, abrirContaCpf = null, nome = null, cpf = null, cep = null, cidade = null, estado = null, rua = null, numero = null, bairro = null, complemento = null;
		int cartaoEscolhido = 0, tipoDeContaEscolhida =0, anoNascimento = 0, mesNascimento = 0, diaNascimento = 0;
		Endereco endereco = new Endereco();
	
		int i=1;
		do {
			
				try {
					System.out.println();
					System.out.println("1 - ADICIONAR CLIENTE\n2 - LISTAR CLIENTES\n3 - REMOVER CLIENTES\n4 - ABRIR CONTA\n5 - VER CONTAS/CARTÕES\n6 - VER TAXA/DESCONTO FUNCIONANDO\n7 - EMITIR CARTÃO\n8 - FAZER TRANSFERÊNCIA\n0 - SAIR..: ");
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
					} catch (MesNascMenor0OuMaior12Exception e) {
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
					} catch (DiaNascMenor0OuMaior31Exception e) {
						System.err.println("\nErro: "+e.getMessage());	
						flagDiaNascimento = true;
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
				
				endereco.setCep(cep);
				
				while(flagCidade) { 
					try {						
						System.out.println("\nDigite sua cidade: ");	
						cidade = input.nextLine();	
						clienteService.validarCidade(cidade);
						
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
				
				endereco.setCidade(cidade);
				
				while(flagEstado) { 
					try {						
						System.out.println("\nDigite seu estado: ");	
						estado = input.nextLine();	
						clienteService.validarCidade(estado);
						
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
				
				endereco.setEstado(estado);
				
				while(flagRua) { 
					try {						
						System.out.println("\nDigite sua rua: ");	
						rua = input.nextLine();	
						clienteService.validarRua(rua);
						
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
				
				endereco.setRua(rua);
				
				while(flagNumero) { 
					try {						
						System.out.println("\nDigite o número: ");	
						numero = input.nextLine();	
						clienteService.validarNumero(numero);
						
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
				
				endereco.setBairro(bairro);
				
				while(flagComplemento) { 
					try {						
						System.out.println("\nDigite o complemento: ");	
						complemento = input.nextLine();	
						clienteService.validarComplemento(complemento);
						
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
				
				endereco.setComplemento(complemento);
				
				Endereco endereco1 = new Endereco(cep, cidade, estado, rua, numero, bairro, complemento);
				
				if(clienteService.adicionarClienteEntityEmDao(cpf, nome, dataNascimentoDATE, endereco1, categoriaCliente)) {
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
				System.out.println("\nABRIR CONTA");
				
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0) {
					System.out.println("Você pode abrir conta para os clientes cadastrados abaixo:");
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
						System.out.println("\nDigite:\n1 - Abrir Conta Corrente\n2 - Abrir conta Poupança: ");
						tipoDeContaEscolhida = input.nextInt();		
						
						if(tipoDeContaEscolhida == 1) {		
							
							contaCorrenteService.verSeTemContaComEsseCPF(abrirContaCpf, tipoDeContaEscolhida);
							
						} else if(tipoDeContaEscolhida == 2)  {
							
							contaPoupancaService.verSeTemContaComEsseCPF(abrirContaCpf, tipoDeContaEscolhida);
							
						} else if(tipoDeContaEscolhida > 2 || tipoDeContaEscolhida <= 0) {
							System.err.println("\nErro: Digite um valor correspondente\n");	
							break;							
						}
						
					}  catch (ContaComCPFExistenteException e) {
						System.err.println("\nErro: "+e.getMessage());	
						break;
					} catch (InputMismatchException e) {
						System.err.println("\nErro: Digite um valor correspondente");						
						input.nextLine();
						break;
					}	
				
					try {	
					System.out.println("Digite o valor do primeiro depósito: ");
					float primeiroDeposito = input.nextFloat();		
					
					if(primeiroDeposito < 0) {
						System.err.println("\nErro: Digite um valor correspondente");						
						input.nextLine();
						break;
					}				
					
					if(tipoDeContaEscolhida == 1) {				
						TiposDeConta contaCorrente;
						contaCorrente = TiposDeConta.CONTA_CORRENTE;	
						
						contaCorrenteService.adicionarContaCorrenteEntityEmDao(abrirContaCpf, primeiroDeposito, contaCorrente, categoriaCliente);
					
						
						
					} else if(tipoDeContaEscolhida == 2) {				
						TiposDeConta contaPoupanca;
						contaPoupanca = TiposDeConta.CONTA_POUPANCA;
						
						contaPoupancaService.adicionarContaPoupancaEntityEmDao(abrirContaCpf, primeiroDeposito, contaPoupanca, categoriaCliente);		
					
					} 	
					} catch (InputMismatchException e) {
						System.err.println("\nErro: Digite um valor correspondente");						
						input.nextLine();
						break;
					}	
				
				
				
				flagCpfAbrirConta = true;	
				flagEscolhaConta = true;
				} else {
					System.err.println("Você precisa ter um cliente cadastrado para criar uma conta!");
				}
				opcao = -1;
				
			break;
			case 5:
				System.out.println("\nVER CONTAS/CARTÕES\n");	
				
				
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0 && contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0 || contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
				
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
				if(clienteService.listarClientesDaoEmEntity().size() != 0 && contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0 || contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
				
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
				System.out.println("\nEMITIR CARTÃO");
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0 && contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0 || contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
											
				for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
					if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
						System.out.println("\nCliente: "+contasCliente.getNome()+"\nCPF: "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nEndereço: "+contasCliente.getEndereco());
					} 
					if(!(contasCliente.getContaCorrente() == null)) {
						System.out.println(contasCliente.getContaCorrente());
					} 
					
					if(!(contasCliente.getContaPoupanca() == null)) {
						System.out.println(contasCliente.getContaPoupanca());
					} 									
					
					if(!(contasCliente.getCartaoDeCredito() == null)) {		
//						System.err.println("MAIN");
//						System.err.println("Cartão de Crédito numero conta vinculada: "+contasCliente.getCartaoDeCredito().getNumeroContaVinculada());
//						System.err.println("Cartão de Crédito cpf cliente: "+contasCliente.getCartaoDeCredito().getCpfDoDono());
						
						cartaoDeCreditoService.buscarCartoesDeCredito(contasCliente.getCpf());
					}
					
					if(!(contasCliente.getCartaoDeDebito() == null)) {						
					//	System.err.println("Categoria da Contta main debito: "+contasCliente.getCategoriaDeConta());
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
					
					System.err.println("\nErro: Digite um valor correspondente");						
					input.nextLine();					
				}					
				
				}

				}else {
					System.err.println("Você precisa ter clientes e contas cadastradas para realizar essa ação!");
				}
				
				opcao = -1;
			break;
			case 8:
				System.out.println("\nFAZER TRANSFERÊNCIA: PIX");	
				
				
				if(clienteService.listarClientesDaoEmEntity().size() != 0 && contaCorrenteService.verContasCorrentesCadastradasDao().size() != 0 && contaPoupancaService.verContasPoupancaCadastradasDao().size() != 0) {
					
					
					System.out.println("Veja as contas cadatradas que podem enviar/receber pix:");
				
					for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
						if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
							System.out.println("\nCliente: "+contasCliente.getNome()+"\nCPF (Chave Pix): "+contasCliente.getCpf()+"\nData de Nascimento: "+contasCliente.getDataNascimento()+"\nEndereço: "+contasCliente.getEndereco());
						} 
						
						if(!(contasCliente.getContaCorrente() == null)) {
							System.out.println("Conta Corrente: "+contasCliente.getContaCorrente()+" - Saldo: "+contasCliente.getContaCorrente().exibirSaldo());
						} 
						
						if(!(contasCliente.getContaPoupanca() == null)) {
							System.out.println("Conta Poupança: "+contasCliente.getContaPoupanca()+" - Saldo: "+contasCliente.getContaPoupanca().exibirSaldo());
						} 
						
						
//						if(!(contasCliente.getContaCorrente() == null)) {
//							System.out.println(contasCliente.getContaCorrente());
//						} 
//						
//						if(!(contasCliente.getContaPoupanca() == null)) {
//							System.out.println(contasCliente.getContaPoupanca());
//						} 									
//						
//						if(!(contasCliente.getCartaoDeCredito() == null)) {		
//							
//							cartaoDeCreditoService.buscarCartoesDeCredito(contasCliente.getCpf());
//						}
//						
//						if(!(contasCliente.getCartaoDeDebito() == null)) {						
//							cartaoDeDebitoService.buscarCartoesDeDebito(contasCliente.getCpf());
//						}
//
//						System.out.println();					
					}				
					
					
					try {	
						
						
						
						System.out.println("\nDigite a chave pix (CPF) que deseja TRANSFERIR o valor: ");
						String cpfParaTransferir = input.nextLine();
																		
						clienteService.validarCpfSemAutenticar(cpfParaTransferir);
						
						System.out.println("\nDigite a chave pix (CPF) que deseja RECEBER o valor: ");
						String cpfParaReceber = input.nextLine();
												
						clienteService.validarCpfSemAutenticar(cpfParaReceber);
						
						if(clienteService.clienteExisteNoDao(cpfParaTransferir)) { //O cliente que vai transferir existe	
							if(clienteService.clienteExisteNoDao(cpfParaReceber)) { //O cliente que vai receber existe		
								
								for(ClienteEntity contasCliente : clienteService.listarClientesDaoEmEntity()) {
									
									if(contasCliente.getCpf().equals(cpfParaTransferir)) {
										
										if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
											System.out.println("\nTRANSFERIR DE: "+contasCliente.getNome()+" - CPF (Chave Pix): "+contasCliente.getCpf());
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
										
										if(!(contasCliente.getCartaoDeDebito() == null)) {						
											cartaoDeDebitoService.buscarCartoesDeDebito(contasCliente.getCpf());
										}
										
									}									
									
									if(contasCliente.getCpf().equals(cpfParaReceber)) {
										
										if(contasCliente.getContaCorrente() != null || contasCliente.getContaPoupanca() != null || contasCliente.getCartaoDeCredito() != null || contasCliente.getCartaoDeDebito() != null) {
											System.out.println("\nRECEBER EM: "+contasCliente.getNome()+" - CPF (Chave Pix): "+contasCliente.getCpf());
										} 
										if(!(contasCliente.getContaCorrente() == null)) {
											System.out.println(contasCliente.getContaCorrente());
										} 
										
										if(!(contasCliente.getContaPoupanca() == null)) {
											System.out.println(contasCliente.getContaPoupanca());
										} 		
									}
								}
								
								
								String cpf1 = cpfParaTransferir;
								String cpf2 = cpfParaReceber;
								
								System.out.println("\nDigite o valor: ");
								float cTransferirValor = input.nextFloat();
								
								
								System.out.println("\nDigite\n1 - Enviar PIX da Conta Corrente\n2 - Enviar PIX da Conta Poupança: ");
								int tipoContaPixEnviar = input.nextInt(); 
								
								if(tipoContaPixEnviar == 1) {
									//Enviar pix da Conta Corrente
									
									for(ClienteEntity clienteEnviar : clienteService.listarClientesDaoEmEntity()) {
										
										if(clienteEnviar.getCpf().equals(cpfParaTransferir)) {
											for(ClienteEntity clienteReceber : clienteService.listarClientesDaoEmEntity()) {
												if(clienteReceber.getCpf().equals(cpfParaReceber)) {
													
													System.out.println("Enviar PIX da Conta Corrente -> ");
													
													//Enviar Pix da Conta Corrente
													contaCorrenteService.temContaCorrente(cpfParaTransferir);
													System.out.println("\nConta de "+clienteEnviar.getNome());
													System.out.println("Saldo Antigo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
													contaCorrenteService.enviarPix(cpfParaTransferir, cTransferirValor);
													System.out.println("Transferiu R$ "+cTransferirValor+" para "+clienteReceber.getNome()+" (PIX: "+cpfParaReceber+")");
													System.out.println("Saldo Novo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
													
													
//													contaCorrenteService.temContaCorrente(cpfParaReceber);
//													System.out.println("\nConta de "+clienteReceber.getNome());
//													System.out.println("Saldo Antigo R$ "+clienteReceber.getContaCorrente().exibirSaldo());
//													contaCorrenteService.receberPix(cpfParaReceber, cTransferirValor);
//													System.out.println("Recebeu R$ "+cTransferirValor+" de "+clienteEnviar.getNome()+" (PIX: "+cpfParaTransferir+")");
//													System.out.println("Saldo Novo R$ "+clienteReceber.getContaCorrente().exibirSaldo());
									
													
												}
											}
											
											
										}
										
									}
								}
								
								if(tipoContaPixEnviar == 2) {
									//Enviar pix da conta poupança
									
									for(ClienteEntity clienteEnviar : clienteService.listarClientesDaoEmEntity()) {
										
										if(clienteEnviar.getCpf().equals(cpfParaTransferir)) {
											for(ClienteEntity clienteReceber : clienteService.listarClientesDaoEmEntity()) {
												if(clienteReceber.getCpf().equals(cpfParaReceber)) {
													
													System.out.println("Enviar PIX da Conta Poupança -> ");
													//Enviar pix da conta poupança
													contaPoupancaService.temContaPoupanca(cpfParaTransferir);
													System.out.println("\nConta de "+clienteEnviar.getNome());
													System.out.println("Saldo Antigo R$ "+clienteEnviar.getContaPoupanca().exibirSaldo());
													contaPoupancaService.enviarPix(cpfParaTransferir, cTransferirValor);
													System.out.println("Transferiu R$ "+cTransferirValor+" para "+clienteReceber.getNome()+" (PIX: "+cpfParaReceber+")");
													System.out.println("Saldo Novo R$ "+clienteEnviar.getContaPoupanca().exibirSaldo());
										
//													contaPoupancaService.temContaPoupanca(cpfParaReceber);
//													System.out.println("\nConta de "+clienteReceber.getNome());
//													System.out.println("Saldo Antigo R$ "+clienteReceber.getContaPoupanca().exibirSaldo());
//													contaPoupancaService.receberPix(cpfParaReceber, cTransferirValor);
//													System.out.println("Recebeu R$ "+cTransferirValor+" de "+clienteEnviar.getNome()+" (PIX: "+cpfParaTransferir+")");
//													System.out.println("Saldo Novo R$ "+clienteReceber.getContaPoupanca().exibirSaldo());
													
												}
											}
											
											
										}
										
									}
									
								}	
								
								
								System.out.println("\nDigite\n1 - Receber PIX na Conta Corrente\n2 - Receber PIX na Conta Poupança: ");
								int tipoContaPixReceber = input.nextInt(); 
								
								
								if(tipoContaPixReceber == 1) {
									//Receber pix da Conta Corrente
									
									
									for(ClienteEntity clienteEnviar : clienteService.listarClientesDaoEmEntity()) {
										
										if(clienteEnviar.getCpf().equals(cpfParaTransferir)) {
											for(ClienteEntity clienteReceber : clienteService.listarClientesDaoEmEntity()) {
												if(clienteReceber.getCpf().equals(cpfParaReceber)) {
													
//													System.err.println("Receber PIX na Conta Corrente <- ");
//													System.out.println("\nConta de "+clienteEnviar.getNome());
//													System.out.println("Saldo Antigo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
//													contaCorrenteService.enviarPix(cpfParaTransferir, cTransferirValor);
//													System.out.println("Transferiu R$ "+cTransferirValor+" para "+clienteReceber.getNome()+" (PIX: "+cpfParaReceber+")");
//													System.out.println("Saldo Novo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
													
													System.out.println("Receber PIX na Conta Corrente <- ");
													contaCorrenteService.temContaCorrente(cpfParaReceber);
													System.out.println("\nConta de "+clienteReceber.getNome());
													System.out.println("Saldo Antigo R$ "+clienteReceber.getContaCorrente().exibirSaldo());
													contaCorrenteService.receberPix(cpfParaReceber, cTransferirValor);
													System.out.println("Recebeu R$ "+cTransferirValor+" de "+clienteEnviar.getNome()+" (PIX: "+cpfParaTransferir+")");
													System.out.println("Saldo Novo R$ "+clienteReceber.getContaCorrente().exibirSaldo());
													
												}
											}
											
											
										}
										
									}
									
								}
								
								if(tipoContaPixReceber == 2) {
									//Receber pix da Conta Poupança
									
									
									for(ClienteEntity clienteEnviar : clienteService.listarClientesDaoEmEntity()) {
										
										if(clienteEnviar.getCpf().equals(cpfParaTransferir)) {
											for(ClienteEntity clienteReceber : clienteService.listarClientesDaoEmEntity()) {
												if(clienteReceber.getCpf().equals(cpfParaReceber)) {
													
//													System.out.println("\nConta de "+clienteEnviar.getNome());
//													System.out.println("Saldo Antigo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
//													contaPoupancaService.enviarPix(cpfParaTransferir, cTransferirValor);
//													System.out.println("Transferiu R$ "+cTransferirValor+" para "+clienteReceber.getNome()+" (PIX: "+cpfParaReceber+")");
//													System.out.println("Saldo Novo R$ "+clienteEnviar.getContaCorrente().exibirSaldo());
//													
//													
													System.out.println("Receber PIX na Conta Poupança <- ");
													contaPoupancaService.temContaPoupanca(cpfParaReceber);
													System.out.println("\nConta de "+clienteReceber.getNome());
													System.out.println("Saldo Antigo R$ "+clienteReceber.getContaPoupanca().exibirSaldo());
													contaPoupancaService.receberPix(cpfParaReceber, cTransferirValor);
													System.out.println("Recebeu R$ "+cTransferirValor+" de "+clienteEnviar.getNome()+" (PIX: "+cpfParaTransferir+")");
													System.out.println("Saldo Novo R$ "+clienteReceber.getContaPoupanca().exibirSaldo());
													
												}
											}
											
											
										}
										
									}
									
									
								}
								
								
							}							
						}
						
						
						
						
						
						
						
						
//					} catch (TransferirValorMenorOuIgualAZeroException e) {
//						
//						System.err.println("\nErro: "+e.getMessage());
//						
//					} catch (EscolhaDosCartoesFalhouException e) {
//						
//						System.err.println("\nErro: "+e.getMessage());
//						
//
//					} catch (NumeroContasTransferenciasIguaisException e) {
//						
//						System.err.println("\nErro: "+e.getMessage());
						
					} catch (ExisteContaCadastradaException e) {
						System.err.println("\nErro: "+e.getMessage());
					} catch (SemSaldoParaTransferenciaException  e) {
						System.err.println("\nErro: "+e.getMessage());
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
					
					
	
				}else {
					System.err.println("Você precisa ter clientes, mais de uma conta cadastrada e cartões emitidos para realizar essa ação!");
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