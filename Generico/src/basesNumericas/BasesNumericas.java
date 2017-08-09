package basesNumericas;

public class BasesNumericas {

	public static String converterDecimalParaBinario(String valorEmDecimal) {
		String valorConvertidoParaBinario = "";
		valorConvertidoParaBinario = Long.toBinaryString(Long.parseLong(valorEmDecimal));
		return valorConvertidoParaBinario;
	}

	public static String converterBinarioParaDecimal(String valorEmBinario) {
		String valorConvertidoParaDecimal = "";
		valorConvertidoParaDecimal = String.valueOf(Long.parseLong(valorEmBinario, 2));
		return valorConvertidoParaDecimal;
	}

	public static String converterDecimalParaHexadecimal(String valorEmDecimal) {
		String valorConvertidoParaHexadecimal = "";
		valorConvertidoParaHexadecimal = Long.toHexString(Long.valueOf(valorEmDecimal));
		return valorConvertidoParaHexadecimal;
	}

	public static String converterHexadecimalParaDecimal(String valorEmHexadecimal) {
		String valorConvertidoParaDecimal = "";
		valorConvertidoParaDecimal = String.valueOf(Long.parseLong(valorEmHexadecimal, 16));
		return valorConvertidoParaDecimal;
	}
	
	public static String converterBinarioParaHexadecimal(String valorEmBinario) {
		String valorConvertidoParaHexadecimal = "";
		valorConvertidoParaHexadecimal = converterDecimalParaHexadecimal(converterBinarioParaDecimal(valorEmBinario));
		return valorConvertidoParaHexadecimal;
	}
	
	public static String converterHexadecimalParaBinario(String valorEmHexadecimal) {
		String valorConvertidoParaBinario = "";
		valorConvertidoParaBinario = converterDecimalParaBinario(converterHexadecimalParaDecimal(valorEmHexadecimal));
		return valorConvertidoParaBinario;
	}


}
