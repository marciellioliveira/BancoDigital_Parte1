package br.com.marcielli.bancodigital.service;

import br.com.marcielli.bancodigital.dao.CartaoDeCreditoDao;
import br.com.marcielli.bancodigital.dao.CartaoDeDebitoDao;
import br.com.marcielli.bancodigital.dao.ClienteDao;
import br.com.marcielli.bancodigital.dao.ContaCorrenteDao;
import br.com.marcielli.bancodigital.dao.ContaPoupancaDao;
import br.com.marcielli.bancodigital.entity.ContaCorrenteEntity;
import br.com.marcielli.bancodigital.entity.ContaPoupancaEntity;
import br.com.marcielli.bancodigital.exception.EscolhaDosCartoesFalhouException;
import br.com.marcielli.bancodigital.exception.NumeroContasTransferenciasIguaisException;
import br.com.marcielli.bancodigital.exception.TransferirValorMenorOuIgualAZeroException;

public class ContaService {
	
	ContaCorrenteDao contaCorrenteDao = ContaCorrenteDao.getInstancia();
	ContaPoupancaDao contaPoupancaDao = ContaPoupancaDao.getInstancia();
	ClienteDao clienteDao = ClienteDao.getInstancia();	
	
	CartaoDeCreditoDao cartaoDeCreditoDao = CartaoDeCreditoDao.getInstancia();
	CartaoDeDebitoDao cartaoDeDebitoDao = CartaoDeDebitoDao.getInstancia();
	
	
	public boolean eContaCorrente(String conta) {		
	
		//Ver se conta a transferir é corrente, se sim
		for(ContaCorrenteEntity contaC : contaCorrenteDao.verContasCorrenteAdicionadas()) {
				
			if(conta.equals(contaC.getNumeroDaConta())) { //Conta a transferir é conta corrente

				return true;
			}
			
		}
		
		return false;
	}
	
	
	public boolean eContaPoupanca(String conta) {		
	
		
		for(ContaPoupancaEntity contaP : contaPoupancaDao.verContasPoupancaAdicionadas()) {
			
			if(conta.equals(contaP.getNumeroDaConta())) { //Conta a receber é conta poupança

				return true;
			}	
			
		}
		
		return false;
	}
	
	
	
	

}
