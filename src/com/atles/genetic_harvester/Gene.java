package com.atles.genetic_harvester;

import java.io.Serializable;

public interface Gene<A, G extends Gene<A, G>> extends Factory<G>, Serializable {
    public A getAllele();

    public G newInstance();

    public G newInstance(final A value);

    public G mutate();

}
