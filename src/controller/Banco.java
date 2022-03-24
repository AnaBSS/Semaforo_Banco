package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Banco extends Thread {
	public String tipo_trans;
	private int trans;
	private int conta;
	private int saldo = new Random().nextInt(10000)+0;
	private Semaphore smf_saque;
	private Semaphore smf_deposito;
	
	public Banco (int trans, Semaphore smf_saque, Semaphore smf_deposito) {
		this.trans = trans;
		this.smf_saque = smf_saque;
		this.smf_deposito = smf_deposito;
	}
	
	public void Run () {
			switch (trans) {
			case 1: try {
				    tipo_trans = "saque";
				    smf_saque.acquire();
				    Saque();
			        break;
			       }catch (InterruptedException e) {
				    e.printStackTrace();
			       } finally {
				     smf_saque.release();
			       }
			case 2: try {
				    tipo_trans = "depostito";
			        smf_deposito.acquire();
			        Deposito ();
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
