package beagleBone;

public enum GPIO_STATE {
	HIGH("1"), LOW("0");

	private String state;

	private GPIO_STATE(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
}
