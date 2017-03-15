package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Population;

public interface Evaluator<G extends Gene<?, G>> {

    public void evaluate(Population<G> population);
}
