package starter;

import beagleBone.USERLED_STATE;
import beagleBone.USERLED_USR;
import beagleBone.UserLeds;

public class Starter {

	public static void main(String[] args) {

		UserLeds led_0 = new UserLeds(USERLED_USR.USR_0);
		UserLeds led_1 = new UserLeds(USERLED_USR.USR_1);
		UserLeds led_2 = new UserLeds(USERLED_USR.USR_2);
		UserLeds led_3 = new UserLeds(USERLED_USR.USR_3);

		led_0.init();
		led_1.init();
		led_2.init();
		led_3.init();

		while(true) {

			try {

				led_0.setState(USERLED_STATE.ON);
				led_1.setState(USERLED_STATE.OFF);
				led_2.setState(USERLED_STATE.OFF);
				led_3.setState(USERLED_STATE.OFF);
				Thread.sleep(100);

				led_0.setState(USERLED_STATE.OFF);
				led_1.setState(USERLED_STATE.ON);
				led_2.setState(USERLED_STATE.OFF);
				led_3.setState(USERLED_STATE.OFF);
				Thread.sleep(100);

				led_0.setState(USERLED_STATE.OFF);
				led_1.setState(USERLED_STATE.OFF);
				led_2.setState(USERLED_STATE.ON);
				led_3.setState(USERLED_STATE.OFF);
				Thread.sleep(100);

				led_0.setState(USERLED_STATE.OFF);
				led_1.setState(USERLED_STATE.OFF);
				led_2.setState(USERLED_STATE.OFF);
				led_3.setState(USERLED_STATE.ON);
				Thread.sleep(100);

				led_0.setState(USERLED_STATE.OFF);
				led_1.setState(USERLED_STATE.OFF);
				led_2.setState(USERLED_STATE.OFF);
				led_3.setState(USERLED_STATE.OFF);
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
