package sistema;

import java.util.ArrayList;
import excecoes.ExceptionsCaixa;
import excecoes.ExceptionsAposta;
import excecoes.ExceptionsCenario;

public class Sistema {
	private Caixa caixaSistema;
	private ExceptionsCaixa exCaixa;
	private ExceptionsCenario exCenario;
	private ExceptionsAposta exAposta;
	private ArrayList<Cenario> cenarios;
	
	
	/**
	 * Starts a new system
	 * @param caixa int
	 * @param taxa double
	 */
	public Sistema(int caixa, double taxa) {
		cenarios   = new ArrayList<>();
		exCaixa    = new ExceptionsCaixa();
		exCenario  = new ExceptionsCenario();
		exAposta   = new ExceptionsAposta();

		try {
			exCaixa.testNegativeCaixa(caixa);
			exCaixa.testNegativeTaxa(taxa);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na inicializacao: " + e.getMessage());
		}
		caixaSistema = new Caixa(caixa, taxa);
	}
	
	/**
	 * Gets the system cash mount
	 * @return
	 */
	public int getCaixa() {
		return caixaSistema.getCaixa() ;
	}
	
	//**********************************************************************************
	
	/**
	 * Registers a Cenario object in the Sistema
	 * @param descricao String
	 * @return The just registered Cenario's number
	 */
	public int cadastrarCenario(String descricao) {
		try{
			exCenario.testDescription(descricao);
		}catch (NullPointerException e) {
			throw new NullPointerException("Erro no cadastro de cenario:" + e.getMessage());
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: " + e.getMessage());
		}
		Cenario c = new Cenario(descricao);
		cenarios.add(c);
		return cenarios.indexOf(c);
		
	}
	
