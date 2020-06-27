package com.example.simplepokedexjava.network.model;

import com.example.simplepokedexjava.utils.PokemonTextUtils;
import com.squareup.moshi.Json;

import java.util.List;
import java.util.Locale;

public class PokemonDetails {
    Integer id;
    String name;
    Double weight;
    Double height;
    @Json(name = "base_experience")
    Integer baseExp;
    PokemonSprite sprites;
    List<PokemonTypeWrapper> types;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return id + ". " + PokemonTextUtils.capitalize(name);
    }

    public String getBaseExp() {
        return String.valueOf(baseExp);
    }

    public String getWeightString() {
        return String.format(Locale.US, "%.1f kg", weight/10);
    }

    public String getHeightString() {
        return String.format(Locale.US, "%.1f m", height/10);
    }

    public String getSpriteUrl() {
        return sprites.imageUrl;
    }

    public List<PokemonTypeWrapper> getTypes() {
        return types;
    }
}
