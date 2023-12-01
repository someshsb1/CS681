package edu.umb.cs681.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent>{
    
    private Map<String, Double> stocQoutekMap = new HashMap<>();
    private ReentrantLock lockT = new ReentrantLock();
    private ReentrantLock lockQ = new ReentrantLock();


    public void changeQoute(String t, double q) {
        lockT.lock();
        lockQ.lock();
        try {
            stocQoutekMap.put(t, q);
        } finally {
            lockT.unlock();
            lockQ.unlock();
        }
        notifyObservers(new StockEvent(t, q)); //Update a map with t and q; notifyObservers(new StockEvent(t, q));
    }

    public static void main(String args[]) {
        
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();
        LineChartObserver lineChartObserver = new LineChartObserver();
        TableObserver tableObserver = new TableObserver();
        ThreeDOObserver threeDoObserver = new ThreeDOObserver();

        stockQuoteObservable.addObserver(lineChartObserver);
        stockQuoteObservable.addObserver(tableObserver);
        stockQuoteObservable.addObserver(threeDoObserver);

        Thread[] threadsObs = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threadsObs[i] = new Thread(() -> {
            String t = "test";
            Double q = Math.random() * 100;
            stockQuoteObservable.changeQoute(t, q);
            });
            threadsObs[i].start();
        }
        for (Thread threads : threadsObs) {
            try {
                threads.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
