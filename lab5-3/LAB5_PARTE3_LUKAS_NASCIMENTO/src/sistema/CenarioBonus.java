package sistema;

import excecoes.ExceptionsCenario;

public class CenarioBonus extends Cenario {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bonus;
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
		CenarioBonus other = (CenarioBonus) obj;
		if (bonus != other.bonus)
			return false;
		return true;
	}

	private int bonus;
	private ExceptionsCenario exC = new ExceptionsCenario();
	
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		try {
			exC.testDescription(descricao);
			exC.testBonus(bonus);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: " + e.getMessage());
		}this.bonus = bonus;
	}
	
	@Override
	public String toString() {
		int bonus;
		if (this.bonus >= 100) bonus = this.bonus/100;
		else bonus = this.bonus;
		return super.toString() + " - R$ " + bonus + ",00";
	}
	
	@Override
	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
