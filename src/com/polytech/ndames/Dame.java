package com.polytech.ndames;

import com.atles.genetic_harvester.*;

/**
 * Created by kifkif on 09/03/2017.
 */
public class Dame extends PhenotypeMonoChromosome<NumericGene>
{
    private int[][] board;

    public Dame(Chromosome<NumericGene> chromosome) {
        super(chromosome);
    }

    public int[][] getBoard()
    {
        if(board == null)
            this.buildBoard();
        return board;
    }

    private void buildBoard()
    {
        Chromosome<NumericGene> chromosome = this.getChromosome();

        int size = getSize();
        this.board = new int[size][size];

        for (int i = 0; i < size; i++)
        {
            int x = chromosome.getGenes().get(i).getAllele();
            int y = i;

            board[y][x]++;
        }
    }

    public int nbrDameAt(int x, int y)
    {
        return this.getBoard()[x][y];
    }

    public int getSize() {
        return this.getChromosome().getLength();
    }
}
