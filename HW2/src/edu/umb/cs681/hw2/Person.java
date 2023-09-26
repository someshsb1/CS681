package edu.umb.cs681.hw2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LinkedList<Dose> doses;

    public Person (String firstName, String lastName, LocalDate dob, LinkedList<Dose> doses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doses = doses;
    }

    public int getAge() {
        LocalDate todaysDate = LocalDate.now();
        return todaysDate.getYear() - dob.getYear();
    }

    public AgeCat getAgeCat() {
        int age = getAge();
        if (age >= 60) {
            return AgeCat.OLD;
        } else if (age >=30) {
            return AgeCat.MID;
        } else 
            return AgeCat.YOUNG;
        }

    public LinkedList<Dose> getDoses() {
        return doses;
    }

    public int getVacCount() {
        return doses.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private static List<Person> genRandomPerson(int instances) {
        List<Person> person = new ArrayList<>();
        Random random = new Random();

        for (int i =0; i < instances; i++) {
           String firstname = "A" + i;
           String lastName = "B" + i;
           LocalDate dob = LocalDate.of(ThreadLocalRandom.current().nextInt(1940, 2023),
                                        ThreadLocalRandom.current().nextInt(1, 13),
                                        ThreadLocalRandom.current().nextInt(1, 29));
        int doseCount = random.nextInt(5);
        LinkedList<Dose> doses = new LinkedList<>();

        for (int j = 0; j < doseCount; j++) {
            Dose dose = new Dose("Vaccine: " + j, "Lot: " + j, LocalDate.now(), "Vaccine Site: " + j);
            doses.add(dose);
        }

        Person p = new Person(firstname, lastName, dob, doses);
        person.add(p);
        }
        return person;
    }

    public static void main (String args[]) {
        List<Person> person = genRandomPerson(1100);

        //Calculate the vaccination rate for each age category.
        Map<AgeCat, Long> vacRateByAgeCat = person.stream().collect(Collectors.groupingBy(Person::getAgeCat, Collectors.counting()));
        System.out.println(" ");
        System.out.println("Vaccination rate for each category: " + vacRateByAgeCat);

        //Calculate the average # of vaccinations administered in each age category.
        Map<AgeCat, Double> avgVacRateByAgeCat = person.stream().collect(Collectors.groupingBy(Person::getAgeCat, Collectors.averagingDouble(Person::getVacCount)));
        System.out.println(" ");
        System.out.println("Average # of vaccinations administered in each age category: " + avgVacRateByAgeCat);

        //Calculate the average age of the people who have never been vaccinated and print results.
        Map<Boolean, Double> avgAgeNotVaccinated = person.stream().collect(Collectors.partitioningBy(p -> p.getVacCount() == 0, Collectors.averagingDouble(Person::getAge)));
        System.out.println(" ");
        System.out.println("Average age of people never vaccinated: " + avgAgeNotVaccinated.get(true));
    }
}
