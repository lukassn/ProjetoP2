package sistema;
import excecoes.ExceptionsCenario;
import java.util.ArrayList;

public class Cenario {
	private String descricao;
	private String estado;
	private boolean ocorreu, finalizado;
	private ExceptionsCenario exC = new ExceptionsCenario();
	private Caixa caixa;
	private ArrayList<Aposta> apostas;
	
	
	public Cenario(String descricao) {
		try {
			exC.testDescription(descricao);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro em Cenario: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro em Cenario: " + e.getMessage());
		}
		caixa          = new Caixa();
		this.descricao = descricao;
		estado         = "Nao finalizado";
		ocorreu        = false;
		finalizado     = false;
		apostas        = new ArrayList<>();
	}
	
	public void setCaixa(int valor) {
		caixa.setCaixa(valor);
	}
	
	public ArrayList<Aposta> getApostas() {
		return apostas;
	}
	
	public Caixa getCaixa() {
		return this.caixa;
	}

	public int getValorCaixa() {
		return caixa.getCaixa();
	}
	
	public int getBonus() {
		return 0;
	}
	
	public void finzaliza() {
		this.finalizado = true;
	}
	
	public boolean isFinalizado() {
		return this.finalizado;
	}
	
	public boolean isOcorreu() {
		return ocorreu;
	}

	public void setOcorreu(boolean ocorreu) {
		this.ocorreu = ocorreu;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return descricao + " - " + estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caixa == null) ? 0 : caixa.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (finalizado ? 1231 : 1237);
		result = prime * result + (ocorreu ? 1231 : 1237);
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
		Cenario other = (Cenario) obj;
		if (caixa == null) {
			if (other.caixa != null)
				return false;
		} else if (!caixa.equals(other.caixa))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (finalizado != other.finalizado)
			return false;
		if (ocorreu != other.ocorreu)
			return false;
		return true;
	}
	

}
