package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Couple;
import com.atles.genetic_harvester.Gene;


public interface Reproductor<G extends Gene<?, G>> {
    public Couple<G> reproduce(Couple<G> couple);

}
