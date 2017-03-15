package com.atles.genetic_harvester;

import java.io.Serializable;
import java.util.Random;

public class NumericGene implements Gene<Integer, NumericGene>, Serializable {
    private static final long serialVersionUID = 1L;

    private final static Random rand = new Random();

    private final int min;
    private final int max;
    private int value;

    public NumericGene(int min, int max, int val) {
        this.min = min;
        this.max = max;
        this.value = val;
    }

    public NumericGene() {
        this(0, 100);
    }

    public NumericGene(int min, int max) {
        this(min, max, rand.nextInt(max - min) + min);
    }

    @Override
    public Integer getAllele() {
        return value;
    }

    @Override
    public NumericGene newInstance() {
        return newInstance(this.value);
    }

    @Override
    public NumericGene newInstance(Integer value) {
        return new NumericGene(this.min, this.max, value);
    }

    private int getRandValue() {
        return this.getRandValue(min, max);
    }

    private int getRandValue(int min, int max) {
        return min + rand.nextInt(max - min);
    }

    public NumericGene mutate() {
        this.value = getRandValue();
        return this;
    }

}
