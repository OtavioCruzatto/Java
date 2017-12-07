package beagleBone;

import java.io.File;

public class Pwm {
	private String pino = "";

	public Pwm(PWM_PIN pino) {
		this.pino = pino.getPwm();
	}

	/**
	 * Método para inicializar um pino da BeagleBone como PWM.
	 * @param pino
	 * @author Otavio
	 */
	public void init(){
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

		verificacao = HandleFile.read(path + "/slots", 20);

		if(!verificacao.contains("am33xx_pwm")){
			HandleFile.write(path + "/slots", "am33xx_pwm");
		}

		if(!verificacao.contains("bone_pwm_" + pino)){
			HandleFile.write(path + "/slots", "bone_pwm_" + pino);
		}
	}

	/**
	 * Periodo e duty cycle em nanosegundos
	 * @param parametroDeConfigaucao
	 * @param valorDoParametro
	 */
	public void config(PWM_CONFIG parametroDeConfigaucao, String valorDoParametro) {
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

		HandleFile.write(adr2 + parametroDeConfigaucao.getPwm(), valorDoParametro);
	}

}
