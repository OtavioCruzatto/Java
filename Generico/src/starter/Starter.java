package starter;

import handleFile.HandleFile;

public class Starter {

	public static void main(String[] args) {
//		String endereco_1 = "C:\\Users\\Otavio\\Desktop\\teste_1.txt";
		String endereco_2 = "C:\\Users\\otavio\\Desktop\\teste.txt";

		HandleFile arquivo_1 = new HandleFile(endereco_2);

		System.out.println(arquivo_1.readBetween(20, 20));


	}

}
