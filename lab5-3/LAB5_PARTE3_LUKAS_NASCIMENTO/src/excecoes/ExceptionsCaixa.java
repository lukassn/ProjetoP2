package excecoes;

public class ExceptionsCaixa {
	
	public ExceptionsCaixa() {
		
	}
	/**
	 * This method validates the cash to initialize a new object
	 * @param cash
	 */
	public void testNegativeCaixa(int caixa) {
		if (caixa < 0)
			throw new IllegalArgumentException("Caixa nao pode ser inferior a 0");
	}
	
	/**
	 * This method validates the tax to initialize
	 * @param tax
	 */
	public void testNegativeTaxa(double tax) {
		if (tax < 0)
			throw new IllegalArgumentException("Taxa nao pode ser inferior a 0");
	}

}
