package portaSerial;

public enum DataBits {
	DataBits_8_bits("8 bits"), DataBits_7_bits("7 bits");

	private String dataBits;

	private DataBits(String dataBits) {
		this.dataBits = dataBits;
	}

	public String getDataBits() {
		return dataBits;
	}
}
