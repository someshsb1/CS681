package edu.umb.cs681.hw13_1;

import java.util.HashMap;
import java.util.Map;

public class MBTASchedule {

    public static class Trains {

        private Map<Integer, String> redLine;
        private Map<Integer, String> orangeLine;
        private Map<Integer, String> blueLine;
        private Map<Integer, String> greenLine;

        public Trains() {
            this.redLine = new HashMap<>();
            this.orangeLine = new HashMap<>();
            this.blueLine = new HashMap<>();
            this.greenLine = new HashMap<>();
        }

        //method to update/add new information of the train - name, platform, arrival time that they are arriving.
        public void trainSchedular(String color, String platform, int arrivalSchedule) {

            switch (color) {
                case "Red":
                            redLine.put(arrivalSchedule, platform);
                case "Orange":
                            orangeLine.put(arrivalSchedule, platform);
                case "Blue":
                            blueLine.put(arrivalSchedule, platform);
                case "Green":
                            greenLine.put(arrivalSchedule, platform);
                break;
                default:
                            System.out.println("No such train.");
            }

        }

        //helper function to print the schedule.
        private void displayScheduleByTrainColor(Map<Integer, String> colorLine) {

            colorLine.forEach((arrivalSchedule, platform) -> {
                System.out.println("Arriving in: " + arrivalSchedule + "min" + " , " + "on platform: "+ platform);
            });
        }

        //method to display the train schedules/arrival time.
        public void displaySchedules(String color) {
            if (color == "Red") {
                System.out.println("Red Line: ");
                    displayScheduleByTrainColor(redLine);
                } else if (color == "Blue") {
                System.out.println("Blue Line: ");
                    displayScheduleByTrainColor(blueLine);
                } else if (color == "Orange") {
                System.out.println("Orange Line: ");
                    displayScheduleByTrainColor(orangeLine);
                } else if (color == "Green") {
                System.out.println("Green Line: ");
                    displayScheduleByTrainColor(greenLine);
                } else {
                    System.out.println("No Schedule.");
            }
        }
    }

    public static void main(String args[]) {

        Trains train = new Trains();

        //below 2 thread simultaneously call the trainSchedular to update the Red line train info which might cause the race conditions as multiple threads are accessing the RedLine train.
        Thread t1 = new Thread(() -> train.trainSchedular("Red", "JFK/UMass - 01", 5));
        t1.start();

        Thread t2 = new Thread(() -> train.trainSchedular("Red", "JFK/UMass - 02", 3));
        t2.start();

        train.displaySchedules("Red"); /* calling the displaySchedule method to print the arrival time of train.
                                                Output will be Inconsistant Information:
                                                Red Line:
                                                Arriving in: 3min , on platform: JFK/UMass - 01
                                                Arriving in: 5min , on platform: JFK/UMass - 01
                                                */

    }
}
