package beagleBone;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class HandleFile {

	/**
	 * M�todo utilizado para escrever em um arquivo. Path � o endere�o do arquivo e data � o texto a ser escrito no arquivo
	 * Neste m�todo, toda vez que chegar um texto a ser escrito no arquivo, o conte�do antigo ser� apagado.
	 *
	 * @param path
	 * @param data
	 * @author Otavio
	 */
	public static void write(String path, String data) {

		try {												// Utiliza um try/catch, por causa das poss�veis excess�es gerada pelos objetos instanciados

			FileWriter fw = new FileWriter(path);			// Cria um objeto do tipo FileWriter e passa para ele o endere�o do arquivo
			BufferedWriter bw = new BufferedWriter(fw);		// Cria um objeto do tipo BufferedWriter e passa para ele o objeto do tipo FileWriter

			bw.write(data);									// Escreve o conte�do de data no arquivo

			bw.close();										// Fecha o objeto bw
			fw.close();										// Fecha o objeto fw

		}
		catch (Exception e) {
			e.printStackTrace();							// Caso ocorra uma excess�o, ser� printado dados sobre a excess�o
		}

	}


	/**
	 * M�todo utilizado para ler o conte�do de um arquivo. Path � o endere�o do arquivo.
	 * Este m�todo apenas l� a primeira linha do arquivo, e retorna seu valor.
	 * Se o arquivo estiver vazio, retorna null.
	 *
	 * @param path
	 * @return
	 * @author Otavio
	 */
	public static String read(String path) {
		String line = "";									// Declara uma String para receber a leitura do arquivo

		try {												// Utiliza um try/catch, por causa das poss�veis excess�es gerada pelos objetos instanciados

			FileReader fr = new FileReader(path);			// Cria um objeto do tipo FileReader e passa para ele o endere�o do arquivo
			BufferedReader br = new BufferedReader(fr);		// Cria um objeto do tipo BufferedReader e passa para ele o objeto do tipo FileReader

			line = br.readLine();							// L� a primeira linha do arquivo e � atribui a vari�vel line

			br.close();										// Fecha o objeto br
			fr.close();										// Fecha o objeto fr
		}
		catch (Exception e) {
			e.printStackTrace();							// Caso ocorra uma excess�o, ser� printado dados sobre a excess�o
		}

		return line;										// Retorna o conte�do da primeira linha do arquivo
	}


}
