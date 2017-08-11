package starter;

import com.fazecast.jSerialComm.SerialPort;

import portaSerial.Baud;
import portaSerial.DataBits;
import portaSerial.PortaSerial;

public class Starter {

	public static void main(String[] args) {

		PortaSerial portaSerial_1 = new PortaSerial();

		portaSerial_1.setControleDeFluxo(SerialPort.FLOW_CONTROL_DISABLED);
		portaSerial_1.setBaudrate(Baud.Baud_9600);
		portaSerial_1.setDataBits(DataBits.DataBits_8bits);
		portaSerial_1.setParidade(SerialPort.NO_PARITY);
		portaSerial_1.setStopBits(SerialPort.ONE_STOP_BIT);
		portaSerial_1.setPortaComSelecionada("COM3");
		portaSerial_1.setPortaComSelecionada(portaSerial_1.getPortaComSelecionada());
		portaSerial_1.conectarNaPorta();

		for(int i = 0; i < portaSerial_1.getListaDePortasComDisponiveis().length; i++) {
			System.out.println(portaSerial_1.getListaDePortasComDisponiveis()[i]);
		}

		while(true) {
			portaSerial_1.enviarDados("TESTE\n\r");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
