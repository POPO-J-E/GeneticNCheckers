package com.atles.genetic_harvester;

public class Couple<G extends Gene<?, G>> {
    private Phenotype<G> p1;
    private Phenotype<G> p2;

    public Couple(Phenotype<G> p1, Phenotype<G> p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Phenotype<G> getP1() {
        return p1;
    }

    public void setP1(Phenotype<G> p1) {
        this.p1 = p1;
    }

    public Phenotype<G> getP2() {
        return p2;
    }

    public void setP2(Phenotype<G> p2) {
        this.p2 = p2;
    }
}
