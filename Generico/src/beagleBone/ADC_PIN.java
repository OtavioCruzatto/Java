package beagleBone;

/**
 * Esta enum foi criada com o objetivo de facilitar a utiliza��o do ADC na BeagleBone.
 * Esta enum � utilizada para se referenciar aos ADC's acess�veis na BeagleBone.
 *
 * @author Otavio
 *
 */
public enum ADC_PIN {
	ADC0("0"),ADC1("1"),ADC2("2"),ADC3("3"),ADC4("4"),ADC5("5"),ADC6("6");

	private String adc = "";

	private ADC_PIN(String adc) {
		this.adc = adc;
	}

	public String getAdc() {
		return this.adc;
	}

}
