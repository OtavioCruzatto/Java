package beagleBone;

public class ControlADC {

	public static String ADC_read(ADC_PIN adc){
		String valor_lido = "";
		valor_lido = HandleFile.read("/sys/bus/iio/devices/iio:device0/in_voltage" + adc.getAdc() + "_raw");
		return valor_lido;
	}

}
