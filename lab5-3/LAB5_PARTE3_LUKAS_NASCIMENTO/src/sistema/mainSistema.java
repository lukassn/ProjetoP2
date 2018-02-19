package sistema;


public class mainSistema {
	private static Sistema sis;
	/**
	 * Linhas de Código para Teste de Saída.
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		sis = new Sistema(100000, 0.01);
		System.out.println("Caixa Sistema: " + sis.getCaixa());
		sis.cadastrarCenario("O aluno Viktor Borgino vai pagar PLP");
		sis.cadastrarCenario("Hoje vai fazer -30 graus em Campina");
		sis.cadastrarCenario("Todos os alunos comparecerao a aula de LP2 nessa terca");
		
		
		sis.cadastrarCenario("As provas vao ser corrigidas ate sexta", 1000);
		sis.cadastrarCenario("As provas nao vao ser corrigidas ate sexta", 10);
		
		sis.cadastrarAposta(4, "Matheus Melanio", 2000, "N VAI ACONTECER");
		sis.cadastrarAposta(4, "Viktor Borgino", 3000, "VAI ACONTECER");
		sis.fecharAposta(4, true);
		
		System.out.println("Caixa Sistema: " + sis.getCaixa());
		System.out.println("\n");
		
		
		sis.cadastrarAposta(1,"Matheus Melanio",10000,"N VAI ACONTECER");
		sis.cadastrarAposta(1, "Viktor Borgino",10000,"VAI ACONTECER");

		sis.cadastrarAposta(2,"Mandela Ursula",100000,"VAI ACONTECER");
		sis.cadastrarAposta(2, "Davson Sadman",150000,"VAI ACONTECER");
		sis.cadastrarAposta(2,"Taigo Leonel",10000,"N VAI ACONTECER");

		sis.cadastrarAposta(3, "Viktor Borgino",20000,"VAI ACONTECER");
		sis.cadastrarAposta(3,"Matheus Gaudencio",2000000,"VAI ACONTECER");
		sis.cadastrarAposta(3,"Higo Addommati",200000,"N VAI ACONTECER");

		sis.fecharAposta(1, false);
		sis.fecharAposta(2, true);
		
		

		
		sis.cadastrarCenario("Este cenario tem seguro");
		sis.cadastrarCenario("Este cenario tambem tem seguro", 1000);
		
		sis.cadastrarAposta(6, "Melanio", 101, "N VAI ACONTECER");
		sis.cadastrarAposta(6, "Gaudino", 201, "VAI ACONTECER");
		sis.cadastrarAposta(6, "Antunis", 301, "N VAI ACONTECER");
		sis.cadastrarAposta(6, "Mathias", 401, "VAI ACONTECER");
		
		int a = sis.cadastrarApostaSeguraValor(6, "Antonio Seguro1", 501, "VAI ACONTECER", 200, 50);
		int b = sis.cadastrarApostaSeguraTaxa(6, "Antonio Seguro2", 501, "VAI ACONTECER", 0.02, 40);
		
		System.out.println("*********************************\n");
		
		sis.alterarSeguroValor(6, b, 100);
		sis.alterarSeguroTaxa(6, a, 0.02);
		
		sis.fecharAposta(6, false);
		
		int c = sis.cadastrarApostaSeguraValor(7,"Antonio Seguro1" ,501,"VAI ACONTECER",200,50);
		int d = sis.cadastrarApostaSeguraTaxa(7,"Antonio Seguro2" ,501,"VAI ACONTECER",0.02,40);
		
		
		System.out.println(sis.exibirCenario(1));
		System.out.println(sis.exibeApostas(1));
		System.out.println("CaixaCenario: " + sis.getCaixaCenario(1));
		System.out.println("RateioCenario: " + sis.getTotalRateioCenario(1));
		
		System.out.println("-------------------");
		System.out.println(sis.exibirCenario(2));
		System.out.println(sis.exibeApostas(2));
		System.out.println("CaixaCenario: " + sis.getCaixaCenario(2));
		System.out.println("RateioCenario: " + sis.getTotalRateioCenario(2));
		
		System.out.println("-------------------");
		System.out.println(sis.exibirCenario(3));
		System.out.println(sis.exibeApostas(3));
		System.out.println("-------------------");
		System.out.println(sis.exibirCenario(4));
		System.out.println(sis.exibeApostas(4));
		System.out.println("CaixaCenario: " + sis.getCaixaCenario(2));
		System.out.println("RateioCenario: " + sis.getTotalRateioCenario(2));
		System.out.println("-------------------");
		System.out.println(sis.exibirCenario(5));
		System.out.println(sis.exibeApostas(5));
		System.out.println("-------------------");
		System.out.println(sis.exibirCenario(6));
		System.out.println(sis.exibeApostas(6));
		System.out.println("CaixaCenario: " + sis.getCaixaCenario(6));
		System.out.println("RateioCenario: " + sis.getTotalRateioCenario(6));
		
		System.out.println("\n\n\nCaixa Sistema: " + sis.getCaixa());
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
