package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;

public interface Mutator<G extends Gene<?, G>> extends Operator<G>{

    public void mutate(Phenotype<G> phenotype);
}
