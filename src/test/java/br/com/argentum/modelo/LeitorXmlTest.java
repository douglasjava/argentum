package br.com.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import br.com.argentum.reader.LeitorXML;

public class LeitorXmlTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" +
		          "  <negociacao>" +
		          "    <preco>43.5</preco>" +
		          "    <quantidade>1000</quantidade>" +
		          "    <data>" +
		          "      <time>1322233344455</time>" +
		          "    </data>" +
		          "  </negociacao>" +
		          "</list>";

		LeitorXML leitor = new LeitorXML();
		List<Negociacao> negociacoes = leitor.carrega(new StringReader(xmlDeTeste));

		assertEquals(1, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		
	}

}
