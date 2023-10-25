package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private int dominationCounts = 0;

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

    public void setDominationCounts(List<Car> cars) {
        for (Car car: cars) {
            if (car == this) {
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

        //list of the cars
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Ford", "A", 3000, 2019, 2300f));
        cars.add(new Car("Porsche", "B", 2000, 2022, 4000f));
        cars.add(new Car("Maruti", "C", 1500, 2017, 2000f));
        cars.add(new Car("Tesla", "D", 3600, 2023, 1000f));

        //setting the threshold based on the car mileage
        int mileageThreshold = 2100;

        //sorting the cars by mileage in ascending order/default order
        List<Car> sortByCarMileage = cars.stream().sorted(Comparator.comparingDouble(Car::getMileage)).collect(toList());

        //seperating the cars to high and low groups based on their mileage
        Map<Boolean, List<Car>> grpMileage = sortByCarMileage.stream().collect(partitioningBy(car -> car.getMileage() >= mileageThreshold));

        List<Car> grpMilHigh = grpMileage.get(true); //greater than or equal to threshold
        List<Car> grpMilLow = grpMileage.get(false); //less than the threshold

        //get average, highest, lowest values of high group
        DoubleSummaryStatistics stats1 = grpMilHigh.stream()
                                        .collect(summarizingDouble(Car::getMileage));

        //get average, highest, lowest, count values of low group
        DoubleSummaryStatistics stats2 = grpMilLow.stream()
                                        .mapToDouble(Car::getMileage)
                                        .summaryStatistics();

        //printing the results of high and low group for car mileage
        System.out.println("High Car Mileage Group: ");
        System.out.println("Average Mileage: " + stats1.getAverage());
        System.out.println("High Mileage: " + stats1.getMax());
        System.out.println("Low Mileage: " + stats1.getMin());
        System.out.println("Number of Cars: " + stats1.getCount());

        System.out.println("Low Car Mileage Group: ");
        System.out.println("Average Mileage: " + stats2.getAverage());
        System.out.println("High Mileage: " + stats2.getMax());
        System.out.println("Low Mileage: " + stats2.getMin());
        System.out.println("Number of Cars: " + stats2.getCount());
        System.out.println("");

        //setting the threshold for car price
        float priceThreshold = 2000f;

        // sorting the cars by price in ascending order/default order
        List<Car> sortByCarPrice = cars.stream().sorted(Comparator.comparingDouble(Car::getPrice)).collect(toList());

        //seperating the cars to high and low groups based on their price
        Map<Boolean, List<Car>> grpPrice = sortByCarPrice.stream().collect(partitioningBy(car -> car.getPrice() >= priceThreshold));

        List<Car> grpPriceHigh = grpPrice.get(true); //greater than or equal to threshold
        List<Car> grpPriceLow = grpPrice.get(false); //less than the threshold

        //get average, highest, lowest values of high group
        DoubleSummaryStatistics stats3 = grpPriceHigh.stream()
                                        .collect(summarizingDouble(Car::getPrice));
        //get average, highest, lowest values of low group
        DoubleSummaryStatistics stats4 = grpPriceLow.stream()
                                        .mapToDouble(Car::getPrice)
                                        .summaryStatistics();
        //printing the results of high and low groups for car year
        System.out.println("High Car Price Group: ");
        System.out.println("Average Price: " + stats3.getAverage());
        System.out.println("High Price: " + stats3.getMax());
        System.out.println("Low Price: " + stats3.getMin());
        System.out.println("Number of Cars: " + stats3.getCount());

        System.out.println("Low Car Price Group: ");
        System.out.println("Average Price: " + stats4.getAverage());
        System.out.println("High Price: " + stats4.getMax());
        System.out.println("Low Price: " + stats4.getMin());
        System.out.println("Number of Cars: " + stats4.getCount());
        System.out.println("");

        //setting the threshold for car year
        int yearThreshold = 2022;

        //sorting the cars by year in ascending order/default order
        List<Car> sortByCarYear = cars.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(toList());

        //seperating the cars to high and low groups based on their year
        Map<Boolean, List<Car>> grpYear = sortByCarYear.stream().collect(partitioningBy(car -> car.getYear() >= yearThreshold));

        List<Car> grpYearHigh = grpYear.get(true); //greater than or equal to threshold
        List<Car> grpYearLow = grpYear.get(false); //less than the threshold

        //get average, highest, lowest values of high group
        IntSummaryStatistics stats5 = grpYearHigh.stream()
                                        .collect(summarizingInt(Car::getYear));

        //get average, highest, lowest values of low group
        IntSummaryStatistics stats6 = grpYearLow.stream()
                                        .mapToInt(Car::getYear)
                                        .summaryStatistics();

        //pringting the results of high and low year groups
        System.out.println("High Car Year Group: ");
        System.out.println("Average Year: " + stats5.getAverage());
        System.out.println("High Year: " + stats5.getMax());
        System.out.println("Low Year: " + stats5.getMin());
        System.out.println("Number of Cars: " + stats5.getCount());

        System.out.println("Low Car Year Group: ");
        System.out.println("Average Year: " + stats6.getAverage());
        System.out.println("High Year: " + stats6.getMax());
        System.out.println("Low Year: " + stats6.getMin());
        System.out.println("Number of Cars: " + stats6.getCount());
        System.out.println("");

        //calculating the domination count for the car list above and fetching the same of high and low groups.
        for (Car car: cars) {
            car.setDominationCounts(cars);
        }

        //setting the threshold for domination count
        int dominationCountsThreshold = 2;

        //sorting the cars by domination count in ascending order/default order.
        List<Car> sortByCardominationCounts = cars.stream().sorted(Comparator.comparingInt(Car::getDominationCounts)).collect(toList());

        //seperating the cars to high and low groups based on their domination

        Map<Boolean, List<Car>> grpDominationCount = sortByCardominationCounts.stream().collect(partitioningBy(car -> car.getDominationCounts() >= dominationCountsThreshold));

        List<Car> grpdominationCountsHigh = grpDominationCount.get(true); //greater than or equal to threshold
        List<Car> grpdominationCountsLow = grpDominationCount.get(false); //less than the threshold

        //get average, highest, lowest values of high group
        IntSummaryStatistics stats7 = grpdominationCountsHigh.stream()
                                        .collect(summarizingInt(Car::getDominationCounts));

        //get average, highest, lowest values of low group
        IntSummaryStatistics stats8 = grpdominationCountsLow.stream()
                                        .mapToInt(Car::getDominationCounts)
                                        .summaryStatistics();
        //printing the results of high and low domination groups
        System.out.println("High Car dominationCounts Group: ");
        System.out.println("Average dominationCounts: " + stats7.getAverage());
        System.out.println("High dominationCounts: " + stats7.getMax());
        System.out.println("Low dominationCounts: " + stats7.getMin());
        System.out.println("Number of Cars: " + stats7.getCount());

        System.out.println("Low Car dominationCounts Group: ");
        System.out.println("Average dominationCounts: " + stats8.getAverage());
        System.out.println("High dominationCounts: " + stats8.getMax());
        System.out.println("Low dominationCounts: " + stats8.getMin());
        System.out.println("Number of Cars: " + stats8.getCount());

    }
}
