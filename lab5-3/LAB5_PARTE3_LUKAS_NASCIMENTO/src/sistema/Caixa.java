package sistema;

import excecoes.ExceptionsCaixa;
public class Caixa {
	private ExceptionsCaixa exC = new ExceptionsCaixa();
	private int caixa;
	private double taxa;
	
	public Caixa() {
		caixa = 0;
		taxa = 0;
	}
	
	public Caixa(int caixa, double taxa) {
		try {
			exC.testNegativeCaixa(caixa);
			exC.testNegativeTaxa(taxa);
			
		}catch(IllegalArgumentException e) {
			if (caixa <= 0)
				throw new IllegalArgumentException("Erro em Caixa: " + e.getMessage());
		}this.caixa = caixa;
		this.taxa = taxa;
		
		
	}

	public void setCaixa(int valor) {
		this.caixa = valor;
	}

	public int getCaixa() {
		return caixa;
	}

	public double getTaxa() {
		return taxa;
	}

	public void somaCaixa(int valor) {
		caixa += valor;
	}
	
	public void subtraiCaixa(int valor) {
		caixa -= valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caixa;
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Caixa other = (Caixa) obj;
		if (caixa != other.caixa)
			return false;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		return true;
	}
	
	
	
}
