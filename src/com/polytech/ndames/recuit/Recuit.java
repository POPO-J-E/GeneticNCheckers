package com.polytech.ndames.recuit;


import com.polytech.ndames.dames.*;
import sample.Utils.Resolver;

import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Recuit extends Resolver<Recuit> {

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
        this.boardFactory = new OnePerColBoardFactory();
        this.gamma = gamma;
    }

    public Recuit(int size, int nbIteration, float alpha)
    {
        this(size, nbIteration, alpha, 0.00001F);
    }

    public Recuit() {
        this(8, 100, 0.99f);
    }

    public Board startResolve(){
        initialBoard = boardFactory.buildBoard(size);
        bestBoard = initialBoard;
//        int maxIteration = nbIteration;
        Board currentBoard = initialBoard;
        deltaf = getHighDeltaF(10);
        temperature = (float) (-deltaf/Math.log(0.1));
        System.out.println("Temp = " + temperature);
        int n1 = generateN1();
        System.out.println("N1 = " + n1);

        int n1depart = n1;
        float temperatureDepart = temperature;

        while(/*temperature >= gamma && */running && nbIteration>=0){
            if(n1==0)
                temperature = (float) (Math.pow(alpha,n1depart)*temperatureDepart);
            Board aleaNeighbour = Utils.getRandomNeighbour(currentBoard);
            //Board aleaNeighbour = Utils.getAleaNeighbour(currentBoard);
            //System.out.println("alea=" +aleaNeighbour);

            deltaf = Utils.getFistness(aleaNeighbour) - Utils.getFistness(currentBoard);
            if (deltaf<=0)
            {
                currentBoard = aleaNeighbour;
                if(Utils.getFistness(currentBoard) < Utils.getFistness(bestBoard))
                {
                    bestBoard = aleaNeighbour;
                    System.out.println(Utils.getFistness(bestBoard));
                    if (Utils.getFistness(bestBoard) == 0)
                    {
                        setChanged();
                        notifyObservers(bestBoard);
                        this.endResolve();
                        return bestBoard;
                    }
                }
            }
            else {
                if (rand.nextFloat() <= Math.exp(-deltaf/temperature))
                    currentBoard = aleaNeighbour;
            }

            //Stop condition
            if(!this.running)
            {
                this.endResolve();
                return this.bestBoard;
            }
            else
            {
                setChanged();
                notifyObservers(currentBoard);
            }
            n1--;
            nbIteration--;
            temperature *= alpha;
        }

        setChanged();
        notifyObservers(bestBoard);

        this.endResolve();

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNbIteration() {
        return nbIteration;
    }

    public void setNbIteration(int nbIteration) {
        this.nbIteration = nbIteration;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getGamma() {
        return gamma;
    }

    public void setGamma(float gamma) {
        this.gamma = gamma;
    }

    public float getBestFitness() {
        if(this.bestBoard == null)
            return 0;
        return Utils.getFistness(this.bestBoard);
    }

    @Override
    public Board getBestBoard() {
        return bestBoard;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getDeltaf() {
        return deltaf;
    }

    public void setDeltaf(float deltaf) {
        this.deltaf = deltaf;
    }
}
