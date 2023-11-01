package edu.umb.cs681.primes;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
        this.factors = new LinkedList<>();
    }

    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public void setDone() {
        lock.lock();
        try {
            done = true;
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
        }

    }

    public LinkedList<Long> getPrimeFactors() {
        return factors;
    }

    public static void main(String args[]) {

        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(36, 2, 20);
        Thread thread = new Thread(gen);
        thread.start(); //thread begins processing

        try { //introduced delay before setting done to true
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //setting done to true after the thread processes for sometime.
        gen.setDone();

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
