package edu.umb.cs681.distance;

import java.util.List;

public interface DistanceMetric {
	 public abstract double distance(List<Double> p1, List<Double> p2);
}
