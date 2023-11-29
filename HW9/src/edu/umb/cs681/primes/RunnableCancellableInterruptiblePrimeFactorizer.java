package edu.umb.cs681.primes;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{

    private static ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done=true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock(); //introducing lock/unlock mechanism to prevent race conditions
            try {
                if (done) {
                    System.out.println("Stopped generating prime factors.");
                    break;
                }
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                } if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
            } finally {
                lock.unlock();
            }
            try { 
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                continue;
            }
        }
    }

    public LinkedList<Long> getPrimeFactors() {
        return factors;
    }

    public static void main(String args[]) {

        RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, 20);
        Thread thread = new Thread(gen);
        thread.start(); //thread begins processing

        try { //introduced delay
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        gen.setDone();
        //2-Step thread termination
        thread.interrupt();

        //joining the thread to wait for it finish processing.
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //resulting factors to display.
        System.out.println("Prime Factors are: " + gen.getPrimeFactors());
    }
}
