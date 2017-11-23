package br.com.argentum.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.argentum.modelo.Negociacao;

public class GeradorAleatorioDeXML {

	private static Logger log = Logger.getGlobal();

	public static void main(String[] args) throws IOException {

		try (PrintStream out = new PrintStream(new File("negociacao.xml"))) {

			Calendar data = Calendar.getInstance();
			Random random = new Random(123);

			List<Negociacao> negociacoes = new ArrayList<>();

			double valor = 40;
			int quantidade = 1000;

			for (int dias = 0; dias < 30; dias++) {

				int quantidadeNegociacaoDoDia = random.nextInt(20);

				for (int negocicao = 0; negocicao < quantidadeNegociacaoDoDia; negocicao++) {

					// no máximo sobe ou cai R$1,00 e nao baixa além de R$5,00
					valor += (random.nextInt(200) - 100) / 100.0;
					if (valor < 5.0) {
						valor = 5.0;
					}

					// quantidade: entre 500 e 1500
					quantidade += 1000 - random.nextInt(500);

					Negociacao n = new Negociacao(valor, quantidade, data);
					negociacoes.add(n);
				}
				data = (Calendar) data.clone();
				data.add(Calendar.DAY_OF_YEAR, 1);
			}

			XStream stream = new XStream(new DomDriver());
			stream.alias("negociacao", Negociacao.class);
			stream.setMode(XStream.NO_REFERENCES);

			negociacoes.sort((p1, p2) -> p1.getData().compareTo(p2.getData()));

			out.println(stream.toXML(negociacoes));

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

}
