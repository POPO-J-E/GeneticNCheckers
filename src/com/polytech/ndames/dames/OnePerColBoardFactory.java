package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jeremy on 15/03/2017.
 */
public class OnePerColBoardFactory implements BoardFactory {

    private Random rand = new Random();

    public Board buildBoard(int size)
    {
        Board board = new Board(size);
        List<Integer> positions = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            positions.add(i);
        }

        for (int i = 0; i < size; i++) {
            Dame dame = new Dame(i,positions.remove(rand.nextInt(positions.size())));
            board.getDames().add(dame);
        }

        return board;
    }
}
