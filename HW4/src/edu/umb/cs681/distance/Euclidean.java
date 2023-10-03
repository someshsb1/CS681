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

		List<Double> p1 = new ArrayList<>();
		List<Double> p2 = new ArrayList<>();

		p1.add((double) 4);
		p2.add((double) 10);

		double distance = euclidean.distance(p1, p2);

		System.out.println("Euclidean distance between p1 and p2 is :" + distance);
	}
}
