package com.atles.genetic_harvester;


import java.io.Serializable;

public class Phenotype<G extends Gene<?, G>> implements Factory<Phenotype<G>>, Serializable {
    private static final long serialVersionUID = 1L;
    private Genotype<G> genotype;
    private float fitness;
    private boolean canMutate;

    public Phenotype(Genotype<G> genotype) {
        this.setGenotype(genotype);
        this.fitness = 0;
        this.canMutate = false;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public Genotype<G> getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype<G> genotype) {
        this.genotype = genotype;
    }

    public Phenotype<G> reproduce(Phenotype<G> phenotype) {
        return new Phenotype<G>(this.genotype.crossover(phenotype.getGenotype()));
    }

    public void render() {
        System.out.println("Phenotype : ");
        System.out.println("Fitness : " + this.fitness);
        System.out.println("Genotype : ");
        genotype.render();
    }

    public G getGene(int x, int y) {
        return this.getChromosome(x).getGenes().get(y);
    }

    public Chromosome<G> getChromosome(int x) {
        return this.getGenotype().getChromosomes().get(x);
    }

    @Override
    public Phenotype<G> newInstance() {
        return new Phenotype<G>(this.genotype.newInstance());
    }

    public boolean canMutate() {
        return canMutate;
    }

    public Phenotype<G> canMutate(boolean canMutate) {
        this.canMutate = canMutate;
        return this;
    }
}
