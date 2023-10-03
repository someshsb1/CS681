package edu.umb.cs681.hw3;

import java.util.ArrayList;
import java.util.List;

public class Car {
    
    private String make, model;
    private int year, mileage, dominationCounts;
    private float price; 

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public void setDominationCount(List<Car> cars) {
        for (Car car: cars) {
            if(car == this) {
                continue;
            }
            if ((car.getPrice() >= this.getPrice() && car.getMileage() <= this.getMileage() && car.getYear() <= this.getYear())) {
                if ((car.getPrice() > this.getPrice() || car.getMileage() < this.getMileage() || car.getYear() < this.getYear())) {
                    this.dominationCounts++;
                }
            }
        }
    }

    public int getDominationCounts() {
        return dominationCounts;
    }


    public static void main(String args[]) {
        List<Car> cars = new ArrayList<>();

        Car car1 = new Car("A", "B", 1000, 2016, 1300f);
        Car car2 = new Car("C", "D", 1400, 2018, 1200f);
        Car car3 = new Car("E", "F", 1400, 2019, 1800f);
        Car car4 = new Car("G", "H", 800, 2022, 2800f);

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        for (Car car: cars) {
            car.setDominationCount(cars);
        }

        //map reduce code for calculating average price - using the slide 33 to implement the required logic

        double averagePrice = cars.stream()
                                    .map(car -> car.getPrice())
                                    .reduce(new CarPriceResultHolder(), 
                                    (result, price) -> {
                                                        result.setAverage((result.getAverage() * result.getNumCarExamined() + price) / (result.getNumCarExamined() + 1));
                                                        result.setNumCarExamined(result.getNumCarExamined() + 1);
                                    return result;}, 
                                    (finalResult, intermediateResult) -> finalResult)
                                .getAverage();

        System.out.println("The average car price is : " + averagePrice);

    }
}
