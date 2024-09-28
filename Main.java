package Java_Core.MultiThreading.Parallel_Merge_Sort;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>(10000000);
        for(int i=0;i<10000000;i++){
            list.add(new Random().nextInt(10000000));
        }

        System.out.println("Sequential Merge Sort VS Parallel Merge Sort");
        System.out.println("We are performing test to Sort 1 crore Elements ");
        System.out.println("Press 1 to sort with Sequential Merge Sort");
        System.out.println("Press 2 to sort with Parallel Merge Sort");
        System.out.println("Press 3 for exit");
        int n=new Scanner(System.in).nextInt();
        switch (n) {
            case 1 -> {
                SequentialMergeSort sequentialMergeSort = new SequentialMergeSort(list);
                Instant start = Instant.now();
                sequentialMergeSort.sort();
                Instant end = Instant.now();
                Duration duration = Duration.between(start, end);
                System.out.println("Time taken by Sequential Merge Sort is : " + duration.getSeconds());
            }
            case 2 -> {
                Parallel_MergeSort parallel_mergeSort = new Parallel_MergeSort(list);
                Instant start1 = Instant.now();
                parallel_mergeSort.sort();
                Instant end1 = Instant.now();
                Duration duration1 = Duration.between(start1, end1);
                System.out.println("Time taken by Parallel Merge Sort is : " + duration1.getSeconds());
            }
            default -> System.out.println("Thanks for using our Program ::");
        }
    }
}
