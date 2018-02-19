package sistema;

import excecoes.ExceptionsAposta;

public class SeguroValor extends Seguro {

	private ExceptionsAposta exA = new ExceptionsAposta();
	private String string;
	private int seguro;
	private int custo;
	
	/**
	 * Constructor of Seguro object with value insurance
	 * @param seguro int
	 * @param custo int
	 */
	public SeguroValor(int seguro, int custo) {
		super();
		exA.testSeguroValor(seguro);
		this.seguro = seguro;
		this.custo = custo;
	}
	
	@Override
	public String toString() {
		return string;
	}
	
	public int getCusto() {
		return custo;
	}
	
	public int getValorSeguro() {
		return seguro;
	}
	
	public void setValorSeguro(int valor) {
		this.seguro = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + custo;
		result = prime * result + seguro;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeguroValor other = (SeguroValor) obj;
		if (custo != other.custo)
			return false;
		if (seguro != other.seguro)
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

}
