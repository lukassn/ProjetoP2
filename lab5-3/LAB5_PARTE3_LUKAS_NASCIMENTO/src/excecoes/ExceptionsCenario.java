package excecoes;

public class ExceptionsCenario {
	
	public ExceptionsCenario(){
		
	}
	
	/**
	 * This method validates the value of a Cenario's object description
	 * @param descricao 
	 */
	public void testDescription(String descricao) {
		if (descricao.equals(null))
				throw new NullPointerException("Descricao nao pode ser vazia");
		else if (descricao.length() <= 0 || descricao.trim() == "")
				throw new IllegalArgumentException("Descricao nao pode ser vazia");
	}
	
	/**
	 * This method validates the value of a CenarioBonus' object bonus
	 */
	public void testBonus(int bonus) {
		if (bonus <= 0)
			throw new IllegalArgumentException("Bonus invalido");
	}
}
