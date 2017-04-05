package com.polytech.ndames.tabou;

import com.polytech.ndames.dames.*;
import sample.Utils.Resolver;

import java.util.List;
import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Tabou implements Resolver {

    private final int tabouSize;
    private int size = 8;
    private float deltaf;
    
    private Board initialBoard;
    private Board bestBoard;
    private float bestFitness;
    
    private Float fitness;
    
    private int nbIteration;
    
    private float temperature;
    private float alpha;
    
    private Random rand;
    private BoardFactory boardFactory;

    private LimitedQueue<Move> fifo;
    private List<Move> moves;

    public Tabou(int size, int tabouSize, int nbIteration, float alpha) {
        this.size = size;
        this.tabouSize = tabouSize;
        this.nbIteration = nbIteration;
        this.alpha = alpha;
        this.rand = new Random();
        this.boardFactory = new BoardFactory();
    }

    public Tabou() {
        tabouSize = 0;
    }

    public void start(){
        moves = Utils.moveFactory.buildAllMoves(size);
        fifo = new LimitedQueue<>(tabouSize);

        initialBoard = boardFactory.buildBoard(size);
        Board currentBoard = initialBoard;
        bestBoard = initialBoard;
        bestFitness = Utils.getFistness(bestBoard);
        float currentFitness = bestFitness;

        int i = 0;
        do
        {
            List<Board> neighbours = Utils.getNeighbours(currentBoard, moves);

            Board localBestBoard = null;
            float localBestFitness = Float.MAX_VALUE;

            for(Board neighbour : neighbours)
            {
                float fitness = Utils.getFistness(neighbour);
                if(fitness < localBestFitness)
                {
                    localBestFitness = fitness;
                    localBestBoard = neighbour;
                }
            }

            if(localBestFitness > currentFitness)


            i++;
        }
        while (i < nbIteration && bestFitness > 0);
    }
}
