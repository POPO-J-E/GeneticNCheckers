package sample.Utils;

import com.polytech.ndames.dames.Board;

import java.util.Observable;
import java.util.function.Consumer;

/**
 * Created by jeremy on 23/03/2017.
 */
public abstract class Resolver<R extends Resolver> extends Observable{

    private Consumer<R> onStopAction;
    protected boolean running;
    private long startMillis;
    private long endMillis;

    public Resolver() {
        this.running = false;
    }

    public synchronized Board start()
    {
        this.running = true;
        startMillis = System.currentTimeMillis();
        endMillis = 0;
        return this.startResolve();
    }

    public abstract Board startResolve();

    public synchronized void stop()
    {
        this.running = false;
        this.onStop();
    }

    private void onStop()
    {
        if(onStopAction != null)
            onStopAction.accept((R) this);
    }

    public void setOnStop(Consumer<R> action)
    {
        this.onStopAction = action;
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public void endResolve()
    {
        endMillis = System.currentTimeMillis();
        this.setChanged();
        this.notifyObservers("end");
    }

    public abstract float getBestFitness();

    public abstract Board getBestBoard();

    public long getMillisExecutionTime()
    {
        if(endMillis == 0)
            return System.currentTimeMillis() - startMillis;
        else
            return endMillis - startMillis;
    }
}
