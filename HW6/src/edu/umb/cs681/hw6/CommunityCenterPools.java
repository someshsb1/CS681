package edu.umb.cs681.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommunityCenterPools {

    public static void main(String args[]) {
        //parsing the community_center_pools.csv file using below function provided in the hw slide
        Path path = Paths.get("Community_Center_Pools.csv");

        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> community_center_pools =
                                                        lines.map(line -> {
                                                            return Stream.of(line.split(","))
                                                            .map(value -> value.substring(0, Math.min(value.length(), 29)))
                                                            .collect(Collectors.toList());
                                                        })
                                                        .collect(Collectors.toList());
            System.out.println("Community Center Pools Parsed Data: ");
            System.out.println("");
            for (List<String> communityPools: community_center_pools) {
                System.out.println(communityPools);
            }
            System.out.println("");

            //data processing filter using thread 1 to display the results only for community pools in the roxbury area.
            Thread t1 = new Thread(() -> {
                System.out.println("Filter Community Center Pools in the Roxbury Area: ");
                System.out.println("");
                community_center_pools.stream()
                .filter(pools -> pools.get(6).contains("Roxbury"))
                .forEach(System.out::println);
                System.out.println("");
            });
            t1.start();

            //data processing sort using thread 2 to display the results of community pools sorted by name.
            Thread t2 = new Thread(() -> {
                System.out.println("Community Center Pools Sorted by Pool Center Name: ");
                System.out.println("");
                System.out.println(community_center_pools.get(0));
                community_center_pools.stream()
                .skip(1)
                .sorted(Comparator.comparing(pools -> pools.get(8)))
                .forEach(System.out::println);
                System.out.println("");
            });
            t2.start();

            //data processing count using thread 3 to display the total number of pools in the dorchester area.
            Thread t3 = new Thread(() -> {
                long totalPools = community_center_pools.stream()
                                    .filter(pools -> pools.get(6).contains("Dorchester"))
                                    .count();
                                    System.out.println("Total Community Center Pools in the Dorchester Area: " + totalPools);
            });
            t3.start();

            //wait for 3 data processing threads to be terminated with join()
            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException ex) {
                System.out.println("Error message: Thread Interrupted " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error parsing the file: " + ex.getMessage());
        }
    }
}
