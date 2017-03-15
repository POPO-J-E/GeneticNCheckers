package com.polytech.ndames.genetic;

import com.atles.genetic_harvester.Chromosome;
import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;
import com.atles.genetic_harvester.Population;
import com.atles.genetic_harvester.operator.Evaluator;

import java.util.List;

/**
 * Created by kifkif on 09/03/2017.
 */
public class DameEvaluator implements Evaluator<NumericGene> {

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
        int fitness = 0;
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

        for (int i = 0; i < size; i++)
        {
            if(rows[i] == 1)
                fitness++;
            if(collumns[i] == 1)
                fitness++;
        }

        for (int i = 0; i < size*2-1; i++)
        {
            if(diagonal_1[i] == 1)
                fitness++;
            if(diagonal_2[i] == 1)
                fitness++;
        }

        dame.setFitness(fitness);
    }

    protected int[][] buildBoard(Phenotype<NumericGene> dame)
    {
        Chromosome<NumericGene> chromosome = dame.getChromosome(0);

        int size = chromosome.getLength();
        int[][] board = new int[size][size];

        for (int i = 0; i < size; i++)
        {
            int x = chromosome.getGenes().get(i).getAllele();
            int y = i;

            board[y][x]++;
        }

        return board;
    }

    @Override
    public void update(Population<NumericGene> population) {

    }
}
