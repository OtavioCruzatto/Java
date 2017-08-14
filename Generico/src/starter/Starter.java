package starter;

import com.fazecast.jSerialComm.SerialPort;
import portaSerial.Baud;
import portaSerial.CaracterDeFinalDeLinha;
import portaSerial.DataBits;
import portaSerial.Echo;
import portaSerial.PortaSerial;
import portaSerial.StatusDaRecepcao;

public class Starter {

	public static void main(String[] args) {

		PortaSerial portaSerial_1 = new PortaSerial();

		portaSerial_1.setControleDeFluxo(SerialPort.FLOW_CONTROL_DISABLED);
		portaSerial_1.setBaudrate(Baud.Baud_4800);
		portaSerial_1.setDataBits(DataBits.DataBits_8bits);
		portaSerial_1.setParidade(SerialPort.NO_PARITY);
		portaSerial_1.setStopBits(SerialPort.ONE_STOP_BIT);
		portaSerial_1.setFinalDeLinha(CaracterDeFinalDeLinha.Habilitar);
		portaSerial_1.setStringDeFinalDeLinha("***");
		portaSerial_1.setEcho(Echo.Habilitar);

//		for(int i = 0; i < portaSerial_1.getListaDePortasComDisponiveis().length; i++) {
//			System.out.println(portaSerial_1.getListaDePortasComDisponiveis()[i]);
//		}

		portaSerial_1.setPortaComSelecionada("COM3");
		if(portaSerial_1.conectarNaPorta()) {
			System.out.println("Conectado");
		}

		while(true) {
			if(portaSerial_1.getStatusDaRecepcao() == StatusDaRecepcao.Finalizada) {
				System.out.print(portaSerial_1.getDadosRecebidos());
				portaSerial_1.setStatusDaRecepcao(StatusDaRecepcao.NaoFinalizada);
				portaSerial_1.setDadosRecebidos("");
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
