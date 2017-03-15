package com.atles.genetic_harvester;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Genotype<G extends Gene<?, G>> implements Factory<Genotype<G>>, Serializable {
    private static final long serialVersionUID = 1L;

    private final int length;
    private List<Chromosome<G>> chromosomes;

    public Genotype(List<Chromosome<G>> chromosomes) {
        this.chromosomes = chromosomes;
        this.length = this.chromosomes.size();
    }

    public Genotype(Chromosome<G>[] chromosomes) {
        this(Arrays.asList(chromosomes));
    }

    @Override
    public Genotype<G> newInstance() {
        List<Chromosome<G>> chromosomes = new ArrayList<Chromosome<G>>(length);
        for (int i = 0; i < length; i++) {
            chromosomes.add(this.chromosomes.get(i).newInstance());
        }
        return new Genotype<G>(chromosomes);
    }

    public List<Chromosome<G>> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome<G>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Genotype<G> crossover(Genotype<G> genotype) {
        Chromosome[] chromosomes = new Chromosome[length];
        for (int i = 0; i < length; i++) {
            chromosomes[i] = this.chromosomes.get(i).crossover(genotype.chromosomes.get(i));
        }
        return new Genotype<G>(chromosomes);
    }

    public void render() {
        for (int i = 0; i < length; i++) {
            this.chromosomes.get(i).render();
        }
    }
}
