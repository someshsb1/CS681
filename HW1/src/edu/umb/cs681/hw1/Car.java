package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
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
        List<Car> sortByCarMileage = cars.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(toList());

        //seperating the cars to high and low groups based on their mileage
        List<Car> grpMilHigh = sortByCarMileage.stream().filter(car -> car.getMileage() >= mileageThreshold).collect(toList());
        List<Car> grpMilLow = sortByCarMileage.stream().filter(car -> car.getMileage() < mileageThreshold).collect(toList());

        //get average, highest, lowest values of high group
        OptionalDouble avgMileageHigh = grpMilHigh.stream().mapToInt(Car::getMileage).average();
        OptionalInt highMileageHigh = grpMilHigh.stream().mapToInt(Car::getMileage).max();
        OptionalInt lowMileageHigh = grpMilHigh.stream().mapToInt(Car::getMileage).min();

        //get average, highest, lowest values of low group
        OptionalDouble avgMileageLow = grpMilLow.stream().mapToInt(Car::getMileage).average();
        OptionalInt highMileageLow = grpMilLow.stream().mapToInt(Car::getMileage).max();
        OptionalInt lowMileageLow = grpMilLow.stream().mapToInt(Car::getMileage).min();

        //get the number of cars for both high and low groups
        int mileageHighCount = grpMilHigh.size();
        int mileageLowCount = grpMilLow.size();

        //printing the results of high and low group for car mileage
        System.out.println("High Car Mileage Group: ");
        System.out.println("Average Mileage: " + avgMileageHigh);
        System.out.println("High Mileage: " + highMileageHigh);
        System.out.println("Low Mileage: " + lowMileageHigh);
        System.out.println("Number of Cars: " + mileageHighCount);

        System.out.println("Low Car Mileage Group: ");
        System.out.println("Average Mileage: " + avgMileageLow);
        System.out.println("High Mileage: " + highMileageLow);
        System.out.println("Low Mileage: " + lowMileageLow);
        System.out.println("Number of Cars: " + mileageLowCount);
        System.out.println("");

        //setting the threshold for car price
        float priceThreshold = 2000f;

        // sorting the cars by price in ascending order/default order
        List<Car> sortByCarPrice = cars.stream().sorted(Comparator.comparingDouble(Car::getPrice)).collect(toList());

        //seperating the cars to high and low groups based on their price
        List<Car> grpPriceHigh = sortByCarPrice.stream().filter(car -> car.getPrice() >= priceThreshold).collect(toList());
        List<Car> grpPriceLow = sortByCarPrice.stream().filter(car -> car.getPrice() < priceThreshold).collect(toList());

        //get average, highest, lowest values of high group
        OptionalDouble avgPriceHigh = grpPriceHigh.stream().mapToDouble(Car::getPrice).average();
        OptionalDouble highPriceHigh = grpPriceHigh.stream().mapToDouble(Car::getPrice).max();
        OptionalDouble lowPriceHigh = grpPriceHigh.stream().mapToDouble(Car::getPrice).min();

        //get average, highest, lowest values of low group
        OptionalDouble avgPriceLow = grpPriceLow.stream().mapToDouble(Car::getPrice).average();
        OptionalDouble highPriceLow = grpPriceLow.stream().mapToDouble(Car::getPrice).max();
        OptionalDouble lowPriceLow = grpPriceLow.stream().mapToDouble(Car::getPrice).min();

        //get the number of cars for both high and low groups
        int priceHighCount = grpPriceHigh.size();
        int priceLowCount = grpPriceLow.size();

        //printing the results of high and low groups for car year
        System.out.println("High Car Price Group: ");
        System.out.println("Average Price: " + avgPriceHigh);
        System.out.println("High Price: " + highPriceHigh);
        System.out.println("Low Price: " + lowPriceHigh);
        System.out.println("Number of Cars: " + priceHighCount);

        System.out.println("Low Car Price Group: ");
        System.out.println("Average Price: " + avgPriceLow);
        System.out.println("High Price: " + highPriceLow);
        System.out.println("Low Price: " + lowPriceLow);
        System.out.println("Number of Cars: " + priceLowCount);
        System.out.println("");

        //setting the threshold for car year
        int yearThreshold = 2022;

        //sorting the cars by year in ascending order/default order
        List<Car> sortByCarYear = cars.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(toList());
        
        //seperating the cars to high and low groups based on their year
        List<Car> grpYearHigh = sortByCarYear.stream().filter(car -> car.getYear() >= yearThreshold).collect(toList());
        List<Car> grpYearLow = sortByCarYear.stream().filter(car -> car.getYear() < yearThreshold).collect(toList());

        //get average, highest, lowest values of high group
        OptionalDouble avgYearHigh = grpYearHigh.stream().mapToInt(Car::getYear).average();
        OptionalInt highYearHigh = grpYearHigh.stream().mapToInt(Car::getYear).max();
        OptionalInt lowYearHigh = grpYearHigh.stream().mapToInt(Car::getYear).min();

        //get average, highest, lowest values of low group
        OptionalDouble avgYearLow = grpYearLow.stream().mapToInt(Car::getYear).average();
        OptionalInt highYearLow = grpYearLow.stream().mapToInt(Car::getYear).max();
        OptionalInt lowYearLow = grpYearLow.stream().mapToInt(Car::getYear).min();

        //get the number of cars for both high and low groups
        int YearHighCount = grpYearHigh.size();
        int YearLowCount = grpYearLow.size();

        //pringting the results of high and low year groups
        System.out.println("High Car Year Group: ");
        System.out.println("Average Year: " + avgYearHigh);
        System.out.println("High Year: " + highYearHigh);
        System.out.println("Low Year: " + lowYearHigh);
        System.out.println("Number of Cars: " + YearHighCount);

        System.out.println("Low Car Year Group: ");
        System.out.println("Average Year: " + avgYearLow);
        System.out.println("High Year: " + highYearLow);
        System.out.println("Low Year: " + lowYearLow);
        System.out.println("Number of Cars: " + YearLowCount);
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
        List<Car> grpdominationCountsHigh = sortByCardominationCounts.stream().filter(car -> car.getDominationCounts() >= dominationCountsThreshold).collect(toList());
        List<Car> grpdominationCountsLow = sortByCardominationCounts.stream().filter(car -> car.getDominationCounts() < dominationCountsThreshold).collect(toList());

        //get average, highest, lowest values of high group
        OptionalDouble avgdominationCountsHigh = grpdominationCountsHigh.stream().mapToInt(Car::getDominationCounts).average();
        OptionalInt highdominationCountsHigh = grpdominationCountsHigh.stream().mapToInt(Car::getDominationCounts).max();
        OptionalInt lowdominationCountsHigh = grpdominationCountsHigh.stream().mapToInt(Car::getDominationCounts).min();

        //get average, highest, lowest values of low group
        OptionalDouble avgdominationCountsLow = grpdominationCountsLow.stream().mapToInt(Car::getDominationCounts).average();
        OptionalInt highdominationCountsLow = grpdominationCountsLow.stream().mapToInt(Car::getDominationCounts).max();
        OptionalInt lowdominationCountsLow = grpdominationCountsLow.stream().mapToInt(Car::getDominationCounts).min();

        //get the number of cars for both high and low groups
        int dominationCountsHighCount = grpdominationCountsHigh.size();
        int dominationCountsLowCount = grpdominationCountsLow.size();

        //printing the results of high and low domination groups
        System.out.println("High Car dominationCounts Group: ");
        System.out.println("Average dominationCounts: " + avgdominationCountsHigh);
        System.out.println("High dominationCounts: " + highdominationCountsHigh);
        System.out.println("Low dominationCounts: " + lowdominationCountsHigh);
        System.out.println("Number of Cars: " + dominationCountsHighCount);

        System.out.println("Low Car dominationCounts Group: ");
        System.out.println("Average dominationCounts: " + avgdominationCountsLow);
        System.out.println("High dominationCounts: " + highdominationCountsLow);
        System.out.println("Low dominationCounts: " + lowdominationCountsLow);
        System.out.println("Number of Cars: " + dominationCountsLowCount);

    }
}
