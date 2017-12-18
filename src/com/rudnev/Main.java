package com.rudnev;

import java.util.*;


public class Main {

    static final int INFINITY = 100000;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Computers count: ");
        int computersCount = input.nextInt();

        System.out.print("Sender number: ");
        int senderNumber = input.nextInt();

        System.out.print("Receiver number: ");
        int receiverNumber = input.nextInt();

        System.out.print("Population size: ");
        int populationCount = input.nextInt();

        System.out.print("Enable automatic mode (y/n)? ");
        boolean automaticMode = input.next().equalsIgnoreCase("y");

        if (automaticMode) {
            System.out.print("Iterations count: ");
            int iterationsCount = input.nextInt();
            new GeneticUtils(generateGraph(computersCount)).runGeneticAlgorithm(iterationsCount, populationCount, senderNumber, receiverNumber);

        } else {
            GeneticUtils gen = new GeneticUtils(generateGraph(computersCount));
            do {
                gen.runIteration(populationCount, senderNumber, receiverNumber);
                System.out.println("Wanna resume (y/n)?");
            } while (input.next().equalsIgnoreCase("y"));
        }

    }


    private static List<List<Integer>> generateGraph(int n) {
        Random random = new Random();
        List<List<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            List<Integer> line = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                int value = i != j ? random.nextInt(100) + 5 : INFINITY;
                line.add(value);
            }
            result.add(line);
        }
        return result;
    }
}
