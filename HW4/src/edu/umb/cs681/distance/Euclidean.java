package edu.umb.cs681.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Euclidean implements DistanceMetric{
	public double distance(List<Double> p1, List<Double> p2) {
        // revised Euclidean distance using Stream API
		double sumOfSquared = IntStream.range(0, p1.size()).mapToDouble(i -> p1.get(i) - p2.get(i)).map(n -> n*n).sum();
		return Math.sqrt(sumOfSquared);
			
	}

	public static void main(String args[]) {

		Euclidean euclidean = new Euclidean();

		// List<Double> p1 = new ArrayList<>();
		// List<Double> p2 = new ArrayList<>();

		// p1.add((double) 4);
		// p2.add((double) 10);

		// double distance = euclidean.distance(p1, p2);
		
		int points = 1001;
		int dimensions = 101;

		List<List<Double>> genPoints = generatePoints(points, dimensions);
		List<List<Double>> distance = Distance.matrix(genPoints, euclidean);

		System.out.println("Euclidean distance with 1000+ points that have 100+ dimensions for each point is :" + distance);

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
