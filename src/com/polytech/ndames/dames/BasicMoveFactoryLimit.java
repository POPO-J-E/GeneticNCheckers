package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kifkif on 16/03/2017.
 */
public class BasicMoveFactoryLimit extends BasicMoveFactory {

    @Override
    public List<Move> buildAllMoves(int size)
    {
        ArrayList<Move> moves = new ArrayList<>(size*size);
        int limit = (int)Math.sqrt(size);
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < limit; j++) {
                moves.add(buildMove(i, j));
                moves.add(buildMove(i, -j));
            }
        }
        return moves;
    }
}
