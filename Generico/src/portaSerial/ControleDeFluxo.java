package portaSerial;

public enum ControleDeFluxo {
	None("None");

	private String controleDeFluxo;

	private ControleDeFluxo(String controleDeFluxo) {
		this.controleDeFluxo = controleDeFluxo;
	}

	public String getControleDeFluxo() {
		return controleDeFluxo;
	}
}
