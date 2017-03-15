package com.atles.genetic_harvester;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kifkif on 09/03/2017.
 */
public class GenotypeMonoChromosome<G extends Gene<?, G>> extends Genotype<G> {

    public GenotypeMonoChromosome(Chromosome<G> chromosome) {
        super(new ArrayList<>(Arrays.asList(chromosome)));
    }

    public Chromosome<G> getChromosome()
    {
        return this.getChromosomes().get(0);
    }
}
