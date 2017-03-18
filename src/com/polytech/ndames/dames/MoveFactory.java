package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kifkif on 16/03/2017.
 */
public class MoveFactory {

    public List<Move> buildAllMoves(int size)
    {
        ArrayList<Move> moves = new ArrayList<>(size*size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                moves.add(buildMove(i, j));
            }
        }
        return moves;
    }

    public Move buildMove(int dame, int velocity)
    {
        return new Move(dame, velocity);
    }

    public Move buildRandomMove(int size)
    {
        return buildMove(Utils.rand.nextInt(size), Utils.rand.nextInt(size));
    }
}
