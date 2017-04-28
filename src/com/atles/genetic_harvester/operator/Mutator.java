package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;

public interface Mutator<G extends Gene<?, G>> extends Operator<G>{

    void mutate(Phenotype<G> phenotype);

    float getMutationRate();

    void setMutationRate(float mutatorRatio);
}
