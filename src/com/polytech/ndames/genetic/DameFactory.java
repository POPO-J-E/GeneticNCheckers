package com.polytech.ndames;

import com.atles.genetic_harvester.Chromosome;
import com.atles.genetic_harvester.Factory;
import com.atles.genetic_harvester.NumericGene;
import com.atles.genetic_harvester.Phenotype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kifkif on 09/03/2017.
 */
public class DameFactory implements Factory<Phenotype<NumericGene>> {

    private int size;

    public  DameFactory(int size)
    {
        this.size = size;
    }

    @Override
    public Dame newInstance() {
        int nbrGenes = size;
        List<NumericGene> genes = new ArrayList<NumericGene>(nbrGenes);
        for (int i = 0; i < nbrGenes; i++)
            genes.add(newGene());
        Chromosome<NumericGene> chromosome = new Chromosome<NumericGene>(genes);
        return new Dame(chromosome);
    }

    private NumericGene newGene()
    {
        return new NumericGene(0, size);
    }
}
