package br.com.argentum.modelo;

import java.util.Calendar;

public final class Candlestick {

	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {

		if (maximo < 0) {
			throw new IllegalArgumentException("Preço de máximo não pode ser negativo");
		}
		if (maximo < minimo) {
			throw new IllegalArgumentException("Preço maximo não pode ser menor que o preço minimo");
		}
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		if (abertura < 0) {
			throw new IllegalArgumentException("Preço de abertura não pode ser negativo");
		}
		if (fechamento < 0) {
			throw new IllegalArgumentException("Preço de fechamento não pode ser negativo");
		}
		if (minimo < 0) {
			throw new IllegalArgumentException("Preço de minimo não pode ser negativo");
		}

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}

	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Candlestick [abertura= " + abertura + ", fechamento= " + fechamento + ", minimo= " + minimo
				+ ", maximo= " + maximo + ", volume= " + volume + ", data= " + data + "]";
	}

}
