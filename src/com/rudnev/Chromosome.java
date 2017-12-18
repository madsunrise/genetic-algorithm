package com.rudnev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.rudnev.Main.INFINITY;

/**
 * Created by ivan on 17.12.2017.
 */
class Chromosome {
    private final List<Integer> gens;
    private final Random random = new Random();

    Chromosome(int gensCount) {
        gens = new ArrayList<>(gensCount);
    }

    Chromosome(int gensCount, int sender, int receiver) {
        gens = new ArrayList<>(gensCount);
        for (int i = 0; i < gensCount; i++) {
            gens.add(i);
        }
        if (sender > 1) {
            swapGens(0, sender - 1);
        }
        if (receiver > 0 && receiver < gensCount) {
            swapGens(receiver - 1, gensCount - 1);
        }
        for (int i = 1; i < gensCount - 1; ++i) {
            int idx = random.nextInt(gensCount - 3) + 1;
            swapGens(idx, i);
        }
    }

    void mutate() {
        gens.set(1 + random.nextInt(gens.size() - 3), random.nextInt(gens.size() - 1));
    }

    int getValue(List<List<Integer>> graph) {
        int value = INFINITY;
        for (int i = 0; i < gens.size() - 1; ++i) {
            int temp = graph.get(gens.get(i)).get(gens.get(i + 1));
            if (temp < value) {
                value = temp;
            }
        }
        return value;
    }

    List<Integer> getGens() {
        return gens;
    }

    private void swapGens(int i, int j) {
        if (i == j) return;
        int temp = gens.get(i);
        gens.set(i, gens.get(j));
        gens.set(j, temp);
    }
}
