package com.polytech.ndames.dames;

/**
 * Created by kifkif on 16/03/2017.
 */
public class BasicMove implements Move<BasicMove>
{
    private int dame;
    private int velocity;

    public BasicMove(int dame, int velocity)
    {
        this.dame = dame;
        this.velocity = velocity;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Move{"+dame + "-" + velocity+"}";
    }

    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }

    public String opposite(int size)
    {
        return "Move{"+dame + " - " + (size-velocity)+"}";
    }

    public boolean isOpposite(BasicMove move, int size)
    {
        return (move.getDame() == dame) && ((size-velocity-1) == move.getVelocity());
    }

    @Override
    public Board apply(Board board) {
        Board newBoard = board.newInstance();
        Dame dame = newBoard.getDames().get(this.getDame());
        dame.setColumn(dame.getColumn() + this.getVelocity() % newBoard.getSize());
        return newBoard;
    }
}
