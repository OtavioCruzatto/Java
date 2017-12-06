package beagleBone;

import basesNumericas.BasesNumericas;

	// PORT:      GPIO44 | GPIO45 | GPIO46 | GPIO47 | GPIO66 | GPIO67 | GPIO68 | GPIO69
	// PORT:      PORT_7 | PORT_6 | PORT_5 | PORT_4 | PORT_3 | PORT_2 | PORT_1 | PORT_0
	// OBS: 0->NIVEL LOGICO BAIXO NO PINO , 1->NIVEL LOGICO ALTO NO PINO

public class Port_1 {
	Gpio port_7;
	Gpio port_6;
	Gpio port_5;
	Gpio port_4;
	Gpio port_3;
	Gpio port_2;
	Gpio port_1;
	Gpio port_0;

	public Port_1() {
		port_7 = new Gpio(GPIO_PIN.GPIO44);
		port_6 = new Gpio(GPIO_PIN.GPIO45);
		port_5 = new Gpio(GPIO_PIN.GPIO46);
		port_4 = new Gpio(GPIO_PIN.GPIO47);
		port_3 = new Gpio(GPIO_PIN.GPIO66);
		port_2 = new Gpio(GPIO_PIN.GPIO67);
		port_1 = new Gpio(GPIO_PIN.GPIO68);
		port_0 = new Gpio(GPIO_PIN.GPIO69);
	}

	public void direction(String data, BaseNumerica base) {

		if(base == BaseNumerica.BINARIO) {
			directionAux(data);
		}
		else if(base == BaseNumerica.HEXADECIMAL) {
			String data_bin = BasesNumericas.converterHexadecimalParaBinario(data);
			directionAux(data_bin);
		}
		else if(base == BaseNumerica.DECIMAL) {
			String data_bin = BasesNumericas.converterDecimalParaBinario(data);
			directionAux(data_bin);
		}

	}


	public void output(String data, BaseNumerica base) {

		if(base == BaseNumerica.BINARIO) {
			outputAux(data);
		}
		else if(base == BaseNumerica.HEXADECIMAL) {
			String data_bin = BasesNumericas.converterHexadecimalParaBinario(data);
			outputAux(data_bin);
		}
		else if(base == BaseNumerica.DECIMAL) {
			String data_bin = BasesNumericas.converterDecimalParaBinario(data);
			outputAux(data_bin);
		}

	}

	public void export() {
		port_7.export();
		port_6.export();
		port_5.export();
		port_4.export();
		port_3.export();
		port_2.export();
		port_1.export();
		port_0.export();
	}

	public void unexport() {
		port_7.unexport();
		port_6.unexport();
		port_5.unexport();
		port_4.unexport();
		port_3.unexport();
		port_2.unexport();
		port_1.unexport();
		port_0.unexport();
	}

	private void directionAux(String data) {

		char bit_7 = data.charAt(0);
		char bit_6 = data.charAt(1);
		char bit_5 = data.charAt(2);
		char bit_4 = data.charAt(3);
		char bit_3 = data.charAt(4);
		char bit_2 = data.charAt(5);
		char bit_1 = data.charAt(6);
		char bit_0 = data.charAt(7);

		if(bit_7 == '0') { port_7.direction(GPIO_DIRECTION.OUT); } else { port_7.direction(GPIO_DIRECTION.IN); }
		if(bit_6 == '0') { port_6.direction(GPIO_DIRECTION.OUT); } else { port_6.direction(GPIO_DIRECTION.IN); }
		if(bit_5 == '0') { port_5.direction(GPIO_DIRECTION.OUT); } else { port_5.direction(GPIO_DIRECTION.IN); }
		if(bit_4 == '0') { port_4.direction(GPIO_DIRECTION.OUT); } else { port_4.direction(GPIO_DIRECTION.IN); }
		if(bit_3 == '0') { port_3.direction(GPIO_DIRECTION.OUT); } else { port_3.direction(GPIO_DIRECTION.IN); }
		if(bit_2 == '0') { port_2.direction(GPIO_DIRECTION.OUT); } else { port_2.direction(GPIO_DIRECTION.IN); }
		if(bit_1 == '0') { port_1.direction(GPIO_DIRECTION.OUT); } else { port_1.direction(GPIO_DIRECTION.IN); }
		if(bit_0 == '0') { port_0.direction(GPIO_DIRECTION.OUT); } else { port_0.direction(GPIO_DIRECTION.IN); }

	}

	private void outputAux(String data) {

		char bit_7 = data.charAt(0);
		char bit_6 = data.charAt(1);
		char bit_5 = data.charAt(2);
		char bit_4 = data.charAt(3);
		char bit_3 = data.charAt(4);
		char bit_2 = data.charAt(5);
		char bit_1 = data.charAt(6);
		char bit_0 = data.charAt(7);

		if(bit_7 == '0') { port_7.output(GPIO_STATE.LOW); } else { port_7.output(GPIO_STATE.HIGH); }
		if(bit_6 == '0') { port_6.output(GPIO_STATE.LOW); } else { port_6.output(GPIO_STATE.HIGH); }
		if(bit_5 == '0') { port_5.output(GPIO_STATE.LOW); } else { port_5.output(GPIO_STATE.HIGH); }
		if(bit_4 == '0') { port_4.output(GPIO_STATE.LOW); } else { port_4.output(GPIO_STATE.HIGH); }
		if(bit_3 == '0') { port_3.output(GPIO_STATE.LOW); } else { port_3.output(GPIO_STATE.HIGH); }
		if(bit_2 == '0') { port_2.output(GPIO_STATE.LOW); } else { port_2.output(GPIO_STATE.HIGH); }
		if(bit_1 == '0') { port_1.output(GPIO_STATE.LOW); } else { port_1.output(GPIO_STATE.HIGH); }
		if(bit_0 == '0') { port_0.output(GPIO_STATE.LOW); } else { port_0.output(GPIO_STATE.HIGH); }

	}

}
