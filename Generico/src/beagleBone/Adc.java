package beagleBone;

public class Adc {
	String pino = "";

	public Adc(ADC_PIN pino) {
		this.pino = pino.getAdc();
	}

	public String readValue(){
		String valor_lido = "";
		valor_lido = HandleFile.read("/sys/bus/iio/devices/iio:device0/in_voltage" + pino + "_raw", 1);
		return valor_lido;
	}

}
