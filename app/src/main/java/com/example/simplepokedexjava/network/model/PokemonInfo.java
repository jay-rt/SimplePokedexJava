package com.example.simplepokedexjava.network.model;

public class PokemonInfo {
    String name;
    String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name;
    }
}
