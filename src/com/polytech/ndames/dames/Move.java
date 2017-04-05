package com.polytech.ndames.dames;

/**
 * Created by kifkif on 16/03/2017.
 */
public interface Move<M extends Move>
{
    boolean equals(Object obj);

    boolean isOpposite(M move, int size);

    Board apply(Board board);
}
