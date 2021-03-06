package com.example.simplepokedexjava.data.network;

import com.example.simplepokedexjava.data.network.model.PokemonDetails;
import com.example.simplepokedexjava.data.network.model.PokemonListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("pokemon/?limit=807")
    Call<PokemonListWrapper> getPokemons();

    @GET("pokemon/{pokemon_name}")
    Call<PokemonDetails> getPokemonDetails(@Path("pokemon_name") String pokemonName);
}
