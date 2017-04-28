package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Chromosome;
import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;

import java.util.List;
import java.util.Random;


public class BasicMutator<G extends Gene<?, G>> implements Mutator<G> {
    protected Random rand = new Random();

    protected float mutatorRatio;

    public BasicMutator(float ratio) {
        this.mutatorRatio = ratio;
    }

    public BasicMutator() {
        this(0.999f);
    }

    @Override
    public void mutate(Phenotype<G> phenotype) {
        List<Chromosome<G>> chromosomes = phenotype.getGenotype().getChromosomes();
        for (int i = 0; i < chromosomes.size(); i++) {
            List<G> genes = chromosomes.get(i).getGenes();
            for (int j = 0; j < genes.size(); j++) {
                if (rand.nextFloat() > mutatorRatio) {
                    genes.get(j).mutate();
                }
            }
        }
    }

    @Override
    public float getMutationRate() {
        return mutatorRatio;
    }

    public void setMutationRate(float mutatorRatio) {
        this.mutatorRatio = mutatorRatio;
    }

    @Override
    public void update(Population<G> population) {
    }
}