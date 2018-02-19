package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import sistema.Sistema;

public class SistemaTest {

	private Sistema sis;
	
	// Construtor de Sistemas
	@Test (expected = IllegalArgumentException.class) 
	public void construtorSistemaCaixaNegativo() {
		sis = new Sistema(-1, 0.5);
	}

	@Test
	public void construtorSistemaCaixaZero() {
		sis = new Sistema(0, 0.5);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void construtorSistemaTaxaNegativa() {
		sis = new Sistema(100, -2);
	}
	
	@Test 
	public void construtorSistemaTaxaZero() {
		sis = new Sistema(100, 0);
	}
	
	@Test
	public void construtorSistemaPositivo() {
		sis = new Sistema(100, 0.4);
	}

	//***************************************************************
	// Cadastro de Cenários
	@Test (expected = IllegalArgumentException.class) 
	public void cadastrarCenarioDescricaoVazia() {
		sis = new Sistema(100, 0.4);
		sis.cadastrarCenario("");
	}
	
	@Test (expected = NullPointerException.class) 
	public void cadastrarCenarioDescricaoNula() {
		sis = new Sistema(100, 0.4);
		sis.cadastrarCenario(null);
	}
	
	@Test
	public void cadastrarCenario() {
		sis = new Sistema(100, 0.4);
		sis.cadastrarCenario("Perderei 2kg esse mês");
	}

	@Test
	public void cadastrarMaisDeUmCenario() {
		sis = new Sistema(100, 0.4);
		sis.cadastrarCenario("Real Madrid ganhará a Champions");
		sis.cadastrarCenario("Eagles ganhará o SuperBowl");
	}
	
	//******************************************************************
	// Exibir Cenario
		@Test (expected = IllegalArgumentException.class) 
		public void exibirCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(-1);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void exibirCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(0);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void exibirCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(1);
		}
		
		@Test ()
		public void exibirCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario");
			sis.exibirCenario(1);
		}

		@Test 
		public void exibirMaisDeUmCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario");
			sis.cadastrarCenario("Segundo cenario");
			assertEquals("1 - Primeiro cenario - Nao finalizado", sis.exibirCenario(1));
			assertEquals("2 - Segundo cenario - Nao finalizado", sis.exibirCenario(2));
		}
//**************************************************************************************************

// Busca de Valor em Caixa de um Cenário
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(0);
		}

		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioCadastradoAberto() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Cenario criado");
			sis.getCaixaCenario(1);
		}
		
		@Test
		public void getCaixaDeCenarioCadastradoFechadoSemDinheiro() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Cenario criado");
			sis.fecharAposta(1, true);
			assertEquals(0, sis.getCaixaCenario(1));
		}
		
		@Test
		public void getCaixaDeCenarioCadastradoFechadoComDinheiro() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Brasil Hexa 2018");
			sis.cadastrarAposta(1, "Neymar", 4, "VAI ACONTECER");
			sis.cadastrarAposta(1, "Messi", 2000, "N VAI ACONTECER");
			sis.fecharAposta(1, true);
			assertEquals(800, sis.getCaixaCenario(1));
		}
	//********************************************************************************************
		
		// Busca de Valor em Premiações de um Cenário
		@Test (expected = IllegalArgumentException.class)
		public void getRateioDeCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.getTotalRateioCenario(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getRateioDeCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.getTotalRateioCenario(0);
		}

		@Test (expected = IllegalArgumentException.class)
		public void getRateioDeCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.getTotalRateioCenario(1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getRateioDeCenarioCadastradoAberto() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Brasil Hexa 2018");
			sis.getTotalRateioCenario(1);
		}
		
		@Test
		public void getRateioDeCenarioCadastradoFechadoSemDinheiro() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Brasil Hexa 2018");
			sis.fecharAposta(1, true);
			assertEquals(0, sis.getTotalRateioCenario(1));
		}
		
		@Test
		public void getRateioDeCenarioCadastradoFechadoComDinheiro() {
			sis = new Sistema(1000, 0.5);
			sis.cadastrarCenario("Brasil Hexa 2018");
			sis.cadastrarAposta(1, "Neymar", 4000, "VAI ACONTECER");
			sis.cadastrarAposta(1, "Messi", 2000, "N VAI ACONTECER");
			sis.fecharAposta(1, true);
			assertEquals(0, sis.getTotalRateioCenario(1));
		}
