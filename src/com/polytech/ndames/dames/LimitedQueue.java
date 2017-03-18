package com.polytech.ndames.dames;

import java.util.LinkedList;

/**
 * Created by kifkif on 18/03/2017.
 */
public class LimitedQueue<E> extends LinkedList<E> {

    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        boolean added = super.add(o);
        while (added && size() > limit) {
            super.remove();
        }
        return added;
    }
}