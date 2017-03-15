package com.atles.genetic_harvester.operator;

import com.atles.genetic_harvester.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BasicReproductor<G extends Gene<?, G>> implements Reproductor<G> {
    public static Random rand = new Random();

    @Override
    public Couple<G> reproduce(Couple<G> couple) {
        int lenght_ch = couple.getP1().getGenotype().getChromosomes().size();
        List<Chromosome<G>> chomosomes1 = new ArrayList<>(lenght_ch);
        List<Chromosome<G>> chomosomes2 = new ArrayList<>(lenght_ch);

        for (int i = 0; i < lenght_ch; i++) {
            Chromosome<G> ch1 = couple.getP1().getGenotype().getChromosomes().get(i);
            Chromosome<G> ch2 = couple.getP2().getGenotype().getChromosomes().get(i);
            int lenght_gn = ch1.getLength();

            int pivaut = 1 + rand.nextInt(lenght_gn - 1);
            List<G> genes1 = blankCopy(lenght_gn);
            List<G> genes2 = blankCopy(lenght_gn);

            for (int j = 0; j < lenght_gn; j++) {
                if (j < pivaut) {
                    genes1.add(ch1.getGenes().get(j).newInstance());
                    genes2.add(ch2.getGenes().get(j).newInstance());
                } else {
                    genes1.add(ch2.getGenes().get(j).newInstance());
                    genes2.add(ch1.getGenes().get(j).newInstance());
                }
            }

            chomosomes1.add(new Chromosome<G>(genes1));
            chomosomes2.add(new Chromosome<G>(genes2));
        }

        Phenotype<G> child1 = new Phenotype<G>(new Genotype<G>(chomosomes1));
        Phenotype<G> child2 = new Phenotype<G>(new Genotype<G>(chomosomes2));

        return new Couple<G>(child1, child2);
    }

    public List<G> blankCopy(int size) {
        return new ArrayList<G>(size);
    }
}
