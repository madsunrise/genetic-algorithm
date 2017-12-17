package com.rudnev;

import java.util.*;

/**
 * Created by ivan on 17.12.2017.
 */
class GeneticUtils {
    private static final double MUTATIONS_PROBABILITY = 0.1;

    private static final double pBest = 0.2;
    private static final double ALIVE_PROPOTION = 0.5;

    GeneticUtils(List<List<Integer>> graph) {
        this.graph = graph;
    }

    private void printPopulation() {
        for (Chromosome chromosome : population) {
            System.out.println(chromosome.getGens());
        }
    }

    void runGeneticAlgorithm(
            int iterations,
            int populationCount,
            int sender,
            int receiver) {
        for (int i = 0; i < iterations; ++i) {
            if (population.isEmpty()) {
                population = createInitialPopulation(populationCount, graph.size(), sender, receiver);
            } else {
                population = rouletteSelection(population);
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
    }

    private List<Chromosome> createInitialPopulation(int count, int computersCount, int sender, int receiver) {
        List<Chromosome> result = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            result.add(new Chromosome(computersCount, sender, receiver));
        }
        return result;
    }


    private List<Chromosome> rouletteSelection(List<Chromosome> oldPopulation) {
        int bestCount = (int) (pBest * oldPopulation.size());
        oldPopulation.sort(Comparator.comparingInt(a -> a.getValue(graph)));

        List<Chromosome> result = new ArrayList<>(oldPopulation.subList(0, bestCount));
        while (result.size() <= (int) (ALIVE_PROPOTION * oldPopulation.size())) {
            int sumFit = 0;
            for (Chromosome chromosome : oldPopulation) {
                sumFit += chromosome.getValue(graph);
            }

            int rouletPos = random.nextInt(sumFit);
            int rouletPosForMember = oldPopulation.get(0).getValue(graph);

            int removeAt = -1;
            for (int i = 0; i < oldPopulation.size(); ++i) {
                Chromosome chromosome = oldPopulation.get(i);
                if (rouletPos < rouletPosForMember) {
                    result.add(chromosome);
                    removeAt = i;
                    break;
                }
                rouletPosForMember += chromosome.getValue(graph);
            }
            if (removeAt != -1) oldPopulation.remove(removeAt);
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
