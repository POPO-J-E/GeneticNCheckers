package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kifkif on 28/03/2017.
 */
public class SwitchMoveFactoryLimit implements MoveFactory {

    private int limit;

    public SwitchMoveFactoryLimit(int limit) {
        this.limit = limit;
    }
    public SwitchMoveFactoryLimit() {
        this(0);
    }

    @Override
    public List<Move> buildAllMoves(int size) {
        this.limit = (int)Math.sqrt(size)+1;
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
        Random r = new Random();
        List<Move> moves = buildAllMoves(size);
        return moves.get(r.nextInt(moves.size()));
    }
}
