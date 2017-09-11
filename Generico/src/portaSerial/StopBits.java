package portaSerial;

public enum StopBits {
	StopBit_1("1 bit"), StopBits_1_5("1.5 bits"), StopBits_2("2 bits");

	private String stopBits;

	private StopBits(String stopBits) {
		this.stopBits = stopBits;
	}

	public String getStopBits() {
		return stopBits;
	}
}
