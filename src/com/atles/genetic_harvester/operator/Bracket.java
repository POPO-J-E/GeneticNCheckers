package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bracket<G extends Gene<?, G>> {
    private int level;
    private Population<G> starting_population;
    private Population<G> winner_population;
    private List<Match<G>> matches;
    private Matcher<G> matcher;

    public Bracket(Matcher<G> m) {
        this.matcher = m;
        this.level = 0;
    }

    public Bracket(Matcher<G> m, int level) {
        this.matcher = m;
        this.level = level;
    }

    public Bracket(Matcher<G> m, int level, Population<G> population) {
        this.matcher = m;
        this.level = level;
        setConcurents(population);
    }

    public void setConcurents(Population<G> population) {
        this.starting_population = population;
        this.winner_population = new Population<G>(population.getSize());
        matches = new ArrayList<Match<G>>();
    }

    public void begin() {
        initMatches();
        beginMatches();
        endMatches();
    }

    private void initMatches() {
        starting_population.shuffle();
        Iterator<Phenotype<G>> i = starting_population.getPopulation().iterator();
        while (i.hasNext()) {
            Phenotype<G> p1 = i.next();
            if (i.hasNext()) {
                Phenotype<G> p2 = i.next();
                Match<G> match = new Match<G>(p1, p2);
                matches.add(match);
            } else {
                winner_population.addPhenotype(p1);
            }
        }
    }

    private void beginMatches() {
        //this.render();
        Iterator<Match<G>> i = matches.iterator();
        while (i.hasNext()) {
            Match<G> match = i.next();
            doMatch(match);
            applyMatch(match);
        }
    }

    private void doMatch(Match<G> match) {
        matcher.match(match);
    }

    private void applyMatch(Match<G> match) {
        this.addToWinnerPopulation(match.getWinner());
        this.setLooserFitness(match.getLooser());
    }

    private void endMatches() {
        if (winner_population.getSize() == 1) {
            setWinnerFitness(winner_population.getPopulation().get(0));
        } else {
            goToNextBraket();
        }
    }

    private void goToNextBraket() {
        Bracket<G> next = new Bracket<G>(this.matcher, level + 1, winner_population);
        next.begin();
    }

    private void addToWinnerPopulation(Phenotype<G> winner) {
        winner_population.addPhenotype(winner);
    }

    private void setWinnerFitness(Phenotype<G> winner) {
        winner.setFitness(level + 1);
        //System.out.println("Bracket winner :");
        winner.render();
    }

    private void setLooserFitness(Phenotype<G> looser) {
        looser.setFitness(level);
    }

    public int getLevel() {
        return level;
    }

    public Population<G> getWinnerPopulation() {
        return winner_population;
    }

    public void render() {
        System.out.println("Bracket : " + level);
        this.starting_population.render();
    }

}
