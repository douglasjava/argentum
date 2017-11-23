package br.com.argentum.reader;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.argentum.modelo.Negociacao;

public class LeitorXML {

	@SuppressWarnings("unchecked")
	public List<Negociacao> carrega(Reader fonte) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		return (List<Negociacao>) stream.fromXML(fonte);
	}

}
