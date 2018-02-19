package sistema;

import excecoes.ExceptionsAposta;

public class SeguroTaxa extends Seguro {


	private ExceptionsAposta exA = new ExceptionsAposta();
	private String string;
	private double seguro;
	private int custo;
	
	/**
	 * Constructor of Seguro object with tax insurance
	 * @param seguro double
	 * @param custo int
	 */
	public SeguroTaxa(double seguro, int custo) {
		super();
		exA.testSeguroTaxa(seguro);
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
		return (int) seguro;
	}
	
	public void setTaxaSeguro(double seguro) {
		this.seguro = seguro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + custo;
		long temp;
		temp = Double.doubleToLongBits(seguro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SeguroTaxa other = (SeguroTaxa) obj;
		if (custo != other.custo)
			return false;
		if (Double.doubleToLongBits(seguro) != Double.doubleToLongBits(other.seguro))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}
}
