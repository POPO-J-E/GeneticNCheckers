package com.polytech.ndames.tabou;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.Dame;
import com.polytech.ndames.dames.Move;

import java.util.List;

/**
 * Created by kifkif on 18/03/2017.
 */
public class TabouBoard extends Board {

    private Move move;

    public TabouBoard(int size) {
        this(size, null);
    }

    public TabouBoard(List<Dame> dames) {
        this(dames, null);
    }

    public TabouBoard(int size, Move move) {
        super(size);
        this.move = move;
    }

    public TabouBoard(List<Dame> dames, Move move) {
        super(dames);
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
