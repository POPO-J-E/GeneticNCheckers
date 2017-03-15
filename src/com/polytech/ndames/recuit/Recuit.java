package com.polytech.ndames.recuit;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.BoardFactory;
import com.polytech.ndames.dames.Dame;
import com.polytech.ndames.dames.Utils;

import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Recuit {

    private Board initialBoard;
    private int size;
    private int nbIteration;
    private int i=0;
    private double temperature;
    private Random rand = new Random();
    private BoardFactory boardFactory = new BoardFactory();

    public void start(){
        initialBoard = boardFactory.buildBoard(size);
        temperature = 0.5;

        while(i < nbIteration)
        {
            Board aleaBoard = Utils.getAleaNeighbour(initialBoard);

        }
    }


}
