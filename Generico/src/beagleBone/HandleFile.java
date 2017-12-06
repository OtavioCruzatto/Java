package beagleBone;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class HandleFile {

	/**
	 * Método utilizado para escrever em um arquivo. Path é o endereço do arquivo e data é o texto a ser escrito no arquivo
	 * Neste método, toda vez que chegar um texto a ser escrito no arquivo, o conteúdo antigo será apagado.
	 *
	 * @param path
	 * @param data
	 * @author Otavio
	 */
	public static void write(String path, String data) {

		try {												// Utiliza um try/catch, por causa das possíveis excessões gerada pelos objetos instanciados

			FileWriter fw = new FileWriter(path);			// Cria um objeto do tipo FileWriter e passa para ele o endereço do arquivo
			BufferedWriter bw = new BufferedWriter(fw);		// Cria um objeto do tipo BufferedWriter e passa para ele o objeto do tipo FileWriter

			bw.write(data);									// Escreve o conteúdo de data no arquivo

			bw.close();										// Fecha o objeto bw
			fw.close();										// Fecha o objeto fw

		}
		catch (Exception e) {
			e.printStackTrace();							// Caso ocorra uma excessão, será printado dados sobre a excessão
		}

	}


	/**
	 * Método utilizado para ler o conteúdo de um arquivo. Path é o endereço do arquivo.
	 * Este método apenas lê a primeira linha do arquivo, e retorna seu valor.
	 * Se o arquivo estiver vazio, retorna null.
	 *
	 * @param path
	 * @return
	 * @author Otavio
	 */
	public static String read(String path) {
		String line = "";									// Declara uma String para receber a leitura do arquivo

		try {												// Utiliza um try/catch, por causa das possíveis excessões gerada pelos objetos instanciados

			FileReader fr = new FileReader(path);			// Cria um objeto do tipo FileReader e passa para ele o endereço do arquivo
			BufferedReader br = new BufferedReader(fr);		// Cria um objeto do tipo BufferedReader e passa para ele o objeto do tipo FileReader

			line = br.readLine();							// Lê a primeira linha do arquivo e à atribui a variável line

			br.close();										// Fecha o objeto br
			fr.close();										// Fecha o objeto fr
		}
		catch (Exception e) {
			e.printStackTrace();							// Caso ocorra uma excessão, será printado dados sobre a excessão
		}

		return line;										// Retorna o conteúdo da primeira linha do arquivo
	}


}
