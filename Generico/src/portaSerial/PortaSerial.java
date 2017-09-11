package portaSerial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class PortaSerial {
	private SerialPort portaSerial;
	private String[] listaDePortasComDisponiveis;
	private String portaComSelecionada;
	private int controleDeFluxo;
	private int stopBits;
	private int paridade;
	private Baud baudrate;
	private DataBits dataBits = DataBits.DataBits_8bits;
	private String dadosRecebidos = "";
	private String stringDeFinalDeLinha = "";
	private StringDeFinalDeLinha finalDeLinha = StringDeFinalDeLinha.Desabilitar;
	private StatusDaRecepcao statusDaRecepcao = StatusDaRecepcao.NaoFinalizada;
	private Echo echo = Echo.Desabilitar;

	public PortaSerial() {
		this.getListaDePortasComDisponiveis();
	}

	public boolean conectarNaPorta() {
		boolean portaComConectada = false;

		this.portaSerial = SerialPort.getCommPort(this.getPortaComSelecionada());
		this.configurarPorta();
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

						entradaDeDados.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		});

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
		try {
			OutputStream saidaDeDados = this.portaSerial.getOutputStream();
			saidaDeDados.write(dadosParaSeremEnviados.getBytes());
			saidaDeDados.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fecharPortaCom() {
		this.portaSerial.closePort();
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


}
