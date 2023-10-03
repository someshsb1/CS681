
HW 3

A result holder can be a class instance

– as well as a native-type value and an array.
• Compute the average car price with a class instance as a result holder (not an array as a result holder)
– public class CarPriceResultHolder { private int numCarExamined; private double average;
...}


– Your map-reduce code:
• double averagePrice = cars.stream()
.map( car -> car.getPrice() ) .reduce( new CarPriceResultHolder(),
(result, price)->{... ...
return result;}), (finalResult, intermediateResult)->finalResult
).getAverage();