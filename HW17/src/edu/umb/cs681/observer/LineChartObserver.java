package edu.umb.cs681.observer;

public class LineChartObserver implements Observer<StockEvent> {

    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        //Call event.getTicker() and event.getQuote() and process the 2 info
        System.out.println("LineChartObserver: " + event.ticker() + " " + event.quote());
        
    }
    
}
