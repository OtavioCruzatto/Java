package beagleBone;

public enum USERLED_USR {
	USR_0("0"), USR_1("1"), USR_2("2"), USR_3("3");

	private String usr;

	private USERLED_USR(String usr) {
		this.usr = usr;
	}

	public String getUsr() {
		return this.usr;
	}
}
