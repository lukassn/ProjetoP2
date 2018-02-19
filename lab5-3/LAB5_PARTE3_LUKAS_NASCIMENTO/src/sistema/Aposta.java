package sistema;

import excecoes.ExceptionsAposta;
public class Aposta {
	private String apostador, previsao;
	private int valor;
	private boolean vaiAcontecer, temSeguro;
	private ExceptionsAposta exA = new ExceptionsAposta();
	private Seguro s;
	private Aposta a;
	
	/**
	 * Constructor of Aposta object
	 * @param apostador String - punter's name/nick
	 * @param valor int - bet's value
	 * @param previsao String - bet's prediction
	 */
	public Aposta(String apostador, int valor, String previsao) {
		exA.testApostador(apostador);
		exA.testPrevisao(previsao);
		exA.testValor(valor);
		this.apostador = apostador;
		this.previsao = previsao;
		this.valor = valor;
		if(previsao.equals("VAI ACONTECER"))
			vaiAcontecer = true;
		else if (previsao.equals("N VAI ACONTECER"))
			vaiAcontecer = false;
		temSeguro = false;
	}
	
	public Aposta(String apostador, int valor, String previsao, int seguro, int custo) {
		exA.testApostador(apostador);
		exA.testPrevisao(previsao);
		exA.testValor(valor);
		exA.testSeguroValor(seguro);
		exA.testValor(custo);
		this.apostador = apostador;
		this.previsao = previsao;
		this.valor = valor;
		if(previsao.equals("VAI ACONTECER"))
			vaiAcontecer = true;
		else if (previsao.equals("N VAI ACONTECER"))
			vaiAcontecer = false;
		temSeguro = true;
		s = new SeguroValor(seguro, custo);
	}
	
	public Aposta(String apostador, int valor, String previsao, double seguro, int custo) {
		exA.testApostador(apostador);
		exA.testPrevisao(previsao);
		exA.testValor(valor);
		exA.testSeguroTaxa(seguro);
		exA.testValor(custo);
		this.apostador = apostador;
		this.previsao = previsao;
		this.valor = valor;
		if(previsao.equals("VAI ACONTECER"))
			vaiAcontecer = true;
		else if (previsao.equals("N VAI ACONTECER"))
			vaiAcontecer = false;
		temSeguro = true;
		s = new SeguroTaxa(seguro, custo);
	}
	
	public void setIgual(Aposta a) {
		this.a = a;
	}
	
	public boolean temSeguro() {
		return temSeguro;
	}

	public Seguro getSeguro() {
		return s;
	}
	
	public String getApostador() {
		return apostador;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getPrevisao() {
		return previsao;
	}

	public void setPrevisao(String previsao) {
		this.previsao = previsao;
	}

	public void setVaiAcontecer(boolean vaiAcontecer) {
		this.vaiAcontecer = vaiAcontecer;
	}

	public String toString() {
		String s =  apostador + " - R$" + (valor/100) + ",00 - ";
		if (vaiAcontecer == false) s += "N VAI ACONTECER";
		else s += "VAI ACONTECER";
		return s;
	}
	
	public int getValor() {
		return valor;
	}

	public boolean isVaiAcontecer() {
		return vaiAcontecer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apostador == null) ? 0 : apostador.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + (vaiAcontecer ? 1231 : 1237);
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (apostador == null) {
			if (other.apostador != null)
				return false;
		} else if (!apostador.equals(other.apostador))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (vaiAcontecer != other.vaiAcontecer)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
}
