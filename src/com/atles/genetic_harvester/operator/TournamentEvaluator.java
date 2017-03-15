package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Population;

public class TournamentEvaluator<G extends Gene<?, G>> implements Evaluator<G> {

    private Matcher<G> matcher;

    public TournamentEvaluator(Matcher<G> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void evaluate(Population<G> population) {
        Bracket<G> bracket = new Bracket<G>(matcher);
        bracket.setConcurents(population);
        bracket.begin();
    }

}
