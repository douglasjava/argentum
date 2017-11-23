package br.com.argentum.webService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.argentum.modelo.Negociacao;
import br.com.argentum.reader.LeitorXML;

public class ClienteWebService {

	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";

	public List<Negociacao> getNegociacoes() {

		HttpURLConnection connection = null;

		try {
			URL url = new URL(URL_WEBSERVICE);
			connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			return new LeitorXML().carrega(new InputStreamReader(content));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static void main(String[] args) {
		ClienteWebService s = new ClienteWebService();
		List<Negociacao> negociacoes = s.getNegociacoes();
		for (Negociacao negociacao : negociacoes) {
			System.out.println(negociacao.getPreco());
		}

	}
}
