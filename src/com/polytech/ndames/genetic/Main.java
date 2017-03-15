package com.polytech.ndames.genetic;

public class Main
{
    public static void main(String[] args)
    {
        DameHarvester harvester = new DameHarvester(new DameFactory(32), 10000);
        harvester.goToXGeneration(50000);
    }
}
