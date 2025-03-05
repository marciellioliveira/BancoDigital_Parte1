package br.com.marcielli.bancodigital.entity;

import br.com.marcielli.bancodigital.service.ClienteService;

public  class Endereco { //A IDEIA DO OBJETO
	
	private String cep;
	private String cidade;
	private String estado;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
		
	public Endereco(String cep, String cidade, String estado, String rua, String numero, String bairro,
			String complemento) {
		super();
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
	}

	public Endereco() {
		
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}	

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String mascararCep(String cep) {				
		String cepPart1 = "";
		String line = "-";
		String cepPart2="";
		String novoCep = "";		
		
		cepPart1 = cep.substring(0,5);
		cepPart2 = cep.substring(5,8);		
		
		novoCep = cepPart1.concat(line).concat(cepPart2);
		
		return novoCep;
	}

	
	
	@Override
	public String toString() {
		if(complemento.equals("")) {
			setComplemento("N/C");
		}
		
		mascararCep(cep);
		
		return "COMPLETO: Rua "+rua+", n° "+numero+", Bairro "+bairro+", Complemento "+complemento+", Cidade "+cidade+"/"+estado+", Cep "+mascararCep(cep);
	}	
}
