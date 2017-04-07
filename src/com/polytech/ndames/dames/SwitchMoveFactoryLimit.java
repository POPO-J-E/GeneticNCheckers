package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kifkif on 28/03/2017.
 */
public class SwitchMoveFactoryLimit implements MoveFactory {

    private int limit;

    public SwitchMoveFactoryLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public List<Move> buildAllMoves(int size) {
        ArrayList<Move> moves = new ArrayList<>(size*size);
        for (int i = 0; i < size; i++) {
            int limit = i+1+this.limit;
            if(limit > size)
                limit = size;
            for (int j = i+1; j < limit; j++) {
                moves.add(buildMove(i, j));
            }
        }
        return moves;
    }

    private Move buildMove(int i, int j) {
        return new SwitchMove(i, j);
    }

    @Override
    public Move buildRandomMove(int size) {
        return null;
    }
}