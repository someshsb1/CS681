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

        int points = 1001;
        int dimensions = 101;

        List<List<Double>> genPoints = generatePoints(points, dimensions);
        List<List<Double>> distance = Distance.matrix(genPoints, manhattan);

        // List<Double> p1 = new ArrayList<>();
        // List<Double> p2 = new ArrayList<>();

        // p1.add((double) 40);
        // p2.add((double) 10);

        // double distance = manhattan.distance(p1, p2);

        System.out.println("Manhattan Distance with 1000+ points that have 100+ dimensions :" + distance);
    }
    //helper method to generate random input for points and dimensions.
    public static List<List<Double>> generatePoints(int points, int dimensions) {
        List<List<Double>> p = new ArrayList<>();
        for (int i = 0; i < points; i++) {
            List<Double> p1 = new ArrayList<>();
            for (int j = 0; j < dimensions; j++) {
                p1.add(Math.random());
            }
            p.add(p1);
        }
        return p;
    }
}
