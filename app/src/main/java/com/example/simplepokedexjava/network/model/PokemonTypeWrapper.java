package com.example.simplepokedexjava.network.model;

import com.example.simplepokedexjava.utils.PokemonTextUtils;

public class PokemonTypeWrapper {
    PokemonType type;

    public String getTypeName() {
        return PokemonTextUtils.capitalize(type.name);
    }
}
