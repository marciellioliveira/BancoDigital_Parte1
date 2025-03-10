package br.com.marcielli.bancodigital.entity;

import java.time.LocalDate;


import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.helpers.CategoriasDeConta;
import br.com.marcielli.bancodigital.helpers.TiposDeConta;

public class ContaCorrenteEntity extends ContaEntity {
	
	private float taxaManutencaoMensal;
	private String nomeClienteDonoDaConta;
	private ContasDoCliente contasDoClientePorCpf;
	//private ArrayList<ContasDoCliente> todasAsContasDoClientePorcpf;
	
	
	public ContaCorrenteEntity(String cpfClienteDaConta, float saldo, TiposDeConta tipoDeConta, CategoriasDeConta categoriaDeConta, ContasDoCliente contasDoClientePorCpf) {
		super(cpfClienteDaConta, saldo, tipoDeConta, categoriaDeConta);
		
		this.contasDoClientePorCpf = contasDoClientePorCpf;
		
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
	public void fazerTransferenciaViaPix() {		
		
	}
	
	
	
	public ContasDoCliente getContasDoClientePorCpf() {
		return contasDoClientePorCpf;
	}

	public void setContasDoClientePorCpf(ContasDoCliente contasDoClientePorCpf) {
		this.contasDoClientePorCpf = contasDoClientePorCpf;
	}

	public float descontarTaxaManutencaoMensal(ClienteEntity cliente) {
		LocalDate dataDeAgora = LocalDate.now();					
		LocalDate dataEsperada = LocalDate.of(dataDeAgora.getYear(), dataDeAgora.getMonth(), 1);
		
		//Voltar aqui e fazer primeiro dia útil da semana/mês
		float saldoNovo = getSaldo() - getTaxaManutencaoMensal(); 		
		return saldoNovo;
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
	
	

	
//
//	public ArrayList<ContasDoCliente> getTodasAsContasDoClientePorcpf() {
//		return todasAsContasDoClientePorcpf;
//	}
//
//	public void setTodasAsContasDoClientePorcpf(ArrayList<ContasDoCliente> todasAsContasDoClientePorcpf) {
//		this.todasAsContasDoClientePorcpf = todasAsContasDoClientePorcpf;
//		todasAsContasDoClientePorcpf.add(contasDoClientePorCpf);
//	}

	@Override
	public String toString() {
		String texto = "";
		for(ClienteEntity cli : ClienteDao.getInstancia().buscarClientes()) {
			if(cli.getCpf().equals(getCpfClienteDaConta())) {
				texto = "Conta de: "+cli.getNome()+" - "+getTipoDeConta().getDescricaoDaConta()+" do cpf "+getCpfClienteDaConta()+" cadastrada na "+getCategoriaDeConta().getTipoDaCategoria().toLowerCase()+" com saldo inicial de R$ "+exibirSaldo()+" e taxa de manutenção anual de "+getTaxaManutencaoMensal();
			}
		}		
		return texto;
		
	}		
}
