package br.com.marcielli.bancodigital.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;
import br.com.marcielli.bancodigital.service.ContaCorrenteService;

public class ContaCorrenteEntity extends ContaEntity {
	
	private float taxaManutencaoMensal;
	private String nomeClienteDonoDaConta;

	
	public ContaCorrenteEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta,  String numeroDaConta) {
		super(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta, numeroDaConta);
		
		//this.contasDoClientePorCpf = contasDoClientePorCpf;
		
		//System.out.println("ContaCorrenteEn tity: "+contasDoClientePorCpf);			
		
		if(saldo <= 1000) {
			categoriaDeConta = CategoriasDeConta.COMUM;
			super.setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 12.00f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			
			for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
				if(cli.getCpf().equals(cpfClienteDaConta)) {
				    setNomeClienteDonoDaConta(nomeClienteDonoDaConta);
				}
			}
			
			this.nomeClienteDonoDaConta = getNomeClienteDonoDaConta();	
		}
		
		if(saldo > 1000 && saldo <= 5000) {
			categoriaDeConta = CategoriasDeConta.SUPER;
			super.setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 8.00f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			this.nomeClienteDonoDaConta = getNomeClienteDonoDaConta();
		}
		
		if(saldo > 5000) {
			categoriaDeConta = CategoriasDeConta.PREMIUM;
			super.setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 0f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			this.nomeClienteDonoDaConta = getNomeClienteDonoDaConta();
		}
	}
	
	public ContaCorrenteEntity() {}		

	@Override
	public float exibirSaldo() {		
		return getSaldo();
	}

	@Override
	public void enviarPix(float valor) {		
		setSaldo(getSaldo() - valor); 
	}
	
	@Override
	public void receberPix(float valor) {
		setSaldo(getSaldo() + valor);		
	}
		
		
		
		
		
		
		//		float saldoAntigoContaCorrente = 0;
//		float novoSaldoContaCorrente = 0;			
//		
//		for(ClienteEntity t : cTransferir) {
//		
//			if(numContaTransf.equals(t.getContaCorrente().getNumeroDaConta())) {	
//				
//				System.err.println("numContaTransf: "+numContaTransf);
//				System.err.println("t.getContaCorrente().getNumeroDaConta(): "+t.getContaCorrente().getNumeroDaConta());
//			
//					saldoAntigoContaCorrente = t.getContaCorrente().getSaldo();
//					t.getContaCorrente().setSaldo(saldoAntigoContaCorrente - cTransferirValor);
//					novoSaldoContaCorrente = t.getContaCorrente().exibirSaldo();
//				
//				
//				
//				
//				
//				
//			}					
//		}
//		
//		System.err.println("Saldo Antigo: "+saldoAntigoContaCorrente);
//		System.err.println("Saldo Novo: "+novoSaldoContaCorrente);
//		
//		


		
		
		
		
		
	
	
	
	public ArrayList<CartaoEntity> salvarCartoesDaConta() {
		
		return null;
	}		

	

	public void descontarTaxaManutencaoMensal(ClienteEntity cliente) {	
		ContaCorrenteService ccService = new ContaCorrenteService();
		ccService.descontarTaxaManutencaoMensal(cliente);		
	}

	public String getNomeClienteDonoDaConta() {
		return nomeClienteDonoDaConta;
	}

	public void setNomeClienteDonoDaConta(String nomeClienteDonoDaConta) {
		this.nomeClienteDonoDaConta = nomeClienteDonoDaConta;
	}

	public float getTaxaManutencaoMensal() {
		return taxaManutencaoMensal;
	}

	public void setTaxaManutencaoMensal(float taxaManutencaoMensal) {
		this.taxaManutencaoMensal = taxaManutencaoMensal;
	}

	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfClienteDaConta())) {
				texto = "Conta Corrente: - número "+getNumeroDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria()+" com saldo de R$ "+exibirSaldo()+" e taxa de manutenção anual de "+getTaxaManutencaoMensal()+".";
			}
		}		
		return texto;		
	}





	
}
