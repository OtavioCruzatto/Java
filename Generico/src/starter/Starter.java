package starter;

import formatacaoDeDados.FormatacaoDeDados;
import manipularArquivo.ManipularArquivo;

public class Starter {

	public static void main(String[] args) {
		String endereco_1 = "C:\\Users\\otavio\\Desktop\\Tavinho.csv";

		ManipularArquivo arquivo_1 = new ManipularArquivo(endereco_1);

		arquivo_1.criarArquivo();
		arquivo_1.apagarTodoConteudoDoArquivo();
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("EIXO X", "EIXO Y"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("0", "1"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("1", "5"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("2", "8"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("3", "3"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("4", "0"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("5", "2"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("6", "4"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("7", "8"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("8", "4"));
		arquivo_1.escreverNoArquivo(FormatacaoDeDados.csv("9", "1"));

	}

}
