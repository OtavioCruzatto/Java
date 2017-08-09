package portaSerial;

import com.fazecast.jSerialComm.SerialPort;

public class PortaSerial {
	private SerialPort portaSerial;
	private String[] listaDePortasComDisponiveis;
	private String portaComSelecionada;
	private int controleDeFluxo;
	private int stopBits;
	private int paridade;
	private Baud baudrate;
	private DataBits dataBits;

	public boolean conectarNaPorta() {
		boolean portaComConectada = false;

		this.portaSerial = SerialPort.getCommPort(this.getPortaComSelecionada());
		this.portaSerial.openPort();
		if(this.portaSerial.isOpen()) {
			portaComConectada = true;
		}

		return portaComConectada;
	}

	public void configurarPorta() {
		this.portaSerial.setBaudRate(this.getBaudrate().getBaud());
		this.portaSerial.setNumDataBits(this.getDataBits().getDataBits());
		this.portaSerial.setFlowControl(this.getControleDeFluxo());
		this.portaSerial.setNumStopBits(this.getStopBits());
		this.portaSerial.setParity(this.getParidade());
	}

	public void enviarDados(String dadosParaSeremEnviados) {

	}

	public PortaSerial() {
		this.getListaDePortasComDisponiveis();
	}

	public SerialPort getPortaSerial() {
		return portaSerial;
	}

	public void setPortaSerial(SerialPort portaSerial) {
		this.portaSerial = portaSerial;
	}

	public String[] getListaDePortasComDisponiveis() {

		int quantidadeTotalDePortasCom = 0;
		int quantidadeDePortasComDisponiveis = 0;

		SerialPort[] portasSeriais = SerialPort.getCommPorts();
		quantidadeTotalDePortasCom = portasSeriais.length;
		String[] listaDePortasComDisponiveisAux = new String[quantidadeTotalDePortasCom];

		for(int contador = 0; contador < quantidadeTotalDePortasCom; contador++) {
			SerialPort portaTeste = SerialPort.getCommPort(portasSeriais[contador].getSystemPortName());
			portaTeste.openPort();
			if(portaTeste.isOpen()) {
				listaDePortasComDisponiveisAux[contador] = portaTeste.getSystemPortName();
				quantidadeDePortasComDisponiveis = quantidadeDePortasComDisponiveis + 1;
			}
			portaTeste.closePort();
		}

		listaDePortasComDisponiveis = new String[quantidadeDePortasComDisponiveis];
		for(int contador = 0; contador < quantidadeDePortasComDisponiveis; contador++) {
			listaDePortasComDisponiveis[contador] = listaDePortasComDisponiveisAux[contador];
		}

//		for(int contador = 0; contador < quantidadeDePortasComDisponiveis; contador++) {
//			System.out.println(listaDePortasComDisponiveis[contador]);
//		}

		return listaDePortasComDisponiveis;
	}

	public String getPortaComSelecionada() {
		return portaComSelecionada;
	}

	public void setPortaComSelecionada(String portaComSelecionada) {
		this.portaComSelecionada = portaComSelecionada;
	}

	public DataBits getDataBits() {
		return dataBits;
	}

	public void setDataBits(DataBits dataBits) {
		this.dataBits = dataBits;
	}

	public Baud getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(Baud baudrate) {
		this.baudrate = baudrate;
	}

	public int getControleDeFluxo() {
		return controleDeFluxo;
	}

	public void setControleDeFluxo(int controleDeFluxo) {
		this.controleDeFluxo = controleDeFluxo;
	}

	public int getStopBits() {
		return stopBits;
	}

	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}

	public int getParidade() {
		return paridade;
	}

	public void setParidade(int paridade) {
		this.paridade = paridade;
	}


}
