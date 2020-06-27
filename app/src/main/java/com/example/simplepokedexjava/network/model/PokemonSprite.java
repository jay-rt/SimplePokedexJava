package com.example.simplepokedexjava.network.model;

import com.squareup.moshi.Json;

public class PokemonSprite {
    @Json(name = "front_default")
    String imageUrl;
}
