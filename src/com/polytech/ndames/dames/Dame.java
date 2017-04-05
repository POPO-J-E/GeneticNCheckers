package com.polytech.ndames.dames;

import com.atles.genetic_harvester.Factory;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Dame implements Factory<Dame>{

    private int row;
    private int column;

    public Dame() {
    }

    public Dame(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public Dame newInstance() {
        return new Dame(row, column);
    }

    @Override
    public String toString() {
        return "Dame{" +
                "" + row +
                " - " + column +
                '}';
    }
}
