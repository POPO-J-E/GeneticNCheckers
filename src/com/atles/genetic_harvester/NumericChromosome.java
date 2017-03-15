package com.atles.genetic_harvester;

import java.util.List;

public class NumericChromosome extends Chromosome<NumericGene> {

    public NumericChromosome(NumericGene[] genes) {
        super(genes);
    }
    public NumericChromosome(List<NumericGene> genes) {
        super(genes);
    }
}
