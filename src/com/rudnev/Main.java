package com.rudnev;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        test();
//        Scanner input = new Scanner(System.in);
//
//        System.out.print("Computers count: ");
//        int computersCount = input.nextInt();
//
//        System.out.print("Sender number: ");
//        int senderNumber = input.nextInt();
//
//        System.out.print("Receiver number: ");
//        int receiverNumber = input.nextInt();
//
//        System.out.print("Population size: ");
//        int populationSize = input.nextInt();
//
//        System.out.print("Enable automatic mode (y/n)? ");
//        boolean automaticMode = input.next().equalsIgnoreCase("y");
//
//        if (automaticMode) {
//            System.out.print("Iterations count: ");
//            int iterationsCount = input.nextInt();
//
//        } else {
//            // while loop
//        }

    }


    public static void test() {
        List<List<Integer>> graph = Arrays.asList(
                Arrays.asList(100000, 90, 44, 81, 32, 16, 56, 27, 61, 63, 89, 16, 104, 109, 69, 43, 31, 68, 36, 86),
                Arrays.asList(27, 100000, 90, 20, 12, 51, 63, 51, 43, 10, 20, 33, 12, 62, 27, 101, 105, 82, 21, 31),
                Arrays.asList(19, 83, 100000, 30, 39, 94, 56, 104, 23, 53, 107, 37, 77, 76, 21, 42, 15, 68, 108, 23),
                Arrays.asList(55, 108, 15, 100000, 60, 33, 32, 61, 37, 89, 41, 79, 91, 109, 65, 73, 89, 73, 107, 39),
                Arrays.asList(81, 84, 72, 93, 100000, 65, 50, 104, 17, 28, 13, 108, 40, 34, 42, 46, 42, 92, 100, 75),
                Arrays.asList(73, 73, 26, 54, 67, 100000, 54, 107, 99, 17, 52, 45, 87, 18, 49, 52, 49, 83, 26, 73),
                Arrays.asList(20, 13, 44, 27, 83, 34, 100000, 82, 30, 65, 97, 89, 70, 95, 79, 21, 37, 36, 89, 105),
                Arrays.asList(63, 81, 73, 81, 93, 77, 88, 100000, 29, 39, 61, 75, 55, 101, 90, 71, 35, 54, 71, 30),
                Arrays.asList(24, 62, 77, 78, 97, 56, 90, 86, 100000, 11, 69, 69, 32, 70, 24, 78, 47, 89, 14, 90),
                Arrays.asList(89, 68, 46, 55, 108, 38, 74, 106, 25, 100000, 42, 26, 60, 95, 56, 11, 77, 77, 86, 31),
                Arrays.asList(104, 43, 52, 13, 73, 78, 107, 25, 109, 90, 100000, 10, 69, 99, 18, 69, 82, 84, 95, 40),
                Arrays.asList(30, 29, 15, 48, 26, 23, 77, 101, 106, 74, 73, 100000, 56, 103, 84, 97, 34, 64, 88, 59),
                Arrays.asList(45, 80, 79, 99, 53, 101, 79, 73, 16, 87, 106, 76, 100000, 58, 18, 43, 86, 40, 73, 95),
                Arrays.asList(106, 87, 52, 74, 38, 66, 102, 105, 49, 14, 38, 10, 35, 100000, 28, 49, 27, 14, 36, 100),
                Arrays.asList(80, 101, 105, 94, 61, 90, 63, 38, 20, 67, 15, 49, 99, 71, 100000, 49, 49, 99, 65, 26),
                Arrays.asList(68, 55, 76, 99, 102, 103, 58, 29, 10, 104, 17, 22, 17, 80, 109, 100000, 27, 90, 77, 85),
                Arrays.asList(31, 38, 23, 69, 67, 48, 108, 101, 90, 20, 43, 81, 81, 76, 69, 70, 100000, 22, 88, 34),
                Arrays.asList(102, 48, 89, 49, 36, 29, 79, 15, 53, 12, 73, 41, 89, 98, 17, 74, 12, 100000, 86, 24),
                Arrays.asList(36, 17, 70, 54, 97, 14, 32, 97, 70, 39, 86, 27, 44, 20, 20, 75, 20, 66, 100000, 82),
                Arrays.asList(73, 61, 108, 22, 99, 57, 84, 14, 92, 87, 90, 66, 61, 61, 14, 32, 101, 98, 47, 100000)
        );

        new GeneticUtils(graph).runGeneticAlgorithm(400, 50, 2, 18);
    }
}
