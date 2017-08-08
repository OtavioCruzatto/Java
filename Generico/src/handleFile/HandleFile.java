package handleFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HandleFile {
	File arquivo;
	String enderecoDoArquivo = "";
	FileWriter escritorDeArquivo;
	BufferedWriter bufferDoEscritorDeArquivo;
	FileReader leitorDeArquivo;
	BufferedReader bufferDoLeitorDeArquivo;
	boolean arquivoExiste = false;
	boolean arquivoCriado = false;
	boolean arquivoDeletado = false;


	/**
	 * Construtor da classe HandleFile.
	 * <br/><br/>
	 *
	 * Seta o endereço recebido como parâmetro para o atributo enderecoDoArquivo, instancia um objeto do tipo File com o
	 * endereco recebido e testa se o arquivo existe.
	 *
	 * @param enderecoDoArquivo
	 * <br/>Recebe como parâmetro uma String com o endereço do local em que o arquivo que será manipulado está.
	 *
	 * @author Otavio Cruzatto
	 */
	public HandleFile(String enderecoDoArquivo) {
		this.setEnderecoDoArquivo(enderecoDoArquivo);
		this.setArquivo(new File(getEnderecoDoArquivo()));
		this.setArquivoExiste(this.fileExists());
	}

	/**
	 * Método que escreve a string recebida como parâmetro, no arquivo do objeto instanciado.
	 * @param informacaoParaSerEscrita
	 * @return
	 * Retorna true se a escrita foi realizada, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean writeInFile(String informacaoParaSerEscrita) {
		boolean escritaRealizada = false;

		try {
			this.setEscritorDeArquivo(new FileWriter(this.getEnderecoDoArquivo(), true));
			this.setBufferDoEscritorDeArquivo(new BufferedWriter(this.getEscritorDeArquivo()));
			this.getBufferDoEscritorDeArquivo().write(informacaoParaSerEscrita);
			this.getBufferDoEscritorDeArquivo().close();
			this.getEscritorDeArquivo().close();

			if(this.readAllFromFile().endsWith(informacaoParaSerEscrita)) {
				escritaRealizada = true;
			}
			else {
				escritaRealizada = false;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return escritaRealizada;
	}

	/**
	 * Método que apaga o conteúdo do arquivo do objeto instanciado.
	 * @return
	 * Retorna true se o conteúdo do arquivo foi apagado, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean eraseFile() {

		try {
			this.setEscritorDeArquivo(new FileWriter(this.getEnderecoDoArquivo()));
			this.setBufferDoEscritorDeArquivo(new BufferedWriter(this.getEscritorDeArquivo()));
			this.getBufferDoEscritorDeArquivo().write("");
			this.getBufferDoEscritorDeArquivo().close();
			this.getEscritorDeArquivo().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return this.readAllFromFile().isEmpty();
	}

	/**
	 * Método que lê todo o conteúdo do arquivo do objeto instanciado.
	 * @return
	 * Retorna uma String com todo o conteúdo do arquivo.
	 * @author Otavio Cruzatto
	 */
	public String readAllFromFile() {
		String conteudoLidoDoArquivo = "";

		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.getBufferDoLeitorDeArquivo().readLine() + "\n";
			}
			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoLidoDoArquivo;
	}

	/**
	 * Método que lê do arquivo o conteúdo da linha passada como parâmetro.
	 * @return
	 * Retorna uma String com o conteúdo da linha desejada.
	 * @author Otavio Cruzatto
	 */
	public String readLineFromFile(int linhaAserLida) {
		String conteudoDaLinhaDesejada = "";
		String conteudoLidoDoArquivo = "";
		int contador = 0;
		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				contador++;
				conteudoLidoDoArquivo = this.getBufferDoLeitorDeArquivo().readLine();
				if(contador == linhaAserLida) {
					conteudoDaLinhaDesejada = conteudoLidoDoArquivo;
				}
			}
			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoDaLinhaDesejada;
	}

	/**
	 * Método que lê do arquivo o trecho entre as linhas passadas como parâmetro, incluindo a primeira e a última linha.
	 * @return
	 * Retorna uma String com o conteúdo do trecho desejado.
	 * @author Otavio Cruzatto
	 */
	public String readBetween(int primeiraLinhaASerLida, int ultimaLinhaASerLida) {
		String trechoDoArquivoDesejado = "";
		String conteudoLidoDoArquivo = "";
		int contador = 0;

		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				contador++;
				conteudoLidoDoArquivo = this.getBufferDoLeitorDeArquivo().readLine();
				if((contador >= primeiraLinhaASerLida) && (contador <= ultimaLinhaASerLida)) {
					trechoDoArquivoDesejado = trechoDoArquivoDesejado + conteudoLidoDoArquivo + "\n";
				}
			}
			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return trechoDoArquivoDesejado;
	}

	/**
	 * Método que cria um arquivo com o nome armazenado no atributo String enderecoDoArquivo, se ele ainda não existir.
	 * <br/><br/>
	 *
	 * @return
	 * Retorna true se o arquivo não existia e foi criado, retorna false caso contrário.
	 * <br/>Caso o arquivo já exista retorna false, porém o atributo boolean arquivoExiste será true.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean createFile() {

		try {
			if(!this.fileExists()) {
				this.getArquivo().createNewFile();
				if(this.fileExists()) {
					this.setArquivoCriado(true);
				}
				else {
					this.setArquivoCriado(false);
				}
			}
			else {
				this.setArquivoCriado(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.isArquivoCriado();
	}

	/**
	 * Método que deleta o arquivo com o nome armazenado no atributo String enderecoDoArquivo, se ele existir.
	 * <br/><br/>
	 *
	 * @return
	 * Retorna true se o arquivo existia e foi deletado, retorna false caso contrário.
	 * <br/>Caso o arquivo não exista retorna false e o atributo boolean arquivoExiste será false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean deleteFile() {

		if(this.fileExists()) {
			this.getArquivo().delete();
			if(!this.fileExists()) {
				this.setArquivoDeletado(true);
			}
			else {
				this.setArquivoDeletado(false);
			}
		}
		else {
			this.setArquivoDeletado(false);
		}

		return this.isArquivoDeletado();

	}

	/**
	 * Método que testa se o arquivo com o nome armazenado no atributo String enderecoDoArquivo existe.
	 * @return
	 * Retorna true se o arquivo existir, caso contrário retorna false.
	 * @author Otavio Cruzatto
	 */
	public boolean fileExists() {
		this.setArquivoExiste(getArquivo().exists());
		return this.isArquivoExiste();
	}

	/**
	 * Método que exibe no console as informações básicas sobre o objeto criado.
	 * @author Otavio Cruzatto
	 */
	public void printStatus() {
		System.out.println("O arquivo existe: " + this.isArquivoExiste());
		System.out.println("O arquivo foi criado: " + this.isArquivoCriado());
		System.out.println("O arquivo foi deletado: " + this.isArquivoDeletado());
		System.out.println("Endereço do arquivo: " + this.getEnderecoDoArquivo());
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public String getEnderecoDoArquivo() {
		return enderecoDoArquivo;
	}

	public void setEnderecoDoArquivo(String enderecoDoArquivo) {
		this.enderecoDoArquivo = enderecoDoArquivo;
	}

	public boolean isArquivoExiste() {
		return arquivoExiste;
	}

	public void setArquivoExiste(boolean arquivoExiste) {
		this.arquivoExiste = arquivoExiste;
	}

	public boolean isArquivoCriado() {
		return arquivoCriado;
	}

	public void setArquivoCriado(boolean arquivoCriado) {
		this.arquivoCriado = arquivoCriado;
	}

	public boolean isArquivoDeletado() {
		return arquivoDeletado;
	}

	public void setArquivoDeletado(boolean arquivoDeletado) {
		this.arquivoDeletado = arquivoDeletado;
	}

	public FileWriter getEscritorDeArquivo() {
		return escritorDeArquivo;
	}

	public void setEscritorDeArquivo(FileWriter escritorDeArquivo) {
		this.escritorDeArquivo = escritorDeArquivo;
	}

	public BufferedWriter getBufferDoEscritorDeArquivo() {
		return bufferDoEscritorDeArquivo;
	}

	public void setBufferDoEscritorDeArquivo(BufferedWriter bufferDoEscritorDeArquivo) {
		this.bufferDoEscritorDeArquivo = bufferDoEscritorDeArquivo;
	}

	public FileReader getLeitorDeArquivo() {
		return leitorDeArquivo;
	}

	public void setLeitorDeArquivo(FileReader leitorDeArquivo) {
		this.leitorDeArquivo = leitorDeArquivo;
	}

	public BufferedReader getBufferDoLeitorDeArquivo() {
		return bufferDoLeitorDeArquivo;
	}

	public void setBufferDoLeitorDeArquivo(BufferedReader bufferDoLeitorDeArquivo) {
		this.bufferDoLeitorDeArquivo = bufferDoLeitorDeArquivo;
	}

}
