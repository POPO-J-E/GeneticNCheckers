package com.polytech.ndames.tabou;

import com.polytech.ndames.dames.BasicMoveFactory;
import com.polytech.ndames.dames.OnePerColBoardFactory;
import com.polytech.ndames.dames.SwitchMoveFactory;
import com.polytech.ndames.dames.SwitchMoveFactoryLimit;

/**
 * Created by jeremy on 15/03/2017.
 */
public class Main {

    public static void main(String[] args)
    {
        int size = 254;
        int limit = (int)Math.sqrt(size)*2;
        System.out.println("limit : "+limit);
        Tabou tabou = new Tabou(254, 8,10000, new OnePerColBoardFactory(), new SwitchMoveFactoryLimit(limit));
        tabou.start();
    }

}
