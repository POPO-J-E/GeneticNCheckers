package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Utils {

    public static List<Board> getNeighbours(Board board)
    {
        List<Board> neighboursBoards = new ArrayList<>();

        for (int i = 0; i < board.getDames().size() ; i++) {
            neighboursBoards.addAll(getNeighbours(board, i));
        }
        return neighboursBoards;
    }

    public static List<Board> getNeighbours(Board board, int nbDame)
    {
        List<Board> neighbours = new ArrayList<>();

        Board newBoardLeft = board.newInstance();
        Board newBoardRight = board.newInstance();

        Dame dameLeft = newBoardLeft.getDames().get(nbDame);
        Dame dameRight = newBoardRight.getDames().get(nbDame);

        if (Utils.moveLeft(newBoardLeft,dameLeft))
            neighbours.add(newBoardLeft);
        if (Utils.moveRight(newBoardRight,dameRight))
            neighbours.add(newBoardRight);

        return neighbours;
    }

    public static boolean moveLeft(Board board, Dame dame)
    {
        if (dame.getRow() > 0)
        {
            dame.setRow(dame.getRow()-1);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean moveRight(Board board, Dame dame)
    {
        if (dame.getRow() < board.getSize()-1)
        {
            dame.setRow(dame.getRow()+1);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Board getAleaNeighbour(Board board)
    {
        Random rand = new Random();
        int aleaIndex = rand.nextInt(board.getSize());
        return getNeighbours(board).get(aleaIndex);
    }
}
