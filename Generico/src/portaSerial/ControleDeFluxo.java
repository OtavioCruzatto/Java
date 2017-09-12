package portaSerial;

public enum ControleDeFluxo {
	None("None"), Xon_Xoff("Xon / Xoff"), Hardware("Hardware");

	private String controleDeFluxo;

	private ControleDeFluxo(String controleDeFluxo) {
		this.controleDeFluxo = controleDeFluxo;
	}

	public String getControleDeFluxo() {
		return controleDeFluxo;
	}
}