//******************************************************************************************************
		// Busca de Valor Total em uma Aposta
		@Test (expected = IllegalArgumentException.class)
		public void valorTotalDeApostaDeCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Lewandovisk no Real Madrid 2018");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			sis.valorTotalDeApostas(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void valorTotalDeApostaDeCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Lewandovski no Real Madrid 2018");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			sis.valorTotalDeApostas(0);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void valorTotalDeApostaDeCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Lewandovski no Real Madrid 2018");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			sis.valorTotalDeApostas(2);
		}
		
		@Test
		public void valorTotalDeApostaDeCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Lewandovski no Real Madrid 2018");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			assertEquals(100, sis.valorTotalDeApostas(1));
		}
		
		@Test
		public void valorTotalDeApostaDeCenarioPositivo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Lewandovisk no Real Madrid 2018");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			assertEquals(100, sis.valorTotalDeApostas(1));
		}
//***********************************************************************************************

		
		// Busca de Total de Apostas
		@Test (expected = IllegalArgumentException.class)
		public void buscaTotalDeApostaDeCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Lukas", 100, "VAI ACONTECER");
			sis.valorTotalDeApostas(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void buscaTotalDeApostaDeCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Lukas", 100, "VAI ACONTECER");
			sis.valorTotalDeApostas(0);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void buscaTotalDeApostaDeCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Lukas", 100, "VAI ACONTECER");
			sis.valorTotalDeApostas(2);
		}
		
		@Test
		public void buscaTotalDeApostaDeCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Lukas", 100, "VAI ACONTECER");
			
			assertEquals(100, sis.valorTotalDeApostas(1));
		}
		
		// Exibir Lista de Cenários
		@Test
		public void exibirComNenhumCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			assertEquals("", sis.exibirCenarios());	
		}
		
		@Test
		public void exibirComUmCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			assertEquals("1 - LAB 5 demorado - Nao finalizado\n", sis.exibirCenarios());	
		}
		
		@Test
		public void exibirListaDeMaisDeUmCenarioCadastradoNaoFinalizado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarCenario("LAB 5 demorado demais");
			assertEquals("1 - LAB 5 demorado - Nao finalizado\n" +
					     "2 - LAB 5 demorado demais - Nao finalizado\n", sis.exibirCenarios());	
		}
		
		@Test
		public void exibirListaDeMaisDeUmCenarioCadastradoFinalizado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarCenario("LAB 5 demorado demais");
			assertEquals("1 - LAB 5 demorado - Nao finalizado\n" +
					     "2 - LAB 5 demorado demais - Nao finalizado\n", sis.exibirCenarios());
			
			sis.fecharAposta(1, true);
			assertEquals("1 - LAB 5 demorado - Finalizado (ocorreu)\n" +
				     "2 - LAB 5 demorado demais - Nao finalizado\n", sis.exibirCenarios());
			
			sis.fecharAposta(2, false);
			assertEquals("1 - LAB 5 demorado - Finalizado (ocorreu)\n" +
				     "2 - LAB 5 demorado demais - Finalizado (n ocorreu)\n", sis.exibirCenarios());
		}

		// Exibir Apostas
		@Test (expected = IllegalArgumentException.class)
		public void exibirApostasDeCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			sis.exibeApostas(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void exibirApostasDeCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			sis.exibeApostas(0);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void exibirApostasDeCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Midia", 100, "VAI ACONTECER");
			
			sis.exibeApostas(2);
		}
		
		@Test
		public void exibirApostasDeCenarioComUmaApostaCadastrada() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "MP", 100, "VAI ACONTECER");
			
			assertEquals("MP - R$1,00 - VAI ACONTECER\n", sis.exibeApostas(1));
		}
		
		@Test
		public void exibirApostasDeCenarioComMaisUmaApostaCadastrada() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("The bomb has been planted?");
			sis.cadastrarAposta(1, "MP", 100, "N VAI ACONTECER");
			sis.cadastrarAposta(1, "Lukas", 10000, "VAI ACONTECER");
			
			assertEquals("MP - R$1,00 - N VAI ACONTECER\n" +
						 "Lukas - R$100,00 - VAI ACONTECER\n", sis.exibeApostas(1));
		}

		// Cadastrar Apostas
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaComCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarAposta(-1, "Michel Temer", 1, "VAI ACONTECER");
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaComCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarAposta(0, "Bolsonaro", 10, "N VAI ACONTECER");
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaComCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarAposta(1, "Merda", 100, "VAI ACONTECER");
		}
		
		@Test
		public void cadastrarApostaComCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("ISSO NAO ACABA");
			sis.cadastrarAposta(1, "Cidadão Brasileiro", 3, "VAI ACONTECER");
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaComApostadorVazio() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("ISSO NAO ACABA");
			sis.cadastrarAposta(1, "", 100, "VAI ACONTECER");
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaComApostadorVazioTambem() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("ISSO NAO ACABA");
			sis.cadastrarAposta(1, "  ", 100, "VAI ACONTECER");
		}
		
		@Test (expected = NullPointerException.class)
		public void cadastrarApostaComApostadorNulo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("ISSO NAO ACABA");
			sis.cadastrarAposta(1, null, 100, "VAI ACONTECER");
		}

		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaDeValorNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Fim do Mundo");
			sis.cadastrarAposta(1, "Você sabe quem", -1000, "VAI ACONTECER");
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaDeValorZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Vtnc");
			sis.cadastrarAposta(1, "Cidadão Brasileiro", 0, "N VAI ACONTECER");
		}

		@Test
		public void cadastrarApostaDeValorPositivo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Loteria 1bi");
			sis.cadastrarAposta(1, "Burgues safado", 20000, "VAI ACONTECER");
		}

		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaDePrevisaoVazia() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("The bomb has been planted!");
			sis.cadastrarAposta(1, "Midia", 100, "");
		}
		
		@Test (expected = NullPointerException.class)
		public void cadastrarApostaDePrevisaoNula() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("The bomb has been planted?");
			sis.cadastrarAposta(1, "Kin jog un", 100, null);
		}

		@Test (expected = IllegalArgumentException.class)
		public void cadastrarApostaDePrevisaoDeEspacos() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Fim do Mundo");
			sis.cadastrarAposta(1, "Russo", 100, "  ");
		}

		@Test
		public void cadastrarApostaDePrevisaoValida() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Fim do Mundo");
			sis.cadastrarAposta(1, "Beerus", 100000, "VAI ACONTECER");
		}

		// Fechar Apostas
		@Test (expected = IllegalArgumentException.class)
		public void fecharApostasComCenarioNegativo() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Fim do Mundo");
			sis.cadastrarAposta(1, "Beerus", 10, "N VAI ACONTECER");
			
			sis.fecharAposta(-1, true);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void fecharApostasComCenarioZero() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Fim do Mundo");
			sis.cadastrarAposta(1, "Beerus", 10, "N VAI ACONTECER");
			
			sis.fecharAposta(0, true);
		}

		@Test (expected = IllegalArgumentException.class)
		public void fecharApostasComCenarioNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.fecharAposta(1, true);
		}
		
		@Test
		public void fecharApostasComCenarioCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Boo", 100, "N VAI ACONTECER");
			
			sis.fecharAposta(1, true);
		}

		@Test (expected = IllegalArgumentException.class)
		public void fecharApostasJaFechadas() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("LAB 5 demorado");
			sis.cadastrarAposta(1, "Vegeta", 0, "N VAI ACONTECER");
			
			sis.fecharAposta(1, true);
			sis.fecharAposta(1, true);
		}

		// Busca de Valor Caixa do Sistema
		@Test
		public void buscaDeValorCaixaPadrao() {
			sis = new Sistema(100, 0.4);
			assertEquals(100, sis.getCaixa());
		}
		
		@Test
		public void buscaDeValorCaixaComputado() {
			sis = new Sistema(100, 0.4);
			
			sis.cadastrarCenario("Brasil campeão 2018?");
			
			sis.cadastrarAposta(1, "Lukas", 120, "VAI ACONTECER");
			sis.cadastrarAposta(1, "Freeza", 7000, "N VAI ACONTECER");
			sis.cadastrarAposta(1, "Cell", 8001, "N VAI ACONTECER");
			
			sis.fecharAposta(1, true);
			
			assertEquals(6100, sis.getCaixa());
		}
		
		//******************************************************************************
		//Cadastro de CenarioBonus
		@Test (expected = IllegalArgumentException.class) 
		public void cadastrarCenarioBonusDescricaoVazia() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("", 10000);
		}
		
		@Test (expected = NullPointerException.class) 
		public void cadastrarCenarioBonusDescricaoNula() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario(null, 10000);
		}
		
		@Test
		public void cadastrarCenarioBonus() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Perderei 2kg esse mês", 1000000000);
		}

		@Test
		public void cadastrarMaisDeUmCenarioBonus() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Real Madrid ganhará a Champions", 1300);
			sis.cadastrarCenario("Eagles ganhará o SuperBowl", 1000);
		}
		
		//Exibir CenarioBonus
		
		@Test (expected = IllegalArgumentException.class) 
		public void exibirCenarioBonusNegativo() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(-1);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void exibirCenarioBonusZero() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(0);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void exibirCenarioBonusNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.exibirCenario(1);
		}
		
		@Test ()
		public void exibirCenarioBonusCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario", 1000);
			sis.exibirCenario(1);
		}

		@Test 
		public void exibirMaisDeUmCenarioBonusCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario", 1000);
			sis.cadastrarCenario("Segundo cenario", 1000);
			sis.exibirCenario(1);
			sis.exibirCenario(2);
		}
		
		@Test ()
		public void exibirCenarioBonusCadastradoAssert() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario", 1000);
			sis.exibirCenario(1);
		}

		@Test 
		public void exibirMaisDeUmCenarioBonusCadastradoAssert() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario", 1000);
			sis.cadastrarCenario("Segundo cenario", 1000);
			
			assertEquals("1 - Primeiro cenario - Nao finalizado - R$ 10,00", sis.exibirCenario(1));
			assertEquals("2 - Segundo cenario - Nao finalizado - R$ 10,00", sis.exibirCenario(2));
		}
		
		@Test
		public void exibirMaisDeUmCenarioBonusCadastradoFinalizadoAssert() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Primeiro cenario", 1000);
			sis.cadastrarCenario("Segundo cenario", 1000);
			sis.fecharAposta(1, true);
			sis.fecharAposta(2, false);
			assertEquals("1 - Primeiro cenario - Finalizado (ocorreu) - R$ 10,00", sis.exibirCenario(1));
			assertEquals("2 - Segundo cenario - Finalizado (n ocorreu) - R$ 10,00", sis.exibirCenario(2));
		}
		//***********************************************************************************8
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioBonusNegativo() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(-1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenariBonusoZero() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(0);
		}

		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioBonusNaoCadastrado() {
			sis = new Sistema(100, 0.4);
			sis.getCaixaCenario(1);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getCaixaDeCenarioBonusCadastradoAberto() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Cenario criado", 1000);
			sis.getCaixaCenario(1);
		}
		
		@Test
		public void getCaixaDeCenarioBonusCadastradoFechadoSemDinheiro() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Cenario criado");
			sis.fecharAposta(1, true);
			assertEquals(0, sis.getCaixaCenario(1));
		}
		
		@Test
		public void getCaixaDeCenarioBonusCadastradoFechadoComDinheiro() {
			sis = new Sistema(100, 0.4);
			sis.cadastrarCenario("Brasil Hexa 2018", 1000);
			sis.cadastrarAposta(1, "Neymar", 4, "VAI ACONTECER");
			sis.cadastrarAposta(1, "Messi", 2000, "N VAI ACONTECER");
			sis.fecharAposta(1, true);
			assertEquals(800, sis.getCaixaCenario(1));
		}
		
		//******************************************************************************
		// Busca de Valor em Premiações de um Cenário
				@Test (expected = IllegalArgumentException.class)
				public void getRateioDeCenarioBonusNegativo() {
					sis = new Sistema(100, 0.4);
					sis.getTotalRateioCenario(-1);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void getRateioDeCenarioBonusZero() {
					sis = new Sistema(100, 0.4);
					sis.getTotalRateioCenario(0);
				}

				@Test (expected = IllegalArgumentException.class)
				public void getRateioDeCenarioBonusNaoCadastrado() {
					sis = new Sistema(100, 0.4);
					sis.getTotalRateioCenario(1);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void getRateioDeCenarioBonusCadastradoAberto() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Brasil Hexa 2018", 1000);
					sis.getTotalRateioCenario(1);
				}
				
				@Test
				public void getRateioDeCenarioBonusCadastradoFechadoSemDinheiro() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Brasil Hexa 2018", 1000);
					sis.fecharAposta(1, true);
					assertEquals(2000, sis.getTotalRateioCenario(1));
				}
				
				@Test
				public void getRateioDeCenarioBonusCadastradoFechadoComDinheiro() {
					sis = new Sistema(1000, 0.5);
					sis.cadastrarCenario("Brasil Hexa 2018", 1000);
					sis.cadastrarAposta(1, "Neymar", 4000, "VAI ACONTECER");
					sis.cadastrarAposta(1, "Messi", 2000, "N VAI ACONTECER");
					sis.fecharAposta(1, true);
					assertEquals(2000, sis.getTotalRateioCenario(1));
				}
				//******************************************************************************************************
				// Cadastrar Apostas
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorComCenarioNegativo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraValor(-1, "Todos vamos morrer", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorComCenarioZero() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraValor(0, "Todos vamos morrer", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorComCenarioNaoCadastrado() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraValor(1, "Eu vou morrer", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test
				public void cadastrarApostaSeguraValorComCenarioCadastrado() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraValor(1, "Você vai morrer", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorComApostadorVazio() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraValor(1, "", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorComApostadorInvalido() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraValor(1, "  ", 1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = NullPointerException.class)
				public void cadastrarApostaSeguraValorComApostadorNulo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraValor(1, null, 1, "VAI ACONTECER", 1000, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorDeValorNegativo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraValor(-1, "Todos vamos morrer", -1, "VAI ACONTECER", 1000, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorDeValorZero() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Vtnc");
					sis.cadastrarApostaSeguraValor(1, "Cidadão Brasileiro", 0, "N VAI ACONTECER", 1000, 100);
				}

				@Test
				public void cadastrarApostaSeguraValorDeValorPositivo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Feudalismo");
					sis.cadastrarApostaSeguraValor(1, "Burgues safado", 20000, "VAI ACONTECER", 1000, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorDePrevisaoVazia() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("SlenderMan");
					sis.cadastrarApostaSeguraValor(1, "Atrás de você", 100, "", 1000, 100);
				}
				
				@Test (expected = NullPointerException.class)
				public void cadastrarApostaSeguroValorDePrevisaoNula() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("The bomb has been planted?");
					sis.cadastrarApostaSeguraValor(1, "Kin jog un", 100, null, 1000, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraValorDePrevisaoInvalida() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraValor(1, "Russo", 100, "  ", 1000, 100);
				}

				@Test
				public void cadastrarApostaSeguroValorDePrevisaoValida() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraValor(1, "Beerus", 100000, "VAI ACONTECER", 1000, 100);
				}
				
				//----- CADASTRAR APOSTA SEGURA TAXA ---------------------
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaComCenarioNegativo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraTaxa(-1, "Todos vamos morrer", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaComCenarioZero() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraTaxa(0, "Todos vamos morrer", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaComCenarioNaoCadastrado() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarApostaSeguraTaxa(1, "Todos vamos morrer", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test
				public void cadastrarApostaSeguraTaxaComCenarioCadastrado() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraTaxa(1, "Todos vamos morrer", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaComApostadorVazio() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraTaxa(1, "", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaComApostadorInvalido() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraTaxa(1, "  ", 1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = NullPointerException.class)
				public void cadastrarApostaSeguraTaxaComApostadorNulo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("ISSO NAO ACABA");
					sis.cadastrarApostaSeguraTaxa(1, null, 1, "VAI ACONTECER", 0.1, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaDeValorNegativo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraTaxa(1, "Iae man", -1, "VAI ACONTECER", 0.1, 100);
				}
				
				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaDeValorZero() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Vtnc");
					sis.cadastrarApostaSeguraTaxa(1, "Cidadao Brasileiro - by Temer", 0, "VAI ACONTECER", 0.1, 100);
				}

				@Test
				public void cadastrarApostaSeguraTaxaDeValorPositivo() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Feudalismo");
					sis.cadastrarApostaSeguraTaxa(1, "Burguesia", 1, "VAI ACONTECER", 0.1, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaDePrevisaoVazia() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("SlenderMan");
					sis.cadastrarApostaSeguraTaxa(1, "Atrás de você", 100, "", 0.1, 100);
				}
				
				@Test (expected = NullPointerException.class)
				public void cadastrarApostaSeguroTaxaDePrevisaoNula() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("The bomb has been planted?");
					sis.cadastrarApostaSeguraTaxa(1, "Kin jog un", 100, null, 0.1, 100);
				}

				@Test (expected = IllegalArgumentException.class)
				public void cadastrarApostaSeguraTaxaDePrevisaoInvalida() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraTaxa(1, "Russo", 100, "  ", 0.1, 100);
				}

				@Test
				public void cadastrarApostaSeguroTaxaDePrevisaoValida() {
					sis = new Sistema(100, 0.4);
					sis.cadastrarCenario("Fim do Mundo");
					sis.cadastrarApostaSeguraTaxa(1, "Beerus", 100000, "VAI ACONTECER", 0.1, 100);
				}
}