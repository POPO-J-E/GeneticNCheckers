package com.polytech.ndames.recuit;

import com.polytech.ndames.dames.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Recuit {

    private int size = 8;
    private float deltaf;
    
    private Board initialBoard;
    private Board bestBoard;
    
    private Float fitness;
    
    private int nbIteration;
    
    private float temperature;
    private float alpha;
    
    private Random rand;
    private BoardFactory boardFactory;

    private float gamma;

    public Recuit(int size, int nbIteration, float alpha, float gamma) {
        this.size = size;
        this.nbIteration = nbIteration;
        this.alpha = alpha;
        this.rand = new Random();
        this.boardFactory = new RandomBoardFactory();
        this.gamma = gamma;
    }

    public Recuit(int size, int nbIteration, float alpha)
    {
        this(size, nbIteration, alpha, 0.00001F);
    }

    public Board start(){
        initialBoard = boardFactory.buildBoard(size);
        bestBoard = initialBoard;

        Board currentBoard = initialBoard;
        deltaf = getHighDeltaF(10);
        temperature = (float) (-deltaf/Math.log(0.8));
        System.out.println("Temp = " + temperature);
        int n1 = generateN1();
        System.out.println("N1 = " + n1);

        for (int j = 0; j < n1; j++) {
            for (int k = 1; k < nbIteration; k++) {
                Board aleaNeighbour = Utils.getRandomNeighbour(currentBoard);
//                Board aleaNeighbour = Utils.getAleaNeighbour(currentBoard);
                System.out.println("alea=" +aleaNeighbour);

                deltaf = Utils.getFistness(aleaNeighbour) - Utils.getFistness(currentBoard);
                if (deltaf<=0)
                {
                    currentBoard = aleaNeighbour;
                    if(Utils.getFistness(currentBoard) < Utils.getFistness(bestBoard))
                    {
                        bestBoard = aleaNeighbour;
                        System.out.println(Utils.getFistness(bestBoard));
                        if (Utils.getFistness(bestBoard) == 0)
                            return bestBoard;
                    }
                }
                else {
                    if (rand.nextFloat() <= Math.exp(-deltaf/temperature))
                        currentBoard = aleaNeighbour;
                }
            }
            temperature *= alpha;
            System.out.println(bestBoard);
        }
        return bestBoard;
    }

    public int generateN1()
    {
        return (int) (Math.log(-deltaf/(temperature*Math.log(0.01)))/Math.log(alpha));
    }

    public float getHighDeltaF(int nb)
    {
        float sum=0;
        for (int i = 0; i < nb; i++) {
            sum += Utils.getFistness(boardFactory.buildBoard(size));
        }

        return sum/nb;
    }

}
