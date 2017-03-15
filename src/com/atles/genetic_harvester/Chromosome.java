package com.atles.genetic_harvester;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chromosome<G extends Gene<?, G>> implements Factory<Chromosome<G>>, Serializable {
    private static final long serialVersionUID = 1L;

    private final static Random rand = new Random();

    private final int length;
    private List<G> genes;

    public Chromosome(G[] genes) {
        this(new ArrayList<>(Arrays.asList(genes)));
    }

    public Chromosome(List<G> genes) {
        this.genes = genes;
        this.length = this.genes.size();
    }

    public int getLength() {
        return length;
    }

    public List<G> getGenes() {
        return genes;
    }

    public void setGenes(List<G> genes) {
        this.genes = genes;
    }

    @Override
    public Chromosome<G> newInstance() {
        List<G> genes = blankCopy();
        for (int i = 0; i < length; i++) {
            genes.add(this.genes.get(i).newInstance());
        }
        return new Chromosome<G>(genes);
    }

    public Chromosome<G> crossover(Chromosome<G> chromosome) {
        int pivaut = rand.nextInt(length);
        List<G> genes = blankCopy();
        for (int i = 0; i < length; i++) {
            if (i < pivaut) {
                genes.add(this.genes.get(i).newInstance());
            } else {
                genes.add(chromosome.getGenes().get(i).newInstance());
            }
        }

        return new Chromosome<G>(genes);
    }

    public List<G> blankCopy() {
        return new ArrayList<G>(length);
    }

    public void render() {
        String s = "Chromosome : {";
        for (int i = 0; i < length; i++) {
            s += genes.get(i).getAllele().toString();
            if (i != length - 1) {
                s += " ,";
            } else {
                s += "};";
            }
        }
        System.out.println(s);
    }
}
