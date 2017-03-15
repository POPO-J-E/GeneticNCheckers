package com.polytech.ndames.dames;

import java.util.Random;

/**
 * Created by jeremy on 15/03/2017.
 */
public class BoardFactory {

    private Random rand = new Random();

    public Board buildBoard(int size)
    {
        Board board = new Board(size);
        for (int i = 0; i < board.getSize(); i++) {
            int aleaColumn = rand.nextInt(board.getSize());
            Dame dame = new Dame(i,aleaColumn);
            board.getDames().add(dame);
        }
        return board;
    }
}
