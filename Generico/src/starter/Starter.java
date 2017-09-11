package starter;

import portaSerial.Baudrate;
import portaSerial.ControleDeFluxo;
import portaSerial.DataBits;
import portaSerial.Paridade;
import portaSerial.PortaSerial;
import portaSerial.StopBits;

public class Starter {

	public static void main(String[] args) {

		PortaSerial portaSerial_1 = new PortaSerial();
		portaSerial_1.setBaudrate(Baudrate.Baud_9600);
		portaSerial_1.setDataBits(DataBits.DataBits_8_bits);
		portaSerial_1.setParidade(Paridade.None);
		portaSerial_1.setStopBits(StopBits.StopBit_1);
		portaSerial_1.setControleDeFluxo(ControleDeFluxo.None);
		portaSerial_1.setPortaComSelecionada(portaSerial_1.getListaDePortasComDisponiveis()[0]);
		portaSerial_1.conectarNaPorta();


		for(int i = 0; i < 100; i++) {
			portaSerial_1.enviarDados("TESTE " + i + "\n");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
