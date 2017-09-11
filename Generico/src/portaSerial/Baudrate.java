package portaSerial;

public enum Baudrate {
	Baud_1200("1200"), Baud_2400("2400"), Baud_4800("4800"), Baud_9600("9600"), Baud_19200("19200"), Baud_38400(
			"38400"), Baud_57600("57600"), Baud_115200("115200");

	private String baudrate;

	private Baudrate(String baudrate) {
		this.baudrate = baudrate;
	}

	public String getBaudrate() {
		return baudrate;
	}
}
