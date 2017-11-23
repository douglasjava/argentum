package br.com.argentum.modelo;

import java.util.Calendar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NegociacaoHandler {

	public static void main(String[] args) {

		Negociacao negociacao = new Negociacao(42.3, 100, Calendar.getInstance());

		XStream stream = new XStream(new DomDriver());
		System.out.println(stream.toXML(negociacao));

	}

}
