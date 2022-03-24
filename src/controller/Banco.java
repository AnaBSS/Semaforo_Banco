package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Banco extends Thread {
	private int conta;
	private int saldo = new Random().nextInt(10000)+0;
	private Semaphore smf_saque;
	private Semaphore smf_deposito;
	
	public Banco (int conta, Semaphore smf_saque, Semaphore smf_deposito) {
		this.conta = conta;
		this.smf_saque = smf_saque;
		this.smf_deposito = smf_deposito;
	}
	
	public void Run () {
		int trans = new Random().nextInt(2)+1;
			switch (trans) {
			case 1: try {
				    smf_saque.acquire();
				    Saque();
				    sleep (1000);
			        break;
			       }catch (InterruptedException e) {
				    e.printStackTrace();
			       } finally {
				     smf_saque.release();
			       }
			case 2: try {
			        smf_deposito.acquire();
			        Deposito ();
			        sleep(1000);
			         break;
			       } catch (InterruptedException e) {
				     e.printStackTrace();
			       } finally {
				     smf_deposito.release();
			       }
			}
  }
	
	public void Saque () {
		int valor = new Random().nextInt(10000)+2;
		if (valor > saldo) {
	    	System.err.println("Saque solicitado \n ============ Conta #" +conta+ "============ \n *Saldo insuficiente* \n Saldo em conta: R$ "+saldo+",00");
	    }else {
		    saldo -= valor;
		    System.out.println("Saque solicitado \n ============ Conta #" +conta+ "============ \n *Você sacou R$ "+ valor +",00* \n Saldo em conta: R$ "+saldo+",00");
	    }
	}
	
	public void Deposito () {
		int valor = new Random().nextInt(10000)+2;
		saldo += valor;
		System.out.println("============ Conta #" +conta+ "============ \n *Você depositou R$"+ valor +",00* \n Saldo em conta: R$"+saldo+",00");
	}
}
