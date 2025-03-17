package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;
import br.com.marcielli.bancodigital.service.ContaPoupancaService;

public class ContaPoupancaEntity extends ContaEntity {
	
	private float acrescimoTaxaRendimento;
	private double taxaMensal;
	
	
	public ContaPoupancaEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta, String numeroDaConta) {
		super(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta,  numeroDaConta);
				
		if(saldo <= 1000) {
			categoriaDeConta = CategoriasDeConta.COMUM;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.005f;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldo > 1000 && saldo <= 5000) {
			categoriaDeConta = CategoriasDeConta.SUPER;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.007f;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldo > 5000) {
			categoriaDeConta = CategoriasDeConta.PREMIUM;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.009f;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
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
	
	public void acrescentarTaxaRendimento(ClienteEntity cliente) {
		
		ContaPoupancaService cps = new ContaPoupancaService();
		cps.creditarTaxaVigenteMensal(cliente);
	}
	
	public float getAcrescimoTaxaRendimento() {
		return acrescimoTaxaRendimento;
	}

	public void setAcrescimoTaxaRendimento(float acrescimoTaxaRendimento) {
		this.acrescimoTaxaRendimento = acrescimoTaxaRendimento;
	}
	
	

	public double getTaxaMensal() {
		return taxaMensal;
	}
	
	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfClienteDaConta())) {
				texto = "Conta Poupança: - número "+getNumeroDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria()+" com saldo de R$ "+exibirSaldo()+" e taxa de rendimento anual de "+getAcrescimoTaxaRendimento()+".";
			}
		}		
		return texto;		
	}	
}