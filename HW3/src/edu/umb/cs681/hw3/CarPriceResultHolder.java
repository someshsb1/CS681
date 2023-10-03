package edu.umb.cs681.hw3;

public class CarPriceResultHolder {
    private int numCarExamined;
    private double average;

    public CarPriceResultHolder() {
        numCarExamined = 0;
        average = 0.0;
    }

    public void setNumCarExamined(int numCarExamined) {
        this.numCarExamined = numCarExamined;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getNumCarExamined() {
        return numCarExamined;
    }

    public double getAverage() {
       
        return average;
    }
}
