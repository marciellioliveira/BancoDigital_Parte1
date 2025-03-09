package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaCorrenteEntity extends ContaEntity {
	
	private float taxaManutencaoMensal;
	
	
	public ContaCorrenteEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta) {
		super(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta);
		
		if(saldo <= 1000) {
			categoriaDeConta = CategoriasDeConta.COMUM;
			setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 12.00f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
		}
		
		if(saldo > 1000 && saldo <= 5000) {
			categoriaDeConta = CategoriasDeConta.SUPER;
			setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 8.00f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
		}
		
		if(saldo > 5000) {
			categoriaDeConta = CategoriasDeConta.PREMIUM;
			setCategoriaDeConta(categoriaDeConta);
			this.taxaManutencaoMensal = 0f;
			System.out.println("Saldo: "+saldo);
			System.out.println("Categoria: "+categoriaDeConta);
			System.out.println("Taxa de Manutenção Mensal: "+taxaManutencaoMensal);
		}
	}
	
	public ContaCorrenteEntity() {}		

	@Override
	public float exibirSaldo() {		
		return getSaldo();
	}

	@Override
	public void fazerTransferenciaViaPix() {		
		
	}
	
	public void descontarTaxaManutencaoMensal(ClienteEntity cliente) {
		
	}

	public float getTaxaManutencaoMensal() {
		return taxaManutencaoMensal;
	}

	public void setTaxaManutencaoMensal(float taxaManutencaoMensal) {
		this.taxaManutencaoMensal = taxaManutencaoMensal;
	}

	@Override
	public String toString() {
		return ""+getTipoDeConta().getDescricaoDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria().toLowerCase()+" com saldo inicial de R$ "+getSaldo()+" e taxa de manutenção anual de "+getTaxaManutencaoMensal();
	}		
}
