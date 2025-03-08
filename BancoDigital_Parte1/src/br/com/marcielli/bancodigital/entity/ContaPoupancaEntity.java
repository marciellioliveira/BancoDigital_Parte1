package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaPoupancaEntity extends ContaEntity {
	
	private float acrescimoTaxaRendimento;
	
	public ContaPoupancaEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta) {
		super(cpfClienteDaConta, saldo, tipoDeConta);
		this.acrescimoTaxaRendimento = acrescimoTaxaRendimento;
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

}
