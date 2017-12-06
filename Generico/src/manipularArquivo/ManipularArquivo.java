package manipularArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipularArquivo {
	private File arquivo;
	private String enderecoDoArquivo = "";
	private FileWriter escritorDeArquivo;
	private BufferedWriter bufferDoEscritorDeArquivo;
	private FileReader leitorDeArquivo;
	private BufferedReader bufferDoLeitorDeArquivo;
	private boolean arquivoExiste = false;
	private boolean arquivoCriado = false;
	private boolean arquivoDeletado = false;
	private int quantidadeDeLinhasNoArquivo = 0;

	/**
	 * Construtor da classe ManipularArquivo.
	 * <br/><br/>
	 *
	 * Seta o endereço recebido como parâmetro para o atributo enderecoDoArquivo, instancia um objeto do tipo File com o
	 * endereco recebido e testa se o arquivo existe.
	 *
	 * @param enderecoDoArquivo
	 * <br/>Recebe como parâmetro uma String com o endereço do local em que o arquivo que será manipulado está ou será criado.
	 *
	 * @author Otavio Cruzatto
	 */
	public ManipularArquivo(String enderecoDoArquivo) {
		this.enderecoDoArquivo = enderecoDoArquivo;
		this.arquivo = new File(enderecoDoArquivo);
		this.arquivoExiste = this.arquivoExiste();

		if(this.arquivoExiste) {
			this.quantidadeDeLinhasNoArquivo = this.quantidadeDeLinhasNoArquivo();
		}
	}

	/**
	 * Construtor da classe ManipularArquivo.
	 * <br/><br/>
	 *
	 * Seta o File recebido como o arquivo do objeto, instancia um objeto do tipo File com o
	 * arquivo recebido e testa se o arquivo existe.
	 *
	 * @param arquivo
	 * <br/>Recebe como parâmetro um File em que o arquivo que será manipulado está ou será criado.
	 *
	 * @author Otavio Cruzatto
	 */
	public ManipularArquivo(File arquivo) {
		this.arquivo = arquivo;
		this.enderecoDoArquivo = this.arquivo.getAbsolutePath();
		this.arquivoExiste = this.arquivoExiste();

		if(this.arquivoExiste) {
			this.quantidadeDeLinhasNoArquivo = this.quantidadeDeLinhasNoArquivo();
		}
	}

	/**
	 * Método que escreve a string recebida como parâmetro, no arquivo do objeto instanciado.
	 * <br/>Obs: Se o arquivo já conter informações, a string recebida é escrita no final do arquivo.
	 * @param informacaoParaSerEscrita
	 * @return
	 * Retorna true se a escrita foi realizada, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean escreverNoArquivo(String informacaoParaSerEscrita) {
		boolean escritaRealizada = false;

		try {
			this.escritorDeArquivo = new FileWriter(this.arquivo, true);
			this.bufferDoEscritorDeArquivo = new BufferedWriter(this.escritorDeArquivo);
			this.bufferDoEscritorDeArquivo.write(informacaoParaSerEscrita);
			this.bufferDoEscritorDeArquivo.flush();
			this.bufferDoEscritorDeArquivo.close();
			this.escritorDeArquivo.close();
			this.quantidadeDeLinhasNoArquivo = this.quantidadeDeLinhasNoArquivo();

			if(this.lerTodoArquivo().endsWith(informacaoParaSerEscrita)) {
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
	 * Método que substitui o conteúdo da linha passada como parâmetro, pela String passada como parâmetro
	 * no arquivo do objeto instanciado, se a linha existir.
	 * @return
	 * Retorna true se o conteúdo da linha do arquivo foi substituido, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean substituirConteudoDaLinha(int linhaASerSubstituida, String informacaoParaSerEscrita) {

		boolean substituicaoDoConteudoRealizada = false;

		if(this.quantidadeDeLinhasNoArquivo >= linhaASerSubstituida) {
			String linhaASerExcluida = this.lerConteudoDaLinha(linhaASerSubstituida);
			String conteudoCompletoDoArquivo = this.lerTodoArquivo();
			String novoConteudo = conteudoCompletoDoArquivo.replace(linhaASerExcluida, informacaoParaSerEscrita);

			this.apagarTodoConteudoDoArquivo();
			substituicaoDoConteudoRealizada = this.escreverNoArquivo(novoConteudo);
		}

		return substituicaoDoConteudoRealizada;
	}

	/**
	 * Método que apaga o conteúdo do arquivo do objeto instanciado.
	 * @return
	 * Retorna true se o conteúdo do arquivo foi apagado, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean apagarTodoConteudoDoArquivo() {

		try {
			this.escritorDeArquivo = new FileWriter(this.arquivo);
			this.bufferDoEscritorDeArquivo = new BufferedWriter(this.escritorDeArquivo);
			this.escritorDeArquivo.write("");
			this.escritorDeArquivo.flush();
			this.bufferDoEscritorDeArquivo.close();
			this.escritorDeArquivo.close();
			this.quantidadeDeLinhasNoArquivo = this.quantidadeDeLinhasNoArquivo();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return this.lerTodoArquivo().isEmpty();
	}

	/**
	 * Método que apaga o conteúdo da linha passada como parâmetro do arquivo do objeto instanciado, se a linha existir.
	 * @return
	 * Retorna true se o conteúdo da linha do arquivo foi apagado, caso contrário retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean apagarConteudoDaLinha(int linhaASerApagada) {

		boolean alteracaoDosDados = false;

		if(this.quantidadeDeLinhasNoArquivo >= linhaASerApagada) {
			String linhaASerExcluida = this.lerConteudoDaLinha(linhaASerApagada);
			String conteudoCompletoDoArquivo = this.lerTodoArquivo();
			String novoConteudo = conteudoCompletoDoArquivo.substring(0, conteudoCompletoDoArquivo.indexOf(linhaASerExcluida)) + conteudoCompletoDoArquivo.substring(conteudoCompletoDoArquivo.indexOf(linhaASerExcluida) + linhaASerExcluida.length() + 1);

			this.apagarTodoConteudoDoArquivo();
			alteracaoDosDados = this.escreverNoArquivo(novoConteudo);
		}

		return alteracaoDosDados;
	}

	/**
	 * Método que lê todo o conteúdo do arquivo do objeto instanciado.
	 * @return
	 * Retorna uma String com todo o conteúdo do arquivo.
	 * @author Otavio Cruzatto
	 */
	public String lerTodoArquivo() {
		String conteudoLidoDoArquivo = "";

		try {
			this.leitorDeArquivo = new FileReader(this.arquivo);
			this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);
			while(this.bufferDoLeitorDeArquivo.ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.bufferDoLeitorDeArquivo.readLine() + "\n";
			}
			this.leitorDeArquivo.close();
			this.bufferDoLeitorDeArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoLidoDoArquivo;
	}


	/**
	 * Método que lê o conteúdo do arquivo entre as Strings passadas por parâmetro.
	 *
	 * @return
	 * Retorna uma String com o conteúdo que está entre as Strings passadas como parâmetro.
	 * <br/><br/>
	 * Exemplo: Texto no arquivo: Pk-Pk(1), Cur 994mV Frequency(1), Cur 850.0Hz
	 *          <br/>Chamada do método: lerConteudoEntreAsStrings("Cur", "Frequency(1)");
	 *          <br/>Retorno: Cur 994mV
	 * @author Otavio Cruzatto
	 *
	 */
	public String lerConteudoEntreAsStrings(String primeiraString, String segundaString) {
		String conteudoLidoDoArquivo = "";
		String conteudoFiltradoDoArquivo = "";

		try {
			this.leitorDeArquivo = new FileReader(this.arquivo);
			this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);

			while(this.bufferDoLeitorDeArquivo.ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.bufferDoLeitorDeArquivo.readLine() + "\n";
			}

			if(conteudoLidoDoArquivo.contains(primeiraString) && conteudoLidoDoArquivo.contains(segundaString)) {
				conteudoFiltradoDoArquivo = conteudoLidoDoArquivo.substring(conteudoLidoDoArquivo.indexOf(primeiraString), conteudoLidoDoArquivo.indexOf(segundaString));
			}

			this.leitorDeArquivo.close();
			this.bufferDoLeitorDeArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoFiltradoDoArquivo;
	}

	/**
	 * Método que lê o conteúdo do arquivo entre as posições passadas por parâmetro.
	 *
	 * @return
	 * Retorna uma String com o conteúdo que está entre as posições passadas como parâmetro.
	 * <br/><br/>
	 * Exemplo: Texto no arquivo: 1234567890
	 *          <br/>Chamada do método: lerConteudoEntreAsPosicoes(5, 9);
	 *          <br/>Retorno: 6789
	 * @author Otavio Cruzatto
	 *
	 */
	public String lerConteudoEntreAsPosicoes(int primeiraPosicao, int segundaPosicao) {
		String conteudoLidoDoArquivo = "";
		String conteudoFiltradoDoArquivo = "";

		try {
			this.leitorDeArquivo = new FileReader(this.arquivo);
			this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);

			while(this.bufferDoLeitorDeArquivo.ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.bufferDoLeitorDeArquivo.readLine() + "\n";
			}

			if((conteudoLidoDoArquivo.length() - 1) >= segundaPosicao) {
				conteudoFiltradoDoArquivo = conteudoLidoDoArquivo.substring(primeiraPosicao, segundaPosicao);
			}

			this.leitorDeArquivo.close();
			this.bufferDoLeitorDeArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoFiltradoDoArquivo;
	}

	/**
	 * Método que lê do arquivo o conteúdo da linha passada como parâmetro.
	 * @return
	 * Retorna uma String com o conteúdo da linha desejada.
	 * @author Otavio Cruzatto
	 */
	public String lerConteudoDaLinha(int linhaAserLida) {
		String conteudoDaLinhaDesejada = "";
		String conteudoLidoDoArquivo = "";
		int contador = 0;

		if(linhaAserLida <= this.quantidadeDeLinhasNoArquivo) {
			try {
				this.leitorDeArquivo = new FileReader(this.arquivo);
				this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);

				while(this.bufferDoLeitorDeArquivo.ready()) {
					contador++;
					conteudoLidoDoArquivo = this.bufferDoLeitorDeArquivo.readLine();
					if(contador == linhaAserLida) {
						conteudoDaLinhaDesejada = conteudoLidoDoArquivo;
					}
				}

				this.leitorDeArquivo.close();
				this.bufferDoLeitorDeArquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return conteudoDaLinhaDesejada;
	}

	/**
	 * Método que lê do arquivo o trecho entre as linhas passadas como parâmetro, incluindo a primeira e a última linha.
	 * @return
	 * Retorna uma String com o conteúdo do trecho desejado.
	 * @author Otavio Cruzatto
	 */
	public String lerConteudoEntreAsLinhas(int primeiraLinhaASerLida, int ultimaLinhaASerLida) {
		String trechoDoArquivoDesejado = "";
		String conteudoLidoDoArquivo = "";
		int contador = 0;

		try {
			this.leitorDeArquivo = new FileReader(this.arquivo);
			this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);

			while(this.bufferDoLeitorDeArquivo.ready()) {
				contador++;
				conteudoLidoDoArquivo = this.bufferDoLeitorDeArquivo.readLine();
				if((contador >= primeiraLinhaASerLida) && (contador <= ultimaLinhaASerLida)) {
					trechoDoArquivoDesejado = trechoDoArquivoDesejado + conteudoLidoDoArquivo + "\n";
				}
			}

			this.leitorDeArquivo.close();
			this.bufferDoLeitorDeArquivo.close();
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
	public boolean criarArquivo() {

		try {
			if(!this.arquivoExiste()) {
				this.arquivo.createNewFile();
				if(this.arquivoExiste()) {
					this.arquivoCriado = true;
				}
				else {
					this.arquivoCriado = false;
				}
			}
			else {
				this.arquivoCriado = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.arquivoCriado;
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
	public boolean deletarArquivo() {

		if(this.arquivoExiste()) {
			this.arquivo.delete();
			if(!this.arquivoExiste()) {
				this.arquivoDeletado = true;
			}
			else {
				this.arquivoDeletado = false;
			}
		}
		else {
			this.arquivoDeletado = false;
		}

		return this.arquivoDeletado;

	}

	/**
	 * Método que testa se o arquivo com o nome armazenado no atributo String enderecoDoArquivo existe.
	 * @return
	 * Retorna true se o arquivo existir, caso contrário retorna false.
	 * @author Otavio Cruzatto
	 */
	public boolean arquivoExiste() {
		this.arquivoExiste = arquivo.exists();
		return this.arquivoExiste;
	}

	/**
	 * Método que exibe no console as informações básicas sobre o objeto criado.
	 * @author Otavio Cruzatto
	 */
	public void exibirInformacoes() {
		System.out.println("O arquivo existe: " + this.arquivoExiste);
		System.out.println("O arquivo foi criado: " + this.arquivoCriado);
		System.out.println("O arquivo foi deletado: " + this.arquivoDeletado);
		System.out.println("Endereço do arquivo: " + this.enderecoDoArquivo);
	}

	/**
	 * Método que retorna a quantidade de linhas do arquivo instanciado.
	 * @return
	 * Quantidade de linhas.
	 * @author Otavio Cruzatto
	 */
	public int quantidadeDeLinhasNoArquivo() {
		int quantidadeDeLinhas = 0;

		try {
			this.leitorDeArquivo = new FileReader(this.arquivo);
			this.bufferDoLeitorDeArquivo = new BufferedReader(this.leitorDeArquivo);

			while(this.bufferDoLeitorDeArquivo.ready()) {
				this.bufferDoLeitorDeArquivo.readLine();
				quantidadeDeLinhas++;
			}
			this.leitorDeArquivo.close();
			this.bufferDoLeitorDeArquivo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return quantidadeDeLinhas;
	}

	public File getArquivo() {
		return arquivo;
	}

	public boolean isArquivoExiste() {
		return arquivoExiste;
	}

	public boolean isArquivoCriado() {
		return arquivoCriado;
	}

	public boolean isArquivoDeletado() {
		return arquivoDeletado;
	}

}