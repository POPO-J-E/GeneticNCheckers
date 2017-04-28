package com.polytech.ndames.genetic;

import com.polytech.ndames.dames.Board;
import com.polytech.ndames.dames.Utils;
import sample.Utils.Resolver;

/**
 * Created by kifkif on 28/04/2017.
 */
public class GeneticResolver extends Resolver<GeneticResolver> {

    private int nbGen;
    private DameHarvester harvester;

    public GeneticResolver() {
        this(8, 10, 100);
    }

    public GeneticResolver(int size, int nbGen, int pop) {
        this.nbGen = nbGen;
        harvester = new DameHarvester(new DameFactory(size), pop);
        harvester.setOnNotify(p -> {
            setChanged();
            Board board = Utils.phenotypeToBoard(p);
            notifyObservers(board);
        });

        harvester.setOnEnd(p -> {
            setChanged();
            notifyObservers("end");
        });

        this.setOnStop(g->harvester.stop());
    }

    @Override
    public Board startResolve() {
        harvester.reset();
        harvester.goToXGeneration(nbGen);
        return Utils.phenotypeToBoard(harvester.getBestPhenotype());
    }

    @Override
    public float getBestFitness() {
        return Utils.getFistness(Utils.phenotypeToBoard(harvester.getBestPhenotype()));
    }

    @Override
    public Board getBestBoard() {
        return Utils.phenotypeToBoard(harvester.getBestPhenotype());
    }

    public int getNbGen() {
        return nbGen;
    }

    public void setNbGen(int nbGen) {
        this.nbGen = nbGen;
    }

    public float getMutationRate() {
        return harvester.getMutator().getMutationRate();
    }

    public void setMutationRate(float mutationRate) {
        this.harvester.getMutator().setMutationRate(mutationRate);
    }

    public int getBoardSize() {
        return ((DameFactory)this.harvester.getPhenotypeFactory()).getSize();
    }

    public void setBoardSize(int boardSize) {
        ((DameFactory)this.harvester.getPhenotypeFactory()).setSize(boardSize);
    }

    public int getPopulationSize() {
        return this.harvester.getSize();
    }

    public void setPopulationSize(int populationSize) {
        this.harvester.setSize(populationSize);
    }
}
