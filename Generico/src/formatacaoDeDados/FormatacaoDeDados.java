package formatacaoDeDados;

public class FormatacaoDeDados {

	/**
	 * M�todo para a formata��o de Strings para o padr�o de arquivo CSV brasileiro, com cada dado separado por ";".
	 * Obs: Podem ser passados como par�metro infinitas Strings para o m�todo (foi utilizado o varargs).
	 * @param dadosASeremEscritosNaMesmaLinha
	 * @return
	 * Retorna uma String com os dados passados como par�metro na mesma ordem que estes foram passados, por�m j� formatado
	 * para ser escrito em um arquivo do tipo CSV.
	 * <br/>Obs: ao final de cada String gerada � adicionado um final de linha "\n" automaticamente.
	 * <br/><br/>Exemplo:
	 * <br/>Chamada do m�todo: FormatacaoDeDados.csv("EIXO X", "EIXO Y");
	 * <br/>Retorno: EIXO X;EIXO Y; (com o enter no final).
	 * @author Otavio Cruzatto
	 *
	 */
	public static String csv(String... dadosASeremEscritosNaMesmaLinha) {
		String textoFormatado = "";

		int quantidadeDeDados = dadosASeremEscritosNaMesmaLinha.length;

		for(int i = 0; i < quantidadeDeDados; i++) {
			textoFormatado = textoFormatado + dadosASeremEscritosNaMesmaLinha[i] + ";";
			if(i == (quantidadeDeDados - 1)) {
				textoFormatado = textoFormatado + "\n";
			}
		}

		return textoFormatado;
	}

}
