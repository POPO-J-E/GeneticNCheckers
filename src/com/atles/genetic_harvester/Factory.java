package com.atles.genetic_harvester;

public interface Factory<T> {

    /**
     * Create a new instance of type T.
     *
     * @return a new instance of type T
     */
    public T newInstance();
}
