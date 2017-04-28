package sample.Utils;

import com.polytech.ndames.dames.Board;
import sample.Controller.SettingsController;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;

/**
 * Created by jeremy on 23/03/2017.
 */
public abstract class Resolver<R extends Resolver> extends Observable{

    private Consumer<R> onStopAction;
    protected boolean running;

    public Resolver() {
        this.running = false;
    }

    public synchronized Board start()
    {
        this.running = true;
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
        this.setChanged();
        this.notifyObservers("end");
    }

    public abstract float getBestFitness();

    public abstract Board getBestBoard();
}
