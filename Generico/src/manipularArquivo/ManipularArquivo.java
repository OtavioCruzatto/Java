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
	 * Seta o endere�o recebido como par�metro para o atributo enderecoDoArquivo, instancia um objeto do tipo File com o
	 * endereco recebido e testa se o arquivo existe.
	 *
	 * @param enderecoDoArquivo
	 * <br/>Recebe como par�metro uma String com o endere�o do local em que o arquivo que ser� manipulado est�.
	 *
	 * @author Otavio Cruzatto
	 */
	public ManipularArquivo(String enderecoDoArquivo) {
		this.setEnderecoDoArquivo(enderecoDoArquivo);
		this.setArquivo(new File(getEnderecoDoArquivo()));
		this.setArquivoExiste(this.arquivoExiste());

		if(isArquivoExiste()) {
			setQuantidadeDeLinhasNoArquivo();
		}
	}

	/**
	 * M�todo que escreve a string recebida como par�metro, no arquivo do objeto instanciado.
	 * <br/>Obs: Se o arquivo j� conter informa��es, a string recebida � escrita no final do arquivo.
	 * @param informacaoParaSerEscrita
	 * @return
	 * Retorna true se a escrita foi realizada, caso contr�rio retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean escreverNoArquivo(String informacaoParaSerEscrita) {
		boolean escritaRealizada = false;

		try {
			this.setEscritorDeArquivo(new FileWriter(this.getEnderecoDoArquivo(), true));
			this.setBufferDoEscritorDeArquivo(new BufferedWriter(this.getEscritorDeArquivo()));
			this.getBufferDoEscritorDeArquivo().write(informacaoParaSerEscrita);
			this.getBufferDoEscritorDeArquivo().close();
			this.getEscritorDeArquivo().close();
			this.setQuantidadeDeLinhasNoArquivo();

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
	 * M�todo que substitui o conte�do da linha passada como par�metro, pela String passada como par�metro 
	 * no arquivo do objeto instanciado, se a linha existir.
	 * @return
	 * Retorna true se o conte�do da linha do arquivo foi substituido, caso contr�rio retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean substituirConteudoDaLinha(int linhaASerSubstituida, String informacaoParaSerEscrita) {

		boolean substituicaoDoConteudoRealizada = false;

		if(this.getQuantidadeDeLinhasNoArquivo() >= linhaASerSubstituida) {
			String linhaASerExcluida = this.lerConteudoDaLinha(linhaASerSubstituida);
			String conteudoCompletoDoArquivo = this.lerTodoArquivo();
			String novoConteudo = conteudoCompletoDoArquivo.replace(linhaASerExcluida, informacaoParaSerEscrita);

			this.apagarTodoConteudoDoArquivo();
			substituicaoDoConteudoRealizada = this.escreverNoArquivo(novoConteudo);
		}

		return substituicaoDoConteudoRealizada;
	}

	/**
	 * M�todo que apaga o conte�do do arquivo do objeto instanciado.
	 * @return
	 * Retorna true se o conte�do do arquivo foi apagado, caso contr�rio retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean apagarTodoConteudoDoArquivo() {

		try {
			this.setEscritorDeArquivo(new FileWriter(this.getEnderecoDoArquivo()));
			this.setBufferDoEscritorDeArquivo(new BufferedWriter(this.getEscritorDeArquivo()));
			this.getBufferDoEscritorDeArquivo().write("");
			this.getBufferDoEscritorDeArquivo().close();
			this.getEscritorDeArquivo().close();
			this.setQuantidadeDeLinhasNoArquivo();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return this.lerTodoArquivo().isEmpty();
	}

	/**
	 * M�todo que apaga o conte�do da linha passada como par�metro do arquivo do objeto instanciado, se a linha existir.
	 * @return
	 * Retorna true se o conte�do da linha do arquivo foi apagado, caso contr�rio retorna false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean apagarConteudoDaLinha(int linhaASerApagada) {

		boolean alteracaoDosDados = false;

		if(this.getQuantidadeDeLinhasNoArquivo() >= linhaASerApagada) {
			String linhaASerExcluida = this.lerConteudoDaLinha(linhaASerApagada);
			String conteudoCompletoDoArquivo = this.lerTodoArquivo();
			String novoConteudo = conteudoCompletoDoArquivo.substring(0, conteudoCompletoDoArquivo.indexOf(linhaASerExcluida)) + conteudoCompletoDoArquivo.substring(conteudoCompletoDoArquivo.indexOf(linhaASerExcluida) + linhaASerExcluida.length() + 1);

			this.apagarTodoConteudoDoArquivo();
			alteracaoDosDados = this.escreverNoArquivo(novoConteudo);
		}

		return alteracaoDosDados;
	}

	/**
	 * M�todo que l� todo o conte�do do arquivo do objeto instanciado.
	 * @return
	 * Retorna uma String com todo o conte�do do arquivo.
	 * @author Otavio Cruzatto
	 */
	public String lerTodoArquivo() {
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
	 * M�todo que l� o conte�do do arquivo entre as Strings passadas por par�metro.
	 *
	 * @return
	 * Retorna uma String com o conte�do que est� entre as Strings passadas como par�metro.
	 * <br/><br/>
	 * Exemplo: Texto no arquivo: Pk-Pk(1), Cur 994mV Frequency(1), Cur 850.0Hz
	 *          <br/>Chamada do m�todo: lerConteudoEntreAsStrings("Cur", "Frequency(1)");
	 *          <br/>Retorno: Cur 994mV
	 * @author Otavio Cruzatto
	 *
	 */
	public String lerConteudoEntreAsStrings(String primeiraString, String segundaString) {
		String conteudoLidoDoArquivo = "";
		String conteudoFiltradoDoArquivo = "";

		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.getBufferDoLeitorDeArquivo().readLine() + "\n";
			}

			if(conteudoLidoDoArquivo.contains(primeiraString) && conteudoLidoDoArquivo.contains(segundaString)) {
				conteudoFiltradoDoArquivo = conteudoLidoDoArquivo.substring(conteudoLidoDoArquivo.indexOf(primeiraString), conteudoLidoDoArquivo.indexOf(segundaString));
			}

			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoFiltradoDoArquivo;
	}

	/**
	 * M�todo que l� o conte�do do arquivo entre as posi��es passadas por par�metro.
	 *
	 * @return
	 * Retorna uma String com o conte�do que est� entre as posi��es passadas como par�metro.
	 * <br/><br/>
	 * Exemplo: Texto no arquivo: 1234567890
	 *          <br/>Chamada do m�todo: lerConteudoEntreAsPosicoes(5, 9);
	 *          <br/>Retorno: 6789
	 * @author Otavio Cruzatto
	 *
	 */
	public String lerConteudoEntreAsPosicoes(int primeiraPosicao, int segundaPosicao) {
		String conteudoLidoDoArquivo = "";
		String conteudoFiltradoDoArquivo = "";

		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				conteudoLidoDoArquivo = conteudoLidoDoArquivo + this.getBufferDoLeitorDeArquivo().readLine() + "\n";
			}

			if((conteudoLidoDoArquivo.length() - 1) >= segundaPosicao) {
				conteudoFiltradoDoArquivo = conteudoLidoDoArquivo.substring(primeiraPosicao, segundaPosicao);
			}

			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conteudoFiltradoDoArquivo;
	}

	/**
	 * M�todo que l� do arquivo o conte�do da linha passada como par�metro.
	 * @return
	 * Retorna uma String com o conte�do da linha desejada.
	 * @author Otavio Cruzatto
	 */
	public String lerConteudoDaLinha(int linhaAserLida) {
		String conteudoDaLinhaDesejada = "";
		String conteudoLidoDoArquivo = "";
		int contador = 0;

		if(linhaAserLida <= getQuantidadeDeLinhasNoArquivo()) {
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
		}


		return conteudoDaLinhaDesejada;
	}

	/**
	 * M�todo que l� do arquivo o trecho entre as linhas passadas como par�metro, incluindo a primeira e a �ltima linha.
	 * @return
	 * Retorna uma String com o conte�do do trecho desejado.
	 * @author Otavio Cruzatto
	 */
	public String lerConteudoEntreAsLinhas(int primeiraLinhaASerLida, int ultimaLinhaASerLida) {
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
	 * M�todo que cria um arquivo com o nome armazenado no atributo String enderecoDoArquivo, se ele ainda n�o existir.
	 * <br/><br/>
	 *
	 * @return
	 * Retorna true se o arquivo n�o existia e foi criado, retorna false caso contr�rio.
	 * <br/>Caso o arquivo j� exista retorna false, por�m o atributo boolean arquivoExiste ser� true.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean criarArquivo() {

		try {
			if(!this.arquivoExiste()) {
				this.getArquivo().createNewFile();
				if(this.arquivoExiste()) {
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
	 * M�todo que deleta o arquivo com o nome armazenado no atributo String enderecoDoArquivo, se ele existir.
	 * <br/><br/>
	 *
	 * @return
	 * Retorna true se o arquivo existia e foi deletado, retorna false caso contr�rio.
	 * <br/>Caso o arquivo n�o exista retorna false e o atributo boolean arquivoExiste ser� false.
	 *
	 * @author Otavio Cruzatto
	 */
	public boolean deletarArquivo() {

		if(this.arquivoExiste()) {
			this.getArquivo().delete();
			if(!this.arquivoExiste()) {
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
	 * M�todo que testa se o arquivo com o nome armazenado no atributo String enderecoDoArquivo existe.
	 * @return
	 * Retorna true se o arquivo existir, caso contr�rio retorna false.
	 * @author Otavio Cruzatto
	 */
	public boolean arquivoExiste() {
		this.setArquivoExiste(getArquivo().exists());
		return this.isArquivoExiste();
	}

	/**
	 * M�todo que exibe no console as informa��es b�sicas sobre o objeto criado.
	 * @author Otavio Cruzatto
	 */
	public void exibirInformacoes() {
		System.out.println("O arquivo existe: " + this.isArquivoExiste());
		System.out.println("O arquivo foi criado: " + this.isArquivoCriado());
		System.out.println("O arquivo foi deletado: " + this.isArquivoDeletado());
		System.out.println("Endere�o do arquivo: " + this.getEnderecoDoArquivo());
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

	public int getQuantidadeDeLinhasNoArquivo() {
		return quantidadeDeLinhasNoArquivo;
	}

	private void setQuantidadeDeLinhasNoArquivo() {
		int quantidadeDeLinhas = 0;

		try {
			this.setLeitorDeArquivo(new FileReader(this.getEnderecoDoArquivo()));
			this.setBufferDoLeitorDeArquivo(new BufferedReader(getLeitorDeArquivo()));
			while(this.getBufferDoLeitorDeArquivo().ready()) {
				this.getBufferDoLeitorDeArquivo().readLine();
				quantidadeDeLinhas++;
			}
			this.getLeitorDeArquivo().close();
			this.getBufferDoLeitorDeArquivo().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.quantidadeDeLinhasNoArquivo = quantidadeDeLinhas;
	}

}
