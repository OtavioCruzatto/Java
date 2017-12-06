package beagleBone;

/**
 * Esta enum foi criada com o objetivo de facilitar a utilização do PWM na BeagleBone.
 * Esta enum é utilizada para se referenciar para se referenciar ao atributo que está sendo utilizado do PWM (polarity, period e duty).
 *
 * @author Otavio
 *
 */
public enum PWM_CONFIG {
	POLARITY("polarity"),PERIOD("period"),DUTY("duty");

	private String pwm = "";

	private PWM_CONFIG(String pwm) {
		this.pwm = pwm;
	}

	public String getPwm() {
		return this.pwm;
	}
}
