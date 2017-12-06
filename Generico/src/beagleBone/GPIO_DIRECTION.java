package beagleBone;

public enum GPIO_DIRECTION {
	IN("in"), OUT("out");

	private String direction;

	private GPIO_DIRECTION(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return this.direction;
	}
}
