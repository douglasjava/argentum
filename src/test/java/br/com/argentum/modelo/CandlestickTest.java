package br.com.argentum.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		try {
			new Candlestick(10.0, 20.0, 30.0, 15.0, 1000, Calendar.getInstance());
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Pre�o maximo n�o pode ser menor que o pre�o minimo"));
		}
	}

	@Test
	public void naoCriaCandlestickComDataNula() {
		try {
			// new Candlestick(abertura, fechamento, minimo, maximo, volume, data)
			new Candlestick(10.0, 23.0, 58.0, 90.0, 2000, null);
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Data n�o pode ser nula"));
		}
	}

	@Test
	public void naoCriaCandlestickComPrecoAberturaNegativo() {
		try {
			new Candlestick(-10, 25.0, 5.0, 63.0, 2000, Calendar.getInstance());
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Pre�o de abertura n�o pode ser negativo"));
		}
	}

	@Test
	public void naoCriaCandlestickComPrecoFechamentoNegativo() {
		try {
			// new Candlestick(abertura, fechamento, minimo, maximo, volume, data)
			new Candlestick(20.0, -25.0, 5.0, 63.0, 2000, Calendar.getInstance());
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Pre�o de fechamento n�o pode ser negativo"));
		}
	}

	@Test
	public void naoCriaCandlestickComPrecoMinimoNegativo() {
		try {
			// new Candlestick(abertura, fechamento, minimo, maximo, volume, data)
			new Candlestick(20.0, 85.0, -5.0, 63.0, 2000, Calendar.getInstance());
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Pre�o de minimo n�o pode ser negativo"));
		}
	}

	@Test
	public void naoCriaCandlestickComPrecoMaximoNegativo() {
		try {
			// new Candlestick(abertura, fechamento, minimo, maximo, volume, data)
			new Candlestick(10.0, 25.0, 5.0, -63.0, 2000, Calendar.getInstance());
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Pre�o de m�ximo n�o pode ser negativo"));
		}
	}

	@Test
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		try {

			new CandleBuilder().comFechamento(50.0).comMinimo(5.0).comMaximo(65.0).comVolume(1500)
					.comData(Calendar.getInstance()).geraCandle();
			fail("Deveria ter lan�ado exce��o");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage(), is("M�todo geraCandle foi chamado antes dos outros"));
		}
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		//new Candlestick(abertura, fechamento, minimo, maximo, volume, data)
		Candlestick candle = new Candlestick(10.0, 10.0, 5.0, 63.0, 2000, Calendar.getInstance());
		
		assertEquals(candle.isAlta(), true);
	}
	
}
