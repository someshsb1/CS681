package edu.umb.cs681.basics;

import java.time.Duration;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone() {
		done = true;
	}

	public void run(){
		try{
			for(int i = 0; i < 10 && !done ; i++){
				account.deposit(100);
				Thread.sleep( Duration.ofSeconds(2) );
			}
		}catch(InterruptedException exception){}
	}
}
