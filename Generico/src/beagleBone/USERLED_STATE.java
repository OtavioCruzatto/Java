package beagleBone;

public enum USERLED_STATE {
	ON("1"), OFF("0");

	private String state;

	private USERLED_STATE(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

}