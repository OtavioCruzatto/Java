package starter;

import basesNumericas.BasesNumericas;
import formatacaoDeDados.FormatacaoDeDados;
import manipularArquivo.ManipularArquivo;

public class Starter {

	public static void main(String[] args) {
		String endereco_1 = "C:\\Users\\otavio\\Desktop\\Tavinho.csv";

		ManipularArquivo arquivo_1 = new ManipularArquivo(endereco_1);

		System.out.println(BasesNumericas.converterHexadecimalParaBinario("5"));

		

	}

}
 