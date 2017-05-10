package com.polytech.ndames.tabou;

import com.polytech.ndames.dames.*;
import sample.Utils.Resolver;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Tabou extends Resolver<Tabou> {

    private int tabouSize;
    private int size = 8;
    
    private Board initialBoard;
    private Board bestBoard;
    private float bestFitness;
    
    private int nbIteration;

    private Random rand;
    private BoardFactory boardFactory;
    private MoveFactory moveFactory;

    private LimitedQueue<Move> fifo;
    private List<Move> moves;

    public Tabou() {
        this(8, 2, 10000, new OnePerColBoardFactory(), new SwitchMoveFactory());
    }

    public Tabou(int size, int tabouSize, int nbIteration, BoardFactory boardFactory, MoveFactory moveFactory) {
        this.size = size;
        this.tabouSize = tabouSize;
        this.nbIteration = nbIteration;
        this.rand = new Random();
        this.boardFactory = boardFactory;
        this.moveFactory = moveFactory;
    }

    public Board startResolve(){
        moves = moveFactory.buildAllMoves(size);
        fifo = new LimitedQueue<>(tabouSize);

        initialBoard = boardFactory.buildBoard(size);
        Board currentBoard = initialBoard;
        bestBoard = initialBoard;
        bestFitness = Utils.getFistness(bestBoard);
        float currentFitness = bestFitness;

        int i = 0;
        do
        {
            Map<Move, Board> neighbours = Utils.getNeighbours(currentBoard, moves);

            Board localBestBoard = null;
            Move localMove = null;
            float localBestFitness = Float.MAX_VALUE;

            for (Map.Entry<Move, Board> entry : neighbours.entrySet()) {
                Move move = entry.getKey();
                Board board = entry.getValue();

                if(!fifo.contains(move)) {
                    float fitness = Utils.getFistness(board);
                    if (fitness < localBestFitness) {
                        localBestFitness = fitness;
                        localBestBoard = board;
                        localMove = move;

                        setChanged();
                        notifyObservers(localBestBoard);
                    }

                    //Stop condition
                    if (!this.running) {
                        this.endResolve();
                        return this.bestBoard;
                    }
                }
            }

            if(localBestFitness >= currentFitness){
                forbidMove(localMove);
            }
            else if(localBestFitness < bestFitness)
            {
                bestFitness = localBestFitness;
                bestBoard = localBestBoard;
            }

            currentFitness = localBestFitness;
            currentBoard = localBestBoard;

            setChanged();
            notifyObservers(currentFitness);

            i++;
        }
        while (i < nbIteration && bestFitness > 0);

        setChanged();
        notifyObservers(bestBoard);
        this.endResolve();
        return bestBoard;
    }

    private void forbidMove(Move move)
    {
        Move opposite = Utils.getOpposite(moves, move, size);
        if(opposite == null)
            System.out.println("null move");
        this.fifo.add(opposite);
        //moves.remove(opposite);
//        Move popMove = this.fifo.addElement(opposite);
//        if(popMove != null)
//            this.moves.add(popMove);
    }

    public int getTabouSize() {
        return tabouSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board getInitialBoard() {
        return initialBoard;
    }

    public void setInitialBoard(Board initialBoard) {
        this.initialBoard = initialBoard;
    }

    public Board getBestBoard() {
        return bestBoard;
    }

    public void setBestBoard(Board bestBoard) {
        this.bestBoard = bestBoard;
    }

    public float getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(float bestFitness) {
        this.bestFitness = bestFitness;
    }

    public int getNbIteration() {
        return nbIteration;
    }

    public void setNbIteration(int nbIteration) {
        this.nbIteration = nbIteration;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public BoardFactory getBoardFactory() {
        return boardFactory;
    }

    public void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    public LimitedQueue<Move> getFifo() {
        return fifo;
    }

    public void setFifo(LimitedQueue<Move> fifo) {
        this.fifo = fifo;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void setTabouSize(int size) {
        tabouSize = size;
    }
}
