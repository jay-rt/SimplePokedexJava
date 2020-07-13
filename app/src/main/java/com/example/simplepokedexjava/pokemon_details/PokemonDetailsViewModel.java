package com.example.simplepokedexjava.pokemon_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplepokedexjava.data.ServiceGenerator;
import com.example.simplepokedexjava.data.network.model.PokemonDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailsViewModel extends ViewModel {

    private MutableLiveData<PokemonDetails> pokemonDetails = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> sharePokemon = new MutableLiveData<>();
    private MutableLiveData<String> browsePokemon = new MutableLiveData<>();

    public LiveData<PokemonDetails> getPokemonDetails() {
        return pokemonDetails;
    }

    public LiveData<Boolean> IsLoading() {
        return isLoading;
    }

    public LiveData<String> getSharePokemon() {
        return sharePokemon;
    }

    public LiveData<String> getBrowsePokemon() {
        return browsePokemon;
    }

    public PokemonDetailsViewModel(String pokemonName) {
        loadPokemonDetails(pokemonName);
    }

    private void loadPokemonDetails(String pokemonName) {
        isLoading.setValue(true);
        ServiceGenerator.getService().getPokemonDetails(pokemonName.toLowerCase()).enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                if(response.isSuccessful()) {
                    pokemonDetails.setValue(response.body());
//                    populateDetails();
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                isLoading.setValue(false);
            }
        });
    }

    public void onShareClick() {
        if(pokemonDetails.getValue() == null) {
            return;
        }
        sharePokemon.setValue(pokemonDetails.getValue().getName());
    }

    public void onShareComplete() {
        sharePokemon.setValue(null);
    }

    public void onBrowseClick() {
        if(pokemonDetails.getValue() == null) {
            return;
        }
        browsePokemon.setValue(pokemonDetails.getValue().getName());
    }

    public void onBrowseComplete() {
        browsePokemon.setValue(null);
    }
}
