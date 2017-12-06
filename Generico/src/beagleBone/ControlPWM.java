package beagleBone;

import java.io.File;

public class ControlPWM {

	/**
	 * Método para inicializar um pino da BeagleBone como PWM.
	 * @param pino
	 * @author Otavio
	 */
	public static void init(PWM_PIN pino){
		String path = "";
		String verificacao = "";
		File diretorio = new File("/sys/devices");

		if(diretorio.isDirectory()){
		    String[] arqs = diretorio.list();
		    for(String nome : arqs){
		    	if(nome.startsWith("bone_")){
		    		path = "/sys/devices/" + nome;
		    	}
		    }
		}

		verificacao = HandleFile.read(path + "/slots");

		if(!(verificacao.contains("am33xx_pwm"))){
			HandleFile.write(path + "/slots", "am33xx_pwm") ;
		}

		if(!(verificacao.contains("bone_pwm_" + pino.getPwm()))){
			HandleFile.write(path + "/slots", "bone_pwm_" + pino.getPwm()) ;
		}
	}

	public static void PWM_control(PWM_PIN pino, PWM_CONFIG tipo_de_dado, String valor_do_dado) {
		String adr1 = "";
		String adr2 = "";
		File dir2 = new File("/sys/devices");

		if(dir2.isDirectory()){
		    String[] arqs = dir2.list();
		    for(String nome : arqs){
		    	if(nome.startsWith("ocp")){
		    		adr1 = "/sys/devices/" + nome;
		    	}
		    }
		}

		dir2 = new File(adr1);
		if(dir2.isDirectory()){
		    String[] arqs = dir2.list();
		    for(String nome : arqs){
		    	if(nome.startsWith("pwm_test_" + pino)){
		    		adr2 = adr1 + "/" + nome + "/";
		    	}
		    }
		}

		HandleFile.write(adr2 + tipo_de_dado.getPwm(), valor_do_dado);
	}

}
