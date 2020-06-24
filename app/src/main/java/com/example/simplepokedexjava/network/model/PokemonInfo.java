package com.example.simplepokedexjava.network.model;

public class PokemonInfo {
    String name;
    String url;

    public String getName() {
        return name.substring(0,1).toUpperCase() + name.substring(1);
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
