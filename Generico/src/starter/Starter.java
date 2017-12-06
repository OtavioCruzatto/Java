package starter;

import beagleBone.BlinkLed;

public class Starter {

	public static void main(String[] args) {

		BlinkLed blinkLed = new BlinkLed();

		while(true) {
			blinkLed.blinkLedPort1_2();
			blinkLed.blinkLedPort1_3();
		}

	}

}
