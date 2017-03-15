package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;

public class Match<G extends Gene<?, G>> {

    private Phenotype<G> challenger_one;
    private Phenotype<G> challenger_two;

    private Phenotype<G> challenger_winner;
    private Phenotype<G> challenger_looser;

    public Match(Phenotype<G> co, Phenotype<G> ct) {
        this.challenger_one = (co);
        this.challenger_two = (ct);
    }

    public Phenotype<G> getWinner() {
        return challenger_winner;
    }

    public Phenotype<G> getLooser() {
        return challenger_looser;
    }

    public Phenotype<G> getChallenger_one() {
        return challenger_one;
    }

    public Phenotype<G> getChallenger_two() {
        return challenger_two;
    }

    public void setChallengerOneWinner(boolean b) {
        if (b) {
            challenger_winner = challenger_one;
            challenger_looser = challenger_two;
        } else {
            challenger_winner = challenger_two;
            challenger_looser = challenger_one;
        }
    }
}
