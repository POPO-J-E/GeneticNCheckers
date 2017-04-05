package com.polytech.ndames.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kifkif on 16/03/2017.
 */
public interface MoveFactory{

    public List<Move> buildAllMoves(int size);

    public Move buildRandomMove(int size);
}