	/**
	 * Registers a CenarioBonus object in the Sistema
	 * @param descricao String
	 * @return The just registered Cenario's number
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		try{
			exCenario.testDescription(descricao);
		}catch (NullPointerException e) {
			throw new NullPointerException("Erro no cadastro de cenario:" + e.getMessage());
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de cenario:" + e.getMessage());
		}
		Cenario c = new CenarioBonus(descricao, bonus);
		cenarios.add(c);
		caixaSistema.subtraiCaixa(bonus);
		c.getCaixa().somaCaixa(bonus);
		
		return cenarios.indexOf(c);
	}
	
	/**
	 * Shows the textual way of a Cenario object registered by its number 
	 * @param cenario int
	 * @return int number of the Cenario object - if registered-
	 */
	public String exibirCenario(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na consulta de cenario: " + e.getMessage());
		}
		return (cenarios.indexOf(cenarios.get(cenario -1)) +1) + " - " + cenarios.get(cenario -1).toString();
	}
	
	/**
	 * Textual way of all Cenario objects registered in the Sistema
	 * one per line and in order of register
	 * @return String int the way: (<number> - <description> - <status>) 
	 */
	public String exibirCenarios() {
		String s = "";
		int index = 0;
		for (Cenario c : cenarios) {
			index ++;
			s += index + " - " + c.toString() + "\n";
		}
		return s;
	}
	
	//***********************************************************************************

	/**
	 * Registers an Aposta object in a Cenario from the system by the order's number
	 * 
	 * @param cenario int
	 * @param apostador String
	 * @param valor int
	 * @param previsao String
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		try{
			exAposta.testApostador(apostador);
			exAposta.testPrevisao(previsao);
			exAposta.testValor(valor);
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro no cadastro de aposta: " + e.getMessage());
		}
		
		Aposta a = new Aposta(apostador, valor, previsao);
		if (previsao.equals("VAI ACONTECER"))
			a.setVaiAcontecer(true);
		else a.setVaiAcontecer(false);
		
		cenarios.get(cenario -1).getApostas().add(a);
			
	}
	
	/**
	 * All values of the bets in a Cenario from the system
	 * @param cenario
	 * @return
	 */
	public int valorTotalDeApostas(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: " + e.getMessage());
		}

		int total = 0;
		for (Aposta i : cenarios.get(cenario -1).getApostas()) 
			total += i.getValor();
			
		return total;
	}
	
	/**
	 * How many bets are in a Cenario object from the system
	 * @param cenario
	 * @return
	 */
	public int totalDeApostas(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na consulta do total de apostas: " + e.getMessage());
		}
		int a = 0;
		for (Aposta i : cenarios.get(cenario -1).getApostas())
			a ++;
		return a;
	}
	
	/**
	 * Textual way of all bets in a Cenario object from the system (one per line)
	 * @param cenario
	 * @return
	 */
	public String exibeApostas(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na exibicao de apostas: " + e.getMessage());
		}
		String s = "";
		for (Aposta i : cenarios.get(cenario -1).getApostas())
			s += i.toString() + "\n";
		return s;
	}
	
	//************************************************************************************
	
	/**
	 * Finish a Cenario in the system (indicating if happened or not)
	 * @param cenario int
	 * @param ocorreu boolean
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
			else if (cenarios.get(cenario -1).isFinalizado() == true)
				throw new IllegalArgumentException("Cenario ja esta fechado");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro ao fechar aposta: " + e.getMessage());
		}
		cenarios.get(cenario -1).finzaliza();
		cenarios.get(cenario -1).setOcorreu(ocorreu);
		
		if (cenarios.get(cenario -1).isOcorreu() == true) 
			cenarios.get(cenario -1).setEstado("Finalizado (ocorreu)");
		else cenarios.get(cenario -1).setEstado("Finalizado (n ocorreu)");
		
		int caixa = 0;
		if (cenarios.get(cenario -1).isFinalizado() == true)
			for (Aposta i : cenarios.get(cenario -1).getApostas())
				if (i.isVaiAcontecer() != cenarios.get(cenario -1).isOcorreu()) {
					caixa += i.getValor();
					if(i.temSeguro()) {
						caixaSistema.somaCaixa(i.getSeguro().getCusto());
						caixaSistema.subtraiCaixa(i.getSeguro().getValorSeguro());
					}
				}
		
		int taxa = (int) (caixa * caixaSistema.getTaxa());
		caixaSistema.somaCaixa(taxa);
	}
	
	/**
	 * All the value of a finished Cenario that goes to cash mount
	 * @param cenario
	 * @return
	 */
	public int getCaixaCenario(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
			else if (cenarios.get(cenario -1).isFinalizado() == false)
				throw new IllegalArgumentException("Cenario ainda esta aberto");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: " + e.getMessage());
		}
		
		int caixa = 0;
		if (cenarios.get(cenario -1).isFinalizado() == true)
			for (Aposta i : cenarios.get(cenario -1).getApostas())
				if (i.isVaiAcontecer() != cenarios.get(cenario -1).isOcorreu()) {
					caixa += i.getValor();
				}
		int taxa = (int) (caixa * caixaSistema.getTaxa());
		caixa = caixa - taxa;
		cenarios.get(cenario -1).setCaixa(caixa);
		return taxa;
	}
	
	/**
	 * All the value of a finished Cenario that goes to apportionment bettors
	 * @param cenario int
	 * @return int
	 */
	public int getTotalRateioCenario(int cenario) {
		try {
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
			else if (cenarios.get(cenario -1).isFinalizado() == false)
				throw new IllegalArgumentException("Cenario ainda esta aberto");
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: " + e.getMessage());
		}
		int valor = cenarios.get(cenario -1).getValorCaixa();
		if (cenarios.get(cenario -1) instanceof CenarioBonus)
			valor += cenarios.get(cenario -1).getBonus();
		
		
		return valor;
		}
	
	//*************************************************************************************

	/**
	 * Registers a new Aposta object with value insurance
	 * @param cenario int
	 * @param apostador String
	 * @param valor int
	 * @param previsao String 
	 * @param seguro int 
	 * @param custo int 
	 * @return index of the new ApostaSeguro registered
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int seguro, int custo) {
		try {
			exAposta.testApostador(apostador);
			exAposta.testPrevisao(previsao);
			exAposta.testValor(valor);
			exAposta.testValor(seguro);
			exAposta.testSeguroValor(custo);
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por valor: " + e.getMessage());
		}
		
		Aposta a = new Aposta(apostador, valor, previsao, seguro, custo);
		if (previsao.equals("VAI ACONTECER"))
			a.setVaiAcontecer(true);
		else a.setVaiAcontecer(false);
		
		caixaSistema.somaCaixa(a.getSeguro().getCusto());
		cenarios.get(cenario -1).getApostas().add(a);
		return cenarios.get(cenario -1).getApostas().indexOf(a);
		
	}
	
	/**
	 * Registers a new Aposta object with tax insurance
	 * @param cenario int
	 * @param apostador String
	 * @param valor int
	 * @param previsao String 
	 * @param seguro int 
	 * @param custo int 
	 * @return index of the new ApostaSeguro registered
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		try {
			exAposta.testApostador(apostador);
			exAposta.testPrevisao(previsao);
			exAposta.testValor(valor);
			exAposta.testSeguroTaxa(taxa);
			exAposta.testValor(custo);
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por taxa: " + e.getMessage());
		}
		
		Aposta a = new Aposta(apostador, valor, previsao, taxa, custo);
		if (previsao.equals("VAI ACONTECER"))
			a.setVaiAcontecer(true);
		else a.setVaiAcontecer(false);
		
		caixaSistema.somaCaixa(a.getSeguro().getCusto());
		cenarios.get(cenario -1).getApostas().add(a);
		return cenarios.get(cenario -1).getApostas().indexOf(a);
	}
	
	/**
	 * Set a new way of insurance of the object Aposta-Seguro
	 * 	it goes from tax to value
	 * @param cenario
	 * @param apostaAssegurada
	 * @param valor
	 * @return index of the object
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		try {
			exAposta.testSeguroValor(valor);
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na mudanca de aposta assegurada de taxa para valor: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro na mudanca de aposta assegurada de taxa para valor: " + e.getMessage());
		}
		Aposta a = cenarios.get(cenario -1).getApostas().get(apostaAssegurada);
		if (a.temSeguro())
			if(a.getSeguro() instanceof SeguroValor)
				((SeguroValor) a.getSeguro()).setValorSeguro(valor);
		cenarios.get(cenario -1).getApostas().get(apostaAssegurada).setIgual(a);
		
		return cenarios.get(cenario -1).getApostas().indexOf(a);
	}
	
	/**
	 * Set a new way of insurance of the object Aposta-Seguro
	 * 	it goes from value to tax
	 * @param cenario
	 * @param apostaAssegurada
	 * @param valor
	 * @return index of the object
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		try {
			exAposta.testSeguroTaxa(taxa);
			if (cenario <= 0)
				throw new IllegalArgumentException("Cenario invalido");
			else if (cenario > cenarios.size())
				throw new IllegalArgumentException("Cenario nao cadastrado");
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Erro na mudanca de aposta assegurada de valor para taxa: " + e.getMessage());
		}catch(NullPointerException e) {
			throw new NullPointerException("Erro na mudanca de aposta assegurada de valor para taxa: " + e.getMessage());
		}
		Aposta a = cenarios.get(cenario -1).getApostas().get(apostaAssegurada);
		if (a.temSeguro())
			if (a.getSeguro() instanceof SeguroTaxa)
				((SeguroTaxa) a.getSeguro()).setTaxaSeguro(taxa);
		
		cenarios.get(cenario -1).getApostas().get(apostaAssegurada).setIgual(a);
		return cenarios.get(cenario -1).getApostas().indexOf(a);
	}
	
	
	
	
	
	
	
	
}