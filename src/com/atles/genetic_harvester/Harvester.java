package com.atles.genetic_harvester;

import com.atles.genetic_harvester.operator.Evaluator;
import com.atles.genetic_harvester.operator.Mutator;
import com.atles.genetic_harvester.operator.Reproductor;
import com.atles.genetic_harvester.operator.Selector;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public abstract class Harvester<G extends Gene<?, G>> {
    private Population<G> population;
    private Phenotype<G> bestPhenotype;
    private int size;

    private Selector<G> selector;
    private Evaluator<G> evaluator;
    private Reproductor<G> reproductor;
    private Mutator<G> mutator;

    private Factory<Phenotype<G>> phenotypeFactory;

    private boolean firstGen = true;

    public Harvester(Factory<Phenotype<G>> phenotypeFactory, int size) {
        this.size = size;
        this.phenotypeFactory = phenotypeFactory;
        generateFirstGeneration();
    }

    public Harvester<G> setSelector(Selector<G> selector) {
        this.selector = selector;
        return this;
    }

    public Harvester<G> setEvaluator(Evaluator<G> evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public Harvester<G> setReproductor(Reproductor<G> reproductor) {
        this.reproductor = reproductor;
        return this;
    }

    public Harvester<G> setMutator(Mutator<G> mutator) {
        this.mutator = mutator;
        return this;
    }

    public void generateFirstGeneration() {
        population = new Population<G>();
        for (int i = 0; i < size; i++) {
            population.addPhenotype(generateTypePhenotype());
        }
    }

    public Population<G> getPopulation() {
        return population;
    }

    public void setPopulation(Population<G> population) {
        this.population = population;
    }

    private Phenotype<G> generateTypePhenotype() {
        return phenotypeFactory.newInstance();
    }

    public void goToNextGeneration() {
        if (!this.firstGen) {
            reproduce();
            mutate();
        } else
            this.firstGen = false;
        System.out.println("Go to generation : " + this.getPopulation().getGeneration());
        evaluator.evaluate(population);
        population.calculTotalFitness();
        updateOperators();
        if(this.bestPhenotype == null || this.bestPhenotype.getFitness() < this.population.getBestPhenotype().getFitness())
            this.bestPhenotype = this.population.getBestPhenotype();

        System.out.println("Best fitness : " + this.getPopulation().getBestPhenotype().getFitness());
        System.out.println("Average fitness : " + this.getPopulation().getTotalFitness() / this.getPopulation().getSize());
        System.out.println("Best phenotype : ");
        this.getPopulation().getBestPhenotype().render();
        System.out.flush();
    }

    public void reproduce() {
        population.getPopulation().sort(new Comparator<Phenotype<G>>() {
            @Override
            public int compare(Phenotype<G> phenotype, Phenotype<G> phenotype_bis) {
                return Float.compare(phenotype_bis.getFitness(), phenotype.getFitness());
            }
        });

        Population<G> nextGen = new Population<>(population.getGeneration() + 1);

        while (nextGen.getSize() < this.size) {

            try {
                Couple<G> parents = selector.select(population);
                Couple<G> children = reproductor.reproduce(parents);
                nextGen.addPhenotype(children.getP1());
                nextGen.addPhenotype(children.getP2());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        while (nextGen.getSize() < this.size * 0.95) {
            nextGen.addPhenotype(this.generateTypePhenotype());
        }

        //add best phenotypes with ratio
        int i = 0;
        while (nextGen.getSize() < this.size) {
            nextGen.addPhenotype(population.getPopulation().get(i).newInstance().canMutate(true));
            i++;
        }

        this.population = nextGen;
    }

    public void mutate() {
        for (Phenotype<G> phenotype : this.population.getPopulation()) {
            if(phenotype.canMutate())
                mutator.mutate(phenotype);
        }
    }

    public void goToXGeneration(int x) {
        while (this.population.getGeneration() < x && !this.solutionFinded()) {
            goToNextGeneration();
        }
    }

    public void updateOperators() {
        this.mutator.update(this.population);
        this.evaluator.update(this.population);
    }

    protected boolean solutionFinded()
    {
        return false;
    }

    public Phenotype<G> getBestPhenotype() {
        return bestPhenotype;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Selector<G> getSelector() {
        return selector;
    }

    public Evaluator<G> getEvaluator() {
        return evaluator;
    }

    public Reproductor<G> getReproductor() {
        return reproductor;
    }

    public Mutator<G> getMutator() {
        return mutator;
    }

    public Factory<Phenotype<G>> getPhenotypeFactory() {
        return phenotypeFactory;
    }

    public void setPhenotypeFactory(Factory<Phenotype<G>> phenotypeFactory) {
        this.phenotypeFactory = phenotypeFactory;
    }
}
