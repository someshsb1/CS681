package edu.umb.cs681.hw18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommunityCenterPools {

    //parsing the community_center_pools.csv file using below function provided in slide 2 note07
    public static void main(String args[]) {
    Path path = Paths.get("Community_Center_Pools.csv");

        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> community_center_pools =
                                                        lines.map(line ->  {
                                                        return Stream.of(line.split(","))
                                                        .parallel()
                                                        .map(value -> value.substring(0, Math.min(value.length(), 29)))
                                                        .collect(Collectors.toList());
                                                        })
                                                        .collect(Collectors.toList());
            System.out.println("Community Center Pools Parsed data: ");
            System.out.println("");
            for (List<String> communityPools: community_center_pools) {
                System.out.println(communityPools);
            }
            System.out.println("");

            //filtered community center pools to display the result for pools in the roxbury area only.
            System.out.println("Community Center Pools in the Roxbury Area: ");
            System.out.println("");
            community_center_pools.stream()
            .parallel()
            .filter(pools -> pools.get(6).contains("Roxbury"))
            .forEach(System.out::println);
            System.out.println("");

            //sorted community center pools by their name.
            System.out.println("Community Center Pools Sorted by Pool Center Name: ");
            System.out.println("");
            System.out.println(community_center_pools.get(0)); //printing the header row as is
            community_center_pools.stream()
            .parallel()
            .skip(1) //preventing the header row sorting in the sorted data result.
            .sorted(Comparator.comparing(pools -> pools.get(8)))
            .forEach(System.out::println);

            System.out.println("");

            //count of community center pools in the dorchester area only.
            long totalPools = community_center_pools.stream()
            .parallel()
            .filter(pools -> pools.get(6).contains("Dorchester"))
            .count();
            System.out.println("Total Community Center Pools in Dorchester: " + totalPools);

        } catch (IOException ex) {
            System.out.println("Error parsing the file: " + ex.getMessage());
        }
    }
}
