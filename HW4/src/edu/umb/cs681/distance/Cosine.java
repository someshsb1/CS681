package edu.umb.cs681.distance;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements DistanceMetric{

    @Override
    public double distance(List<Double> p1, List<Double> p2) {
        // revised cosine distance using Stream API
        double dp = IntStream.range(0, p1.size()).mapToDouble(i -> p1.get(i) * p2.get(i)).sum();

        double Mp1 = Math.sqrt(IntStream.range(0, p1.size()).mapToDouble(i -> Math.pow(p1.get(i), 2)).sum());
        double Mp2 = Math.sqrt(IntStream.range(0, p2.size()).mapToDouble(i -> Math.pow(p2.get(i), 2)).sum());

        return 1.0 - (dp/(Mp1 * Mp2));
    }

    public static void main(String args[]) {
        Cosine cosine = new Cosine();

        List<Double> p1 = List.of(40.0, 55.0, 75.0);
        List<Double> p2 = List.of(20.0, 25.0, 30.0);

        double distance = cosine.distance(p1, p2);

        System.out.println("Cosine distance b/w point 1 and point 2 is: " + distance);
    }
    
}
