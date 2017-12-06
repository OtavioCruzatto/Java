package beagleBone;

/**
 * Esta enum foi criada com o objetivo de facilitar a utilização do PWM na BeagleBone.
 * Esta enum é utilizada para se referenciar aos pinos nas barras, (P9_14 por exemplo).
 *
 * @author Otavio
 *
 */
public enum PWM_PIN {
	P9_14("P9_14"),P9_21("P9_21"),P9_42("P9_42"),P8_13("P8_13");

	private String pwm = "";

	private PWM_PIN(String pwm) {
		this.pwm = pwm;
	}

	public String getPwm() {
		return this.pwm;
	}

}
