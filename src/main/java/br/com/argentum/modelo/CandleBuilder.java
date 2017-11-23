package br.com.argentum.modelo;

import java.util.Calendar;

public class CandleBuilder {

	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;

	private boolean isAbertura;
	private boolean isFechamento;
	private boolean isMinimo;
	private boolean isMaximo;
	private boolean isVolume;
	private boolean isData;
	
	public CandleBuilder comAbertura(double abertura) {
		this.abertura = abertura;
		isAbertura = Boolean.TRUE;
		return this;
	}

	public CandleBuilder comFechamento(double fechamento) {
		this.fechamento = fechamento;
		isFechamento = Boolean.TRUE;
		return this;
	}

	public CandleBuilder comMinimo(double minimo) {
		this.minimo = minimo;
		isMinimo = Boolean.TRUE;
		return this;
	}

	public CandleBuilder comMaximo(double maximo) {
		this.maximo = maximo;
		isMaximo = Boolean.TRUE;
		return this;
	}

	public CandleBuilder comVolume(double volume) {
		this.volume = volume;
		isVolume = Boolean.TRUE;
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		this.data = data;
		isData = Boolean.TRUE;
		return this;
	}

	public Candlestick geraCandle() {

		if (!isAbertura || !isFechamento || !isMinimo || !isMaximo || !isVolume || !isData) {
			throw new IllegalStateException("Método geraCandle foi chamado antes dos outros");
		}
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}

}
