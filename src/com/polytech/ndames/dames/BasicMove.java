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
        return "Move{"+dame + " - " + velocity+"}";
    }

    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }

    public String opposite(int size)
    {
        return "Move{"+dame + velocity + " - " + (-velocity)+"}";
    }

    public boolean isOpposite(BasicMove move, int size)
    {
        int secondDame = dame + velocity;
        if(secondDame < 0)
            secondDame += size;
        secondDame %= size;

        int vel1 = velocity;
        int vel2 = -move.getVelocity();
        if(vel1 < 0)
            vel1 += size;
        if(vel2 < 0)
            vel2 += size;

        return (move.getDame() == secondDame) && (vel1 == -vel2);
    }

    @Override
    public Board apply(Board board) {
        Board newBoard = board.newInstance();
        Dame dame = newBoard.getDames().get(this.getDame());
        int vel = this.getVelocity();
        int lastCol = dame.getColumn();
        int col = lastCol + vel;
        if(col < 0)
            col += newBoard.getSize();
        int newCol = col % newBoard.getSize();
        dame.setColumn(newCol);
        return newBoard;
    }
}
