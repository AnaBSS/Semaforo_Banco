package view;

import controller.Banco;
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		int trans = 21;
		Semaphore smf_saque = new Semaphore (1);
		Semaphore smf_deposito = new Semaphore (1);
		
		for (int i = 1 ; i < trans ; i++) {
	    new Banco (i, smf_saque, smf_deposito).start();
		}
		}
	}