package com.atles.genetic_harvester;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population<G extends Gene<?, G>> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int generation;
    private List<Phenotype<G>> population;
    private float totalFitness;
    private float bestFitness;
    private float worstFitness;
    private Phenotype<G> bestPhenotype;
    private int size;

    public Population() {
        this(0);
    }

    public Population(int gen) {
        totalFitness = 0;
        this.setGeneration(gen);
        setPopulation(new ArrayList<Phenotype<G>>());
    }

    /**
     * Loads neural network from the specified file.
     *
     * @param filePath file path to load network from
     * @return loaded neural network as NeuralNetwork object
     */
    public static Population<?> load(String filePath) {
        ObjectInputStream oistream = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Cannot find file: " + filePath);
            }

            oistream = new ObjectInputStream(new FileInputStream(filePath));
            Population<?> nnet = (Population<?>) oistream.readObject();

            return nnet;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } finally {
            if (oistream != null) {
                try {
                    oistream.close();
                } catch (IOException ioe) {
                }
            }
        }

        return null;
    }

    public void shuffle() {
        Collections.shuffle(population);
    }

    public boolean addPhenotype(Phenotype<G> ph) {
        return population.add(ph);
    }

    public List<Phenotype<G>> getPopulation() {
        return population;
    }

    public void setPopulation(List<Phenotype<G>> population) {
        this.population = population;
        calculTotalFitness();
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void render() {
        System.out.println("Population");
        System.out.println("Generation : " + generation);
        System.out.println("Size : " + population.size());
        System.out.println("Phenotypes : ");
        Iterator<Phenotype<G>> i = population.iterator();
        while (i.hasNext()) {
            i.next().render();
        }
    }

    public int getSize() {
        return population.size();
    }

    public void calculTotalFitness() {
        totalFitness = 0;
        bestFitness = Integer.MIN_VALUE;
        worstFitness = Integer.MAX_VALUE;
        Iterator<Phenotype<G>> i = this.population.iterator();
        while (i.hasNext()) {
            Phenotype<G> p = i.next();
            totalFitness += p.getFitness();

            if (p.getFitness() > bestFitness) {
                bestFitness = p.getFitness();
                this.bestPhenotype = p;
            }

            if (p.getFitness() < worstFitness) {
                worstFitness = p.getFitness();
            }
        }
    }

    public float getWorstFitness() {
        return worstFitness;
    }

    public float getTotalFitness() {
        return totalFitness;
    }

    public float getAverageFitness() {
        return totalFitness / this.getSize();
    }

    public float getBestFitness() {
        return this.getBestPhenotype().getFitness();
    }

    public Phenotype<G> getBestPhenotype() {
        return this.bestPhenotype;
    }

    public void save(String filePath) {
        System.out.print("save");
        ObjectOutputStream out = null;
        try {
            File file = new File(filePath);
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(this);
            out.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
