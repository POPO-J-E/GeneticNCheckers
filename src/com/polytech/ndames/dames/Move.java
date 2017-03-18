package com.polytech.ndames.dames;

/**
 * Created by kifkif on 16/03/2017.
 */
public class Move
{
    private int dame;
    private int velocity;

    public Move(int dame, int velocity)
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
        return dame + "-" + velocity;
    }

    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }

    public String oposite(int size)
    {
        return dame + "-" + (size-velocity);
    }
}
