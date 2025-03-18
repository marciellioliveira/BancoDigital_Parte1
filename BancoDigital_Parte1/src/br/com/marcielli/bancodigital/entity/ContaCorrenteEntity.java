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
		this.taxaManutencaoMensal = setTaxaManutencaoMensal(saldo);
		if(saldo <= 1000) {
			categoriaDeConta = CategoriasDeConta.COMUM;
			super.setCategoriaDeConta(categoriaDeConta);
			//this.taxaManutencaoMensal = 12.00f;
			
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());
			//System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			
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
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());
			//System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			this.nomeClienteDonoDaConta = getNomeClienteDonoDaConta();
		}
		
		if(saldo > 5000) {
			categoriaDeConta = CategoriasDeConta.PREMIUM;
			super.setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 0f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());
			//System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
			
			this.nomeClienteDonoDaConta = getNomeClienteDonoDaConta();
		}
	}
	
	public ContaCorrenteEntity() {}		
	
	public void atualizaCategoria(float valor, int enviaOuRecebe) {
		CategoriasDeConta categoria;
		
		float total = 0;
		//Valor que ta enviando ou recebendo do pix
		
		if(enviaOuRecebe == 1) { //enviaOuRecebe = 1 (Envia pix)
			total = this.exibirSaldo() - valor;
		}
		
		if(enviaOuRecebe == 2) { //enviaOuRecebe = 2 (Recebe Pix)
			total = this.exibirSaldo() + valor;
		}
		
		
		if(total <= 1000) {
			categoria = CategoriasDeConta.COMUM;
			super.setCategoriaDeConta(categoria);
		}
		if(total > 1000 && total <= 5000) {
			categoria = CategoriasDeConta.SUPER;
			super.setCategoriaDeConta(categoria);		
		}
		
		if(total > 5000) {
			categoria = CategoriasDeConta.PREMIUM;
			super.setCategoriaDeConta(categoria);
			
		}
		
	}
	

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

	public float setTaxaManutencaoMensal(float valor) {
		
		if(valor <= 1000) {		
			this.taxaManutencaoMensal = 12.00f;		
		}
		
		if(valor  > 1000 && valor  <= 5000) {			
			this.taxaManutencaoMensal = 8.00f;			
		}
		
		if(valor  > 5000) {		
			this.taxaManutencaoMensal = 0f;		
		}
		return taxaManutencaoMensal;
	}

	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfClienteDaConta())) {
				texto = "Conta Corrente: - número "+getNumeroDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria()+" com saldo de R$ "+exibirSaldo()+" e taxa de manutenção mensal de "+getTaxaManutencaoMensal()+".";
			}
		}		
		return texto;		
	}





	
}
