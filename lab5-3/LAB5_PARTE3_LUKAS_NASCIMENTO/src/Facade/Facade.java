package Facade;

import easyaccept.EasyAccept;
import sistema.Sistema;

public class Facade {
	private Sistema s;
	
	public static void main(String[] args) {
		args = new String[] {"Facade.Facade", "acceptance_test2/us1_test (2).txt",
										"acceptance_test2/us2_test (2).txt",
										"acceptance_test2/us3_test (2).txt",
										"acceptance_test2/us4_test (2).txt",
										"acceptance_test2/us5_test.txt",
										"acceptance_test2/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	public void inicializa(int caixa, double taxa) {
		s = new Sistema(caixa, taxa);
	}
	
	public int getCaixa() {
		return s.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		return s.cadastrarCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		return s.cadastrarCenario(descricao, bonus);
	}
	
	public String exibirCenario(int cenario) {
		return s.exibirCenario(cenario);
	}
	
	public String exibirCenarios() {
		return s.exibirCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao){
		s.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return s.valorTotalDeApostas(cenario);
	}
	
	public int totalDeApostas(int cenario) {
		return s.totalDeApostas(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return s.exibeApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		s.fecharAposta(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		return s.getCaixaCenario(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return s.getTotalRateioCenario(cenario);
	}
	
	
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorS, int custo) {
		return s.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorS, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return s.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return s.alterarSeguroValor(cenario, apostaAssegurada, valor);
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurado, double taxa) {
		return s.alterarSeguroTaxa(cenario, apostaAssegurado, taxa);
	}
	

}
