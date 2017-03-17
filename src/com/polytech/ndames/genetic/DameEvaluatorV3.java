package com.polytech.ndames.genetic;

import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;
import com.atles.genetic_harvester.operator.Evaluator;
import com.atles.genetic_harvester.operator.Operator;

import java.util.List;

/**
 * Created by kifkif on 09/03/2017.
 */
public class DameEvaluatorV3 implements Evaluator<NumericGene> {

    @Override
    public void evaluate(Population<NumericGene> population)
    {
        List<Phenotype<NumericGene>> phenotypes = population.getPopulation();

        for (Phenotype<NumericGene> phenotype : phenotypes) {
            evaluate(phenotype);
        }
    }

    private void evaluate(Phenotype<NumericGene> dame)
    {
        float fitness = 0;
        int size = dame.getChromosome(0).getLength();
        int conflicts = 0;

        for(int row = 0; row < size; row++)
        {
            int posGene = dame.getGene(0, row).getAllele();
            for (int row_bis = row+1; row_bis < size; row_bis++)
            {
                int posGene_bis = dame.getGene(0, row_bis).getAllele();

                if(posGene == posGene_bis)
                    conflicts++;
                if(row_bis - row == Math.abs(posGene - posGene_bis))
                    conflicts++;
            }
        }

        fitness = 1 / (conflicts+0.0001f);
        dame.setFitness(fitness);
    }

    @Override
    public void update(Population<NumericGene> population) {
    }
}
