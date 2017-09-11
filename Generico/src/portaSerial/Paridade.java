package portaSerial;

public enum Paridade {
	None("None"), Odd("Odd"), Even("Even");

	private String paridade;

	private Paridade(String paridade) {
		this.paridade = paridade;
	}

	public String getParidade() {
		return paridade;
	}
}
