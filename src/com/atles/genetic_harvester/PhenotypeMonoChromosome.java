package com.atles.genetic_harvester;

import java.util.ArrayList;

/**
 * Created by kifkif on 09/03/2017.
 */
public class PhenotypeMonoChromosome<G extends Gene<?, G>> extends Phenotype<G> {

    public PhenotypeMonoChromosome(Chromosome<G> chromosome) {
        super(new GenotypeMonoChromosome<G>(chromosome));
    }

    public Chromosome<G> getChromosome()
    {
        return this.getGenotype().getChromosomes().get(0);
    }

    public G getGene(int x) {
        return this.getChromosome().getGenes().get(x);
    }
}
