package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Population;

/**
 * Created by kifkif on 13/03/2017.
 */
public interface Operator<G extends Gene<?, G>>  {
    public void update(Population<G> population);
}
