package sistema;


public abstract class Seguro{
	private SeguroValor seguroValor;
	private SeguroTaxa seguroTaxa;
	private Seguro seguro;
	
	public Seguro() {
		
	}
	
	
	public abstract int getCusto();
	
	public abstract int getValorSeguro();

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seguro == null) ? 0 : seguro.hashCode());
		result = prime * result + ((seguroTaxa == null) ? 0 : seguroTaxa.hashCode());
		result = prime * result + ((seguroValor == null) ? 0 : seguroValor.hashCode());
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
		Seguro other = (Seguro) obj;
		if (seguro == null) {
			if (other.seguro != null)
				return false;
		} else if (!seguro.equals(other.seguro))
			return false;
		if (seguroTaxa == null) {
			if (other.seguroTaxa != null)
				return false;
		} else if (!seguroTaxa.equals(other.seguroTaxa))
			return false;
		if (seguroValor == null) {
			if (other.seguroValor != null)
				return false;
		} else if (!seguroValor.equals(other.seguroValor))
			return false;
		return true;
	}
}
