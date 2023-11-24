package edu.umb.cs681.basics;

import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockedBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	
	public void deposit(double amount){
		while( balance > 300 ){
			try{
				System.out.print("W");
				Thread.sleep( Duration.ofSeconds(2) );
			}
			catch (InterruptedException e){}
		}
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance += amount;
			System.out.println(", New balance (d): " + balance);
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		while( balance <= 0 ){
			try{
				System.out.print("W");
				Thread.sleep( Duration.ofSeconds(2) );
			}
			catch (InterruptedException e){}
		}
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance -= amount;
			System.out.println(", New balance (w): " + balance);
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }

	public static void main(String[] args){
		DeadlockedBankAccount2 bankAccount = new DeadlockedBankAccount2();
		new Thread( new DepositRunnable(bankAccount) ).start();
		new Thread( new WithdrawRunnable(bankAccount) ).start();
	}
}