package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;

public interface Matcher<G extends Gene<?, G>> {

    public void match(Match<G> match);
}
