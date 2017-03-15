package com.polytech.ndames;

import com.atles.genetic_harvester.Factory;
import com.atles.genetic_harvester.Harvester;
import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.operator.*;

/**
 * Created by kifkif on 09/03/2017.
 */
public class DameHarvester extends Harvester<NumericGene>{
    public DameHarvester(Factory<Phenotype<NumericGene>> phenotypeFactory, int size) {
        super(phenotypeFactory, size);

        this.setEvaluator(new DameEvaluatorV3()); // evaluation du score moyen contre 50 ia random
        this.setSelector(new PoolSelector<>()); // selection des individus par "roue"
        this.setMutator(new VariableMutator<>()); // mutateur "simple" par ration de mutation (0.01%)
        this.setReproductor(new UniformCrossOver<>());
//        this.setReproductor(new BasicReproductor<>()); // reproducteur simple : un seul croisement des gènes à une position aléatoire

    }

    @Override
    protected boolean solutionFinded() {
        return this.getBestPhenotype() != null && this.getBestPhenotype().getFitness() > 2;
    }
}
