package com.polytech.ndames;

import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;
import com.atles.genetic_harvester.operator.Evaluator;

import java.util.List;

/**
 * Created by kifkif on 09/03/2017.
 */
public class DameEvaluatorV2 extends DameEvaluator {

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
        int[][] board = buildBoard(dame);

        int[] rows = new int[size];
        int[] collumns = new int[size];
        int[] diagonal_1 = new int[size*2-1];
        int[] diagonal_2 = new int[size*2-1];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int dames = board[i][j];
                rows[i] += dames;
                collumns[j] += dames;
                diagonal_1[i+j] += dames;
                diagonal_2[i+(size-j-1)] += dames;
            }
        }

        float conflicts = 0.00001f;

        for (int i = 0; i < size; i++)
        {
            if(rows[i] > 1)
                conflicts += rows[i]-1;
            if(collumns[i] > 1)
                conflicts += collumns[i]-1;
        }

        for (int i = 0; i < size*2-1; i++)
        {
            if(diagonal_1[i] > 1)
                conflicts += diagonal_1[i]-1;
            if(diagonal_2[i] > 1)
                conflicts += diagonal_2[i]-1;
        }

        fitness = 1f / conflicts;

        dame.setFitness(fitness);
    }
}
