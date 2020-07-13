package com.example.simplepokedexjava.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simplepokedexjava.data.database.LocalPokemonInfo;
import com.example.simplepokedexjava.data.database.PokemonDatabase;
import com.example.simplepokedexjava.data.network.model.PokemonInfo;
import com.example.simplepokedexjava.data.network.model.PokemonListWrapper;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private PokemonDatabase database = ServiceGenerator.getDatabase();

    public LiveData<List<LocalPokemonInfo>> getPokemonInfos() {
        return database.getPokemonDao().getPokemonInfos();
    }

    public void loadPokemonLists() {
        ServiceGenerator.getService().getPokemons().enqueue(new Callback<PokemonListWrapper>() {
            @Override
            public void onResponse(Call<PokemonListWrapper> call, Response<PokemonListWrapper> response) {
                if(response.body() != null && response.isSuccessful()){
                    List<PokemonInfo> httpRequest = response.body().getResults();
                    List<LocalPokemonInfo> result = httpRequest.stream()
                            .map(it -> new LocalPokemonInfo(Integer.parseInt(it.getId()), it.getName()))
                            .collect(Collectors.toList());

                    AsyncTask.execute(() -> database.getPokemonDao().insertAll(result));
                }
            }

            @Override
            public void onFailure(Call<PokemonListWrapper> call, Throwable t) {
            }
        });
    }

    public void savePokemon(LocalPokemonInfo info) {
        AsyncTask.execute(() -> database.getPokemonDao().update(info));
    }
}
