package portaSerial;

public enum DataBits {
	DataBits_8bits(8), DataBits_7bits(7);

	private int dataBits;

	private DataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	public int getDataBits() {
		return dataBits;
	}
}
