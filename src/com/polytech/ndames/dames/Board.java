package com.polytech.ndames.dames;

import com.atles.genetic_harvester.Factory;
import com.atles.genetic_harvester.operator.UniformCrossOver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Jérémy on 15/03/2017.
 */
public class Board implements Factory<Board> {
    //Fiels
    private List<Dame> dames;
    private int size;

    //Constructors
    public Board(int size) {
        this.dames = new ArrayList<>(size);
        this.size = size;
    }
    public Board(List<Dame> dames) {
        this.dames = dames;
        size = dames.size();
    }

    //Getters & Setters
    public List<Dame> getDames() {
        return dames;
    }

    public int getSize() {
        return size;
    }

    //Implemented Methods
    @Override
    public Board newInstance() {
        List<Dame> dames = new ArrayList<>();
        for (Dame dame : this.dames)
        {
            dames.add(dame.newInstance());
        }
        return new Board(dames);
    }

    @Override
    public String toString() {
        return "Board{" +
                "size=" + size +
                ", fitness=" + Utils.getFistness(this) +
                '}';
    }
}
