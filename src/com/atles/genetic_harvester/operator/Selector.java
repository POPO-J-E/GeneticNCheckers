package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Couple;
import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Population;

public interface Selector<G extends Gene<?, G>> {
    public Couple<G> select(Population<G> population) throws Exception;
}
