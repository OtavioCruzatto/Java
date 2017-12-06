package beagleBone;

public class BlinkLed {
	private Port_1 port_1;

	public BlinkLed() {
		port_1 = new Port_1();
		port_1.export();
		port_1.direction("00000000", BaseNumerica.BINARIO);
	}

	public void blinkOneLed_1() {

	}

	public void blinkLedPort1_1() {
		try {
			port_1.output("10000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00010000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00001000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00001000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00010000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void blinkLedPort1_2() {
		try {
			port_1.output("10000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11100000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11110000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111110", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00001111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000011", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void blinkLedPort1_3() {
		try {
			port_1.output("00000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000011", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00001111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111111", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111110", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11111000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11110000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11100000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("11000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("10000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00000000", BaseNumerica.BINARIO);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void blinkLedPort1_4() {
		try {
			port_1.output("10000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("10000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("10000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("10000001", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00011000", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("00100100", BaseNumerica.BINARIO);
			Thread.sleep(50);
			port_1.output("01000010", BaseNumerica.BINARIO);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
