package starter;

import manipularArquivo.ManipularArquivo;

public class Starter {

	public static void main(String[] args) {
		String endereco_1 = "C:\\Users\\otavio\\Desktop\\ArquivoTeste.txt";

		ManipularArquivo arquivo_1 = new ManipularArquivo(endereco_1);

		arquivo_1.substituirConteudoDaLinha(7, "KKKKKKKKKKKKK");

	}

}
