package portaSerial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import graphicalUserInterface.ConfiguracaoPortaSerialController;

public class PortaSerial {
	private SerialPort portaSerial;
	private String[] listaDePortasComDisponiveis;
	private String portaComSelecionada;
	private String dadosRecebidos = "";
	private String stringDeFinalDeLinha = "";
	private StringDeFinalDeLinha finalDeLinha = StringDeFinalDeLinha.Desabilitar;
	private StatusDaRecepcao statusDaRecepcao = StatusDaRecepcao.NaoFinalizada;
	private Echo echo = Echo.Desabilitar;
	private String baudrate = Baudrate.Baud_9600.getBaudrate();
	private String dataBits = DataBits.DataBits_8_bits.getDataBits();
	private String paridade = Paridade.None.getParidade();
	private String controleDeFluxo = ControleDeFluxo.None.getControleDeFluxo();
	private String stopBits = StopBits.StopBit_1.getStopBits();
	private ConfiguracaoPortaSerialController configuracaoPortaSerialController;
	private boolean portaComConectada = false;

	public PortaSerial() {
		this.getListaDePortasComDisponiveis();
		
	}

	public boolean conectarNaPorta() {
		portaComConectada = false;

		this.portaSerial = SerialPort.getCommPort(this.getPortaComSelecionada());
		this.configurarPortaSerial();
		this.portaSerial.openPort();
		if(this.portaSerial.isOpen()) {
			portaComConectada = true;
		}

		this.portaSerial.addDataListener(new SerialPortDataListener() {

			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
					return;
				}
				else {

					/*
					 * Uma forma de receber os dados:
					 */
//					try {
//						InputStream entradaDeDados = portaSerial.getInputStream();
//						while(portaSerial.bytesAvailable() != 0) {
//							char dadoRecebido = (char) entradaDeDados.read();
//							setDadosRecebidos(getDadosRecebidos() + dadoRecebido);
//
//							if(echo == Echo.Habilitar) {
//								enviarDados(String.valueOf(dadoRecebido));
//							}
//
//						}
//
//						if(finalDeLinha == CaracterDeFinalDeLinha.Habilitar) {
//							if(getDadosRecebidos().endsWith(stringDeFinalDeLinha)) {
//								setDadosRecebidos(getDadosRecebidos().substring(0, getDadosRecebidos().lastIndexOf(stringDeFinalDeLinha)));
//								statusDaRecepcao = StatusDaRecepcao.Finalizada;
//							}
//							else {
//							statusDaRecepcao = StatusDaRecepcao.NaoFinalizada;
//							}
//						}
//
//						entradaDeDados.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}


					/*
					 * Outra forma de receber os dados:
					 */
					try {
						InputStream entradaDeDados = portaSerial.getInputStream();
						byte[] novosBytes = new byte[portaSerial.bytesAvailable()];
						entradaDeDados.read(novosBytes);

						for(int i = 0; i < novosBytes.length; i++) {
							setDadosRecebidos(getDadosRecebidos() + (char) novosBytes[i]);

							if(echo == Echo.Habilitar) {
								enviarDados(String.valueOf((char) novosBytes[i]));
							}
						}

						if(finalDeLinha == StringDeFinalDeLinha.Habilitar) {
							if(getDadosRecebidos().endsWith(String.valueOf(stringDeFinalDeLinha))) {
								setDadosRecebidos(getDadosRecebidos().substring(0, getDadosRecebidos().lastIndexOf(stringDeFinalDeLinha)));
								statusDaRecepcao = StatusDaRecepcao.Finalizada;
							}
							else {
								statusDaRecepcao = StatusDaRecepcao.NaoFinalizada;
							}
						}

						configuracaoPortaSerialController.interpretarDadosRecebidos(getDadosRecebidos());

						entradaDeDados.close();

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		});

		return portaComConectada;
	}

	public void configurarPortaSerial() {

		this.portaSerial.setBaudRate(Integer.parseInt(baudrate));

		switch (dataBits) {
			case "7 bits":
				this.portaSerial.setNumDataBits(7);
			break;

			case "8 bits":
				this.portaSerial.setNumDataBits(8);
			break;

			default:
				System.out.println("Databits inválido!");
				System.out.println("Obs: Para 7 bits: String = \"7 bits\"");
				System.out.println("Obs: Para 8 bits: String = \"8 bits\"");
			break;
		}

		switch (paridade) {
			case "None":
				this.portaSerial.setParity(SerialPort.NO_PARITY);
			break;

			case "Odd":
				this.portaSerial.setNumDataBits(SerialPort.ODD_PARITY);
			break;

			case "Even":
				this.portaSerial.setNumDataBits(SerialPort.EVEN_PARITY);
			break;

			default:
				System.out.println("Paridade inválido!");
				System.out.println("Obs: Para None: String = \"None\"");
				System.out.println("Obs: Para Odd: String = \"Odd\"");
				System.out.println("Obs: Para Even: String = \"Even\"");
			break;
		}

		switch (stopBits) {
			case "1 bit":
				this.portaSerial.setNumStopBits(SerialPort.ONE_STOP_BIT);
			break;

			case "1.5 bits":
				this.portaSerial.setNumStopBits(SerialPort.ONE_POINT_FIVE_STOP_BITS);
			break;

			case "2 bits":
				this.portaSerial.setNumStopBits(SerialPort.TWO_STOP_BITS);
			break;

			default:
				System.out.println("StopBits inválido!");
				System.out.println("Obs: Para 1 bit: String = \"1 bit\"");
				System.out.println("Obs: Para 1.5 bits: String = \"1.5 bits\"");
				System.out.println("Obs: Para 2 bits: String = \"1.5 bits\"");
			break;
		}

		switch (controleDeFluxo) {
			case "None":
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
				break;
			case "Xon / Xoff":
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED);
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED);
				break;
			case "Hardware":
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_CTS_ENABLED);
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_RTS_ENABLED);
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_DTR_ENABLED);
				portaSerial.setFlowControl(SerialPort.FLOW_CONTROL_DSR_ENABLED);
				break;
		}

	}

	public void enviarDados(String dadosParaSeremEnviados) {
		try {
			OutputStream saidaDeDados = this.portaSerial.getOutputStream();
			saidaDeDados.write(dadosParaSeremEnviados.getBytes());
			saidaDeDados.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fecharPortaSerial() {
		if(portaComConectada == true) {
			this.portaSerial.closePort();
		}
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

		for(int contador = 0; contador < quantidadeDePortasComDisponiveis; contador++) {
			System.out.println(listaDePortasComDisponiveis[contador]);
		}

		return listaDePortasComDisponiveis;
	}

	public String getPortaComSelecionada() {
		return portaComSelecionada;
	}

	public void setPortaComSelecionada(String portaComSelecionada) {
		this.portaComSelecionada = portaComSelecionada;
	}

	public String getDadosRecebidos() {
		return dadosRecebidos;
	}

	public void setDadosRecebidos(String dadosRecebidos) {
		this.dadosRecebidos = dadosRecebidos;
	}

	public StringDeFinalDeLinha getFinalDeLinha() {
		return finalDeLinha;
	}

	public void setFinalDeLinha(StringDeFinalDeLinha finalDeLinha) {
		this.finalDeLinha = finalDeLinha;
	}

	public StatusDaRecepcao getStatusDaRecepcao() {
		return statusDaRecepcao;
	}

	public void setStatusDaRecepcao(StatusDaRecepcao statusDaRecepcao) {
		this.statusDaRecepcao = statusDaRecepcao;
	}

	public Echo getEcho() {
		return echo;
	}

	public void setEcho(Echo echo) {
		this.echo = echo;
	}

	public String getStringDeFinalDeLinha() {
		return stringDeFinalDeLinha;
	}

	public void setStringDeFinalDeLinha(String stringDeFinalDeLinha) {
		this.stringDeFinalDeLinha = stringDeFinalDeLinha;
	}

	public SerialPort getPortaSerial() {
		return portaSerial;
	}

	public void setPortaSerial(SerialPort portaSerial) {
		this.portaSerial = portaSerial;
	}

	public String getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(String baudrate) {
		this.baudrate = baudrate;
	}

	public String getDataBits() {
		return dataBits;
	}

	public void setDataBits(String dataBits) {
		this.dataBits = dataBits;
	}

	public String getParidade() {
		return paridade;
	}

	public void setParidade(String paridade) {
		this.paridade = paridade;
	}

	public String getControleDeFluxo() {
		return controleDeFluxo;
	}

	public void setControleDeFluxo(String controleDeFluxo) {
		this.controleDeFluxo = controleDeFluxo;
	}

	public String getStopBits() {
		return stopBits;
	}

	public void setStopBits(String stopBits) {
		this.stopBits = stopBits;
	}

	public ConfiguracaoPortaSerialController getConfiguracaoPortaSerialController() {
		return configuracaoPortaSerialController;
	}

	public void setConfiguracaoPortaSerialController(ConfiguracaoPortaSerialController configuracaoPortaSerialController) {
		this.configuracaoPortaSerialController = configuracaoPortaSerialController;
	}

}
