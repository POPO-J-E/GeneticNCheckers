package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Couple;
import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;

import java.util.Iterator;
import java.util.Random;


public class WheelSelector<G extends Gene<?, G>> implements Selector<G> {
    private static Random rand = new Random();

    public Phenotype<G> selectOne(Population<G> population) throws Exception {
        float total_fitness = population.getTotalFitness();
        double value = randUniformPositive() * total_fitness;

        Iterator<Phenotype<G>> i = population.getPopulation().iterator();
        while (i.hasNext()) {
            Phenotype<G> p = i.next();
            value -= p.getFitness();
            if (value <= 0) {
                return p;
            }
        }

        throw new Exception("cant choose phenotype in population");
    }

    public Couple<G> select(Population<G> population) throws Exception {
        Phenotype<G> p1 = selectOne(population);
        Phenotype<G> p2;
        do {
            p2 = selectOne(population);
        } while (p1 == p2);

        return new Couple<G>(p1, p2);
    }

    private double randUniformPositive() {
        return rand.nextDouble();
    }

}
