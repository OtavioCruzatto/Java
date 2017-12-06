package beagleBone;

/**
 * Esta enum foi criada com o objetivo de facilitar o acesso aos pinos de GPIO da BeagleBone.
 * Aqui estão mapeados todos os pinos que estão externalizados nas barras de pino P8 e P9.
 * Quando um determinado atributo é chamado (GPIO66 por exemplo) o enum irá retornar um inteiro com o número do pino referente a aquele GPIO (neste caso 66)
 *
 * @author Otavio
 *
 */
public enum GPIO_PIN {
	GPIO66("66"), GPIO67("67"), GPIO69("69"), GPIO68("68"), GPIO45("45"), GPIO44("44"), GPIO23("23"), GPIO26("26"), GPIO47("47"), GPIO46(
			"46"), GPIO27("27"), GPIO65("65"), GPIO22("22"), GPIO61("61"), GPIO30("30"), GPIO60("60"), GPIO31("31"), GPIO40("40"), GPIO48(
					"48"), GPIO51("51"), GPIO4("4"), GPIO5("5"), GPIO3("3"), GPIO2("2"), GPIO49("49"), GPIO15("15"), GPIO117("117"), GPIO14(
							"14"), GPIO125("125"), GPIO122("122"), GPIO20("20"), GPIO7("7");

	private String pin;

	private GPIO_PIN(String pin) {
		this.pin = pin;
	}

	public String getPin() {
		return this.pin;
	}


}
