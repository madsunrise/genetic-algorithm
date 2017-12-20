package com.rudnev;

import java.util.*;

/**
 * Created by ivan on 17.12.2017.
 */
class GeneticUtils {
    private static final double MUTATIONS_PROBABILITY = 0.1;

    private static final double ALIVE_PROPOTION = 0.5;

    GeneticUtils(List<List<Integer>> graph) {
        this.graph = graph;
    }

    void runGeneticAlgorithm(int iterations, int populationCount, int sender, int receiver) {
        for (int i = 0; i < iterations; ++i) {
            runIteration(populationCount, sender, receiver);
        }
    }

    void runIteration(int populationCount, int sender, int receiver) {
        if (population.isEmpty()) {
            population = createInitialPopulation(populationCount, graph.size(), sender, receiver);
        } else {
            population = tournamentSelection(population);
        }

        reproduction(populationCount);
        createMutations();

        int max = 0;
        for (Chromosome chromosome : population) {
            int value = chromosome.getValue(graph);
            if (max < value) {
                max = value;
            }
        }

        System.out.println("Population throughput: " + max);
    }

    private List<Chromosome> createInitialPopulation(int count, int computersCount, int sender, int receiver) {
        List<Chromosome> result = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            result.add(new Chromosome(computersCount, sender, receiver));
        }
        return result;
    }


    private List<Chromosome> tournamentSelection(List<Chromosome> oldPopulation) {
        int resultSize = (int) (oldPopulation.size() * ALIVE_PROPOTION);
        List<Chromosome> result = new ArrayList<>();
        for (int i = 0; i < resultSize; ++i) {
            Chromosome a = oldPopulation.get(random.nextInt(oldPopulation.size()));
            Chromosome b = oldPopulation.get(random.nextInt(oldPopulation.size()));
            if (a.getValue(graph) < b.getValue(graph)) {
                result.add(a);
            }
            else {
                result.add(b);
            }
        }
        return result;
    }

    private void reproduction(int populationCount) {
        System.out.println("Reproduction...");
        while (population.size() < populationCount) {
            population.add(cross(
                    population.get(random.nextInt(population.size())),
                    population.get(random.nextInt(population.size()))
                    )
            );
        }
    }

    private Chromosome cross(Chromosome a, Chromosome b) {
        Chromosome result = new Chromosome(a.getGens().size());
        for (int i = 0; i < a.getGens().size(); ++i) {
            int gen = random.nextBoolean() ? a.getGens().get(i) : b.getGens().get(i);
            result.getGens().add(gen);
        }
        return result;
    }


    private void createMutations() {
        System.out.println("Performing mutations...");
        for (Chromosome chromosome : population) {
            if (random.nextDouble() < MUTATIONS_PROBABILITY) {
                chromosome.mutate();
            }
        }
    }

    private final Random random = new Random();
    private final List<List<Integer>> graph;
    private List<Chromosome> population = new ArrayList<>();
}
