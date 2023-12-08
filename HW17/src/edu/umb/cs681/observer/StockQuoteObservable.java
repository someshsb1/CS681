package edu.umb.cs681.observer;

import java.util.concurrent.ConcurrentHashMap;

public class StockQuoteObservable extends Observable<StockEvent>{
    
    private ConcurrentHashMap<String, Double> stocQoutekMap = new ConcurrentHashMap<>(); //thread-safe


    public void changeQoute(String t, double q) {
        stocQoutekMap.put(t, q);
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
