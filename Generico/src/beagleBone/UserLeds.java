package beagleBone;

public class UserLeds {
	private String usr = "";

	public UserLeds(USERLED_USR usr) {
		this.usr = usr.getUsr();
	}

	public void init() {
		HandleFile.write("/sys/devices/ocp.2/gpio-leds.8/leds/beaglebone:green:usr" + usr + "/trigger", "none");
	}

	public void setState(USERLED_STATE state) {
		HandleFile.write("/sys/devices/ocp.2/gpio-leds.8/leds/beaglebone:green:usr" + usr + "/brightness", state.getState());
	}

}
