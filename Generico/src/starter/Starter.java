package starter;

import crc16.CRC16;

public class Starter {

	public static void main(String[] args) {

		System.out.println(Integer.toHexString(CRC16.calcularCrc16ASCII("123456789")));
		System.out.println(Integer.toHexString(CRC16.calcularCrc16HEX("AA 55 D0 25 9E 9D 9C")));
		
	}

}
