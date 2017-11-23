package br.com.argentum.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {

		// Se criar um negocio no dia 15...
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);

		// ainda que eu tente mudar a data para 20...
		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		// ele continua no dia 15
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));

	}

	@Test()
	public void naoCriaNegociacaoComDataNull() {
		try {
			new Negociacao(10, 5, null);
			fail("Deveria ter lançado exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Negociacao não pode ter Data nula"));
		}
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {

		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {

		Calendar diaMes1 = new GregorianCalendar(2017, 10, 20, 8, 30);
		Calendar diaMes2 = new GregorianCalendar(2017, 11, 20, 8, 30);

		Negociacao negociacao = new Negociacao(45.3, 100, diaMes1);
		assertFalse(negociacao.isMesmoDia(diaMes2));

	}

	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {

		Calendar diaMes1 = new GregorianCalendar(2017, 10, 20, 8, 30);
		Calendar diaMes2 = new GregorianCalendar(2018, 10, 20, 8, 30);

		Negociacao negociacao = new Negociacao(45.3, 100, diaMes1);
		assertFalse(negociacao.isMesmoDia(diaMes2));

	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5,
				negociacao6, negociacao7, negociacao8);

		CandlestickFactory fabrica = new CandlestickFactory();

		List<Candlestick> candles = fabrica.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);

	}

	@Test
	public void naoPermiteConstruirCandlesComNegociacoesForaDeOrdem() {

		try {
			Calendar hoje = Calendar.getInstance();

			Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
			Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
			Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
			Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

			Calendar amanha = (Calendar) hoje.clone();
			amanha.add(Calendar.DAY_OF_MONTH, 1);

			Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
			Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);

			Calendar depois = (Calendar) amanha.clone();
			depois.add(Calendar.DAY_OF_MONTH, 1);

			Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
			Negociacao negociacao8 = new Negociacao(52.3, 100, depois);

			List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao7, negociacao3, negociacao4,
					negociacao5, negociacao8, negociacao2, negociacao6);

			CandlestickFactory fabrica = new CandlestickFactory();

			List<Candlestick> candles = fabrica.constroiCandles(negociacoes);
			System.out.println(candles);
			
			fail("Deveria ter lançado exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("negociações em ordem errada"));
		}

	}

}
