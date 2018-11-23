package starter;

import portaSerial.Baudrate;
import portaSerial.ControleDeFluxo;
import portaSerial.DataBits;
import portaSerial.Paridade;
import portaSerial.PortaSerial;
import portaSerial.StopBits;

public class Starter {

	public static void main(String[] args) {

		PortaSerial com = new PortaSerial();
		com.setPortaComSelecionada(com.getListaDePortasComDisponiveis()[0]);
		com.setBaudrate(Baudrate.Baud_115200.getBaudrate());
		com.setParidade(Paridade.None.getParidade());
		com.setDataBits(DataBits.DataBits_8_bits.getDataBits());
		com.setControleDeFluxo(ControleDeFluxo.None.getControleDeFluxo());
		com.setStopBits(StopBits.StopBit_1.getStopBits());
		com.conectarNaPorta();	
		com.configurarPortaSerial();
			
		
		while(true) {
			
			try {
				Thread.sleep(750);
				for(int aux = 0; aux < com.getDadosRecebidosByte().size(); aux++) {
					System.out.print(Integer.toHexString(com.getDadosRecebidosByte().get(aux)) + " ");
				}
				System.out.println();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	}

}
