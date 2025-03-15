package br.com.marcielli.bancodigital.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import br.com.marcielli.bancodigital.dao.CartaoDeCreditoDao;
import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.CartaoDeCreditoEntity;
import br.com.marcielli.bancodigital.entity.CartaoEntity;
import br.com.marcielli.bancodigital.entity.ClienteEntity;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TipoDeCartao;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class CartaoDeCreditoService {

	CartaoDeCreditoDao cartaoDeCreditoDao = CartaoDeCreditoDao.getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();
	ContaPoupancaDao contaPoupancaDao = ContaPoupancaDao.getInstancia();
	ContaCorrenteDao contaCorrenteDao = ContaCorrenteDao.getInstancia();
	
	public void adicionarCartaoDeCreditoEntityEmDao(String cpfClienteEmitirCartao, int tipoDeCartaoEscolhido, String senha, String contaVinculadaAoCartao) {
		
		try {
			
			for(ClienteEntity c : clienteDao.buscarClientes()) {
				if(cpfClienteEmitirCartao.equals(c.getCpf())) {
					
					String numeroDoCartaoDeCredito = geraNumeroDoCartaoDeCredito();	
					TiposDeConta tpc= buscarTipoDaContaDoCliente(cpfClienteEmitirCartao);
					String numeroDaConta = buscarNumeroDaConta(cpfClienteEmitirCartao);
					
					//System.out.println("\nO Cartão de Crédito "+numeroDoCartaoDeCredito+" foi cadastrado no cpf "+cpfClienteEmitirCartao+" do titular:\n");					
					
					
					CartaoDeCreditoEntity cartaoDeCreditoNovo = new CartaoDeCreditoEntity(numeroDoCartaoDeCredito, c.getNome(), cpfClienteEmitirCartao, tpc,
							c.getCategoriaDeConta(), TipoDeCartao.CARTAO_DE_CREDITO, true, senha, contaVinculadaAoCartao);
					
					//Adicionando Cartão de Crédito no Cliente 		
					c.setCartaoDeCredito(cartaoDeCreditoNovo);
			
					
					
					
					cartaoDeCreditoDao.adicionarCartaoDeCredito(cartaoDeCreditoNovo);
					
					
					System.out.println("\n"+TipoDeCartao.CARTAO_DE_CREDITO.getDescricaoDoTipoDeCartao()+" número "+numeroDoCartaoDeCredito+" vinculado a conta "+contaVinculadaAoCartao+" do cliente portador do cpf número "+cpfClienteEmitirCartao+" foi cadastrado com sucesso!\n");
					
				}
			}
			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}			
	}


	public void buscarCartoesDeCredito(String cpf) {	
		
		for(CartaoDeCreditoEntity cartaoCEntity : cartaoDeCreditoDao.buscarCartoesDeCredito()) {
			if(cpf.equals(cartaoCEntity.getCpfDoDono())) {
				
				System.out.println(cartaoCEntity.getTipoDeCartao().getDescricaoDoTipoDeCartao()+": número "+cartaoCEntity.getNumeroDoCartao()+" vinculado à conta "+cartaoCEntity.getNumeroContaVinculada()+" e ao cpf "+cartaoCEntity.getCpfDoDono()+", cadastrado com limite inicial de R$ "+cartaoCEntity.getLimiteDeCreditoPreAprovado()+" e taxa de utilização de "+cartaoCEntity.taxaDeUtilizacao());

			}
		}	
		
	}
	


	public TiposDeConta buscarTipoDaContaDoCliente(String cpf) {
		ContaCorrenteService ccService = new ContaCorrenteService();
		ContaPoupancaService cpService = new ContaPoupancaService();
		
		TiposDeConta tipoDeConta = TiposDeConta.CONTA_CORRENTE;
		
		for(ContaCorrenteEntity cc : ccService.verContasCorrentesCadastradasDao()) {
			for(ContaPoupancaEntity cp : cpService.verContasPoupancaCadastradasDao()) {
				if(ccService.verContasCorrentesCadastradasDao().size() != 0 || cpService.verContasPoupancaCadastradasDao().size() != 0) {
					if(cpf.equals(cc.getCpfClienteDaConta())) {
						tipoDeConta = TiposDeConta.CONTA_CORRENTE;
					} else if(cpf.equals(cp.getCpfClienteDaConta())) {
						tipoDeConta = TiposDeConta.CONTA_POUPANCA;
					}
				}
			}
		}			
		
		return tipoDeConta;				
	}
	
	public String buscarNumeroDaConta(String cpf) {
		ContaCorrenteService ccService = new ContaCorrenteService();
		ContaPoupancaService cpService = new ContaPoupancaService();
		
		String nc = "";
		
		for(ContaCorrenteEntity cc : ccService.verContasCorrentesCadastradasDao()) {
			for(ContaPoupancaEntity cp : cpService.verContasPoupancaCadastradasDao()) {
				if(ccService.verContasCorrentesCadastradasDao().size() != 0 || cpService.verContasPoupancaCadastradasDao().size() != 0) {
					if(cpf.equals(cc.getCpfClienteDaConta())) {
						nc = cc.getNumeroDaConta();
					} else if(cpf.equals(cp.getCpfClienteDaConta())) {
						nc = cp.getNumeroDaConta();
					}
				}
			}
		}			
		
		return nc;				
	}


	public String geraNumeroDoCartaoDeCredito() {
		
		int[] sequencia = new int[8];
		Random random = new Random();
		String minhaConta = "";
		
		
		for(int i=0; i<sequencia.length; i++) {			
			sequencia[i] = 1 + random.nextInt(8);
		}
		
		for(int i=0; i<sequencia.length; i++) {			
			minhaConta += Integer.toString(sequencia[i]);
		}		
		
		ContaEntity cent = new ContaCorrenteEntity();
		
		cent.setNumeroDaConta(minhaConta.concat("-cc"));
	
		
		return cent.getNumeroDaConta();
	}


	public boolean verSeCpfACadastrarJatemClienteCadastrado(String cpf) {	
		
		for(ClienteEntity c : clienteDao.buscarClientes()) {
			if(cpf.equals(c.getCpf())) {
				return true;
			}
		}
		
		return false;
	}
	


















}
