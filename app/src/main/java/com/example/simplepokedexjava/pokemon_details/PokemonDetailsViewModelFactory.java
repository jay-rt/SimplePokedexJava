package com.example.simplepokedexjava.pokemon_details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PokemonDetailsViewModelFactory implements ViewModelProvider.Factory {

    private String pokemonName;

    public PokemonDetailsViewModelFactory(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PokemonDetailsViewModel.class)) {
            return (T) new PokemonDetailsViewModel(pokemonName);
        }

        throw new IllegalArgumentException("No valid view model");
    }
}
