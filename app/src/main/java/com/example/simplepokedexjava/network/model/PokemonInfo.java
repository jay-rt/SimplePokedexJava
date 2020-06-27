package com.example.simplepokedexjava.network.model;

import com.example.simplepokedexjava.utils.PokemonTextUtils;

public class PokemonInfo {
    String name;
    String url;

    public String getName() {
        return PokemonTextUtils.capitalize(name);
    }

    public String getUrl() {
        return url;
    }

    public String getId(){
        String result = url.substring(0, url.lastIndexOf("/"));
        return result.substring(result.lastIndexOf("/") + 1);
    }

    public String getTitle(){
        return getId() + ". " + getName();
    }
}
