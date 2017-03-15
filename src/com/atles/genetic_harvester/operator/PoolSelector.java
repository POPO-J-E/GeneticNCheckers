package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Couple;
import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class PoolSelector<G extends Gene<?, G>> implements Selector<G> {
    private static Random rand = new Random();

    private Population<G> popultation;
    private List<Phenotype<G>> pool;
    private int poolSize;

    public Phenotype<G> selectOne(Population<G> population) throws Exception {
        if(population != this.popultation)
            generatePool(population);

        return pool.get(rand.nextInt(pool.size()));
    }

    private void generatePool(Population<G> population) {
        this.popultation = population;
        pool = new ArrayList<>();

        for(int i = 0; i < population.getSize(); i++)
        {
            Phenotype<G> phenotype = population.getPopulation().get(i);
            float fitness = phenotype.getFitness() / population.getTotalFitness() * population.getSize();
            int integer_part = (int)fitness;
            float real_part = fitness - integer_part;

            for(int j = 0; j < integer_part; j++)
            {
                pool.add(phenotype);
            }

            if(randUniformPositive() < real_part)
                pool.add(phenotype);
        }
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
