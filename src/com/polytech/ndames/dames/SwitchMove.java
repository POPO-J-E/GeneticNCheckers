package com.polytech.ndames.dames;

/**
 * Created by kifkif on 16/03/2017.
 */
public class SwitchMove implements Move<SwitchMove>
{
    private int col_1;
    private int col_2;

    public SwitchMove(int col_1, int col_2) {
        this.col_1 = col_1;
        this.col_2 = col_2;
    }

    public boolean isOpposite(SwitchMove move, int size)
    {
        return ((move.getCol_1() == col_1) && (move.getCol_2() == col_2)) || ((move.getCol_1() == col_2) && (move.getCol_1() == col_2));
    }

    @Override
    public Board apply(Board board) {
        Board newBoard = board.newInstance();
        Dame dame_1 = newBoard.getDames().get(this.getCol_1());
        Dame dame_2 = newBoard.getDames().get(this.getCol_2());
        newBoard.getDames().set(this.getCol_1(), dame_2);
        newBoard.getDames().set(this.getCol_2(), dame_1);
        return newBoard;
    }

    public int getCol_1() {
        return col_1;
    }

    public void setCol_1(int col_1) {
        this.col_1 = col_1;
    }

    public int getCol_2() {
        return col_2;
    }

    public void setCol_2(int col_2) {
        this.col_2 = col_2;
    }
}
