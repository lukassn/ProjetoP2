package excecoes;

public class ExceptionsAposta {
	
	public ExceptionsAposta() {
		
	}

	/**
	 * Exception for null or empty String 
	 * @param apostador
	 */
	public void testApostador(String apostador) {
		boolean erro = true;
		if (apostador == "")
			throw new IllegalArgumentException("Apostador nao pode ser vazio ou nulo");
		if (apostador .equals(null))
			throw new IllegalArgumentException("Apostador nao pode ser vazio ou nulo");
		for (int i = 0; i < apostador.length(); i++)
			if (apostador.charAt(i) != ' ') 
				erro = false;
		if (erro)
			throw new IllegalArgumentException("Apostador nao pode ser vazio ou nulo");
		}
	
	/**
	 * Exception for negative value of int parameter
	 * @param valor
	 */
	public void testValor(int valor) {
		if (valor <= 0)
			throw new IllegalArgumentException("Valor nao pode ser menor ou igual a zero");
	}
	
	/**
	 * Exception for null, empty or invalid parameter
	 * @param previsao
	 */
	public void testPrevisao(String previsao) {
		if (previsao.equals(null))
			throw new IllegalArgumentException("Previsao nao pode ser vazia ou nula");
		
		boolean erro = true;
		for (int i = 0; i < previsao.length(); i++)
			if (previsao.charAt(i) != ' ')
				erro = false;
		if (erro)
			throw new IllegalArgumentException("Previsao nao pode ser vazia ou nula");
		if (!previsao.equals("N VAI ACONTECER") && !previsao.equals("VAI ACONTECER")) 
			throw new IllegalArgumentException("Previsao invalida");
	}
	
	/**
	 * Exception for negative or invalid parameter
	 */
	public void testSeguroValor(int seguro) {
		if (seguro <= 0)
			throw new IllegalArgumentException("Valor nao pode ser menor ou igual a zero");

	}
	
	/**
	 * Exception for negative or invalid parameter
	 */
	public void testSeguroTaxa(double seguro) {
		if (seguro <= 0)
			throw new IllegalArgumentException("Valor nao pode ser menor ou igual a zero");
	}
	
}