package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.Chromosome;
import com.atles.genetic_harvester.Gene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;

import java.util.List;
import java.util.Random;


public class VariableMutator<G extends Gene<?, G>> extends BasicMutator<G> {
    private float step;
    private int repeat;
    private final float originalRatio;
    private int nbrRepeat;
    private float lastFitness;

    public VariableMutator(float ratio, float step, int repeat) {
        super(ratio);
        this.originalRatio = ratio;
        this.step = step;
        this.repeat = repeat;
        nbrRepeat = 0;
    }

    public VariableMutator(float ratio) {
        this(ratio, 0.1f, 5);
    }

    public VariableMutator() {
        this(0.999f);
    }

    public void increase()
    {
        this.mutatorRatio -= step;
    }

    public void reset()
    {
        this.mutatorRatio = originalRatio;
        this.nbrRepeat = 0;
    }

    public void update(Population<G> population)
    {
        if(population.getBestFitness() == this.lastFitness)
        {
            nbrRepeat++;

            if(nbrRepeat >= repeat)
            {
                this.increase();
            }
        }
        else
        {
            this.reset();
        }

        this.lastFitness = population.getBestFitness();
    }
}