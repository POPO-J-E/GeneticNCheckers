package com.polytech.ndames.dames;

import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;
import com.polytech.ndames.genetic.DameFactory;

import java.util.*;

/**
 *
 * Created by jeremy on 15/03/2017.
 */
public class Utils {

    public static Random rand = new Random();
    public static MoveFactory moveFactory = new BasicMoveFactory();

    public static Board getNeighbour(Board board, BasicMove move)
    {
        Board newBoard = board.newInstance();
        Dame dame = newBoard.getDames().get(move.getDame());
        dame.setColumn(dame.getColumn() + move.getVelocity() % newBoard.getSize());
        return newBoard;
    }

    public static Board getRandomNeighbour(Board board)
    {
        BasicMove move = (BasicMove)moveFactory.buildRandomMove(board.getSize());
        return getNeighbour(board, move);
    }

    public static List<Board> getNeighbours(Board board)
    {
        List<Board> neighboursBoards = new ArrayList<>();

        for (int i = 0; i < board.getDames().size() ; i++) {
            neighboursBoards.addAll(getNeighbours(board, i));
        }
        return neighboursBoards;
    }

    public static Map<Move, Board> getNeighbours(Board board, List<Move> moves)
    {
        Map<Move, Board> neighbours = new HashMap<>();
        moves.forEach(move->neighbours.put(move, move.apply(board)));
        return neighbours;
    }

    public static List<Board> getNeighbours(Board board, int nbDame)
    {
        List<Board> neighbours = new ArrayList<>();

        Board newBoardLeft = board.newInstance();
        Board newBoardRight = board.newInstance();

        Dame dameLeft = newBoardLeft.getDames().get(nbDame);
        Dame dameRight = newBoardRight.getDames().get(nbDame);

        if (Utils.moveLeft(newBoardLeft,dameLeft))
            neighbours.add(newBoardLeft);
        if (Utils.moveRight(newBoardRight,dameRight))
            neighbours.add(newBoardRight);

        return neighbours;
    }

    public static boolean moveLeft(Board board, Dame dame)
    {
        if (dame.getRow() > 0)
        {
            dame.setRow(dame.getRow()-1);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean moveRight(Board board, Dame dame)
    {
        if (dame.getRow() < board.getSize()-1)
        {
            dame.setRow(dame.getRow()+1);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Board getAleaNeighbour(Board board)
    {
        Random rand = new Random();
        int aleaIndex = rand.nextInt(board.getSize());
        return getNeighbours(board).get(aleaIndex);
    }

    public static float getFistness(Board board)
    {
        float fitness = 0;
        int size = board.getSize();
        float conflicts = 0;

        for(int row = 0; row < size; row++)
        {
            Dame dame = board.getDames().get(row);
            for (int row_bis = row+1; row_bis < size; row_bis++)
            {
                Dame dame_bis = board.getDames().get(row_bis);

                if((dame.getColumn() == dame_bis.getColumn()) || (row_bis - row == Math.abs(dame.getColumn() - dame_bis.getColumn())))
                {
                    conflicts++;
                    dame.setConflict(true);
                    dame_bis.setConflict(true);
                }
            }
        }

        fitness = conflicts;
        return fitness;
    }

    public static Move getOpposite(List<Move> moves, Move move, int size)
    {
        for (Move m : moves) {
            if(move.isOpposite(m, size))
                return m;
        }

        try {
            return null;
        }
        finally {
            System.out.println("No opposite move.");
        }
    }

    public static Board phenotypeToBoard(Phenotype<NumericGene> phenotype) {
        Board board = new Board(phenotype.getChromosome(0).getGenes().size());
        for (int i = 0; i < board.getSize(); i++) {
            Dame d = new Dame(i,phenotype.getGene(0, i).getAllele());
            board.getDames().add(d);
        }
        return board;
    }
}
