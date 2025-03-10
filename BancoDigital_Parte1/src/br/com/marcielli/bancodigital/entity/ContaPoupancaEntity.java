package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaPoupancaEntity extends ContaEntity {
	
	private float acrescimoTaxaRendimento;
	
	
	public ContaPoupancaEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta) {
		super(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta);
				
		if(saldo <= 1000) {
			categoriaDeConta = CategoriasDeConta.COMUM;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.5f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldo > 1000 && saldo <= 5000) {
			categoriaDeConta = CategoriasDeConta.SUPER;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.7f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldo > 5000) {
			categoriaDeConta = CategoriasDeConta.PREMIUM;
			setCategoriaDeConta(categoriaDeConta);
			this.acrescimoTaxaRendimento = 0.9f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
	}

	@Override
	public float exibirSaldo() {
		
		return 0;
	}

	@Override
	public void fazerTransferenciaViaPix() {		
		
	}
	
	public void acrescentarTaxaRendimento() {
		
	}
	
	public float getAcrescimoTaxaRendimento() {
		return acrescimoTaxaRendimento;
	}

	public void setAcrescimoTaxaRendimento(float acrescimoTaxaRendimento) {
		this.acrescimoTaxaRendimento = acrescimoTaxaRendimento;
	}

	@Override
	public String toString() {
		return ""+getTipoDeConta().getDescricaoDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria().toLowerCase()+" com saldo inicial de R$ "+getSaldo()+" e taxa de rendimento anual de "+getAcrescimoTaxaRendimento();
	}	

}