package edu.umb.cs681.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Manhattan implements DistanceMetric {

    public double distance(List<Double> p1, List<Double> p2) {
        // revised Manhattan distance using Stream API
        double sumOfSquared = IntStream.range(0, p1.size()).mapToDouble(i -> Math.abs(p1.get(i) - p2.get(i))).sum();
        return sumOfSquared;
    }

    public static void main(String argsp[]) {
        Manhattan manhattan = new Manhattan();

        List<Double> p1 = new ArrayList<>();
        List<Double> p2 = new ArrayList<>();

        p1.add((double) 40);
        p2.add((double) 10);

        double distance = manhattan.distance(p1, p2);

        System.out.println("Manhattan Distance b/w p1 and p2 is :" + distance);
    }
}
