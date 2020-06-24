package com.example.simplepokedexjava.network;

import com.example.simplepokedexjava.network.model.PokemonListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("pokemon/?limit=807")
    Call<PokemonListWrapper> getPokemons();
}
