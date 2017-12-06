package beagleBone;

import java.io.File;

/**
 * Esta classe é utilizada como um meio para manusear individualmente os GPIOS da BeagleBone.
 * @author Otavio
 *
 */
public class Gpio {
	private String gpio = "";

	public Gpio(GPIO_PIN pin) {
		this.gpio = pin.getPin();
	}

	/**
	 * Método para definir a direção de um GPIO da BeagleBone.
	 *
	 * @param direction
	 * @author Otavio
	 */
	public void direction(GPIO_DIRECTION direction) {
		HandleFile.write("/sys/class/gpio/gpio" + gpio + "/direction", direction.getDirection());
	}

	/**
	 * Método para definir o estado de um GPIO definido como saída na BeagleBone.
	 *
	 * @param state
	 * @author Otavio
	 */
	public void output(GPIO_STATE state) {
		HandleFile.write("/sys/class/gpio/gpio" + gpio + "/value", state.getState());
	}

	/**
	 * Método para exportar um GPIO para a "Área de Trabalho" da BeagleBone.
	 *
	 * @author Otavio
	 */
	public void export() {
		try {
			File f = new File("/sys/class/gpio/gpio" + gpio);
			if( (f.exists()) == false ) {
				HandleFile.write("/sys/class/gpio/export", gpio);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para retirar um GPIO da "Área de Trabalho" da BeagleBone.
	 * @author Otavio
	 */
	public void unexport() {
		try {
			File f = new File("/sys/class/gpio/gpio" + gpio);
			if( (f.exists()) == true ) {
				HandleFile.write("/sys/class/gpio/unexport", gpio);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
