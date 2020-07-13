package com.example.simplepokedexjava.pokemon_lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplepokedexjava.data.MainRepository;
import com.example.simplepokedexjava.data.database.LocalPokemonInfo;

import java.util.List;

public class MainViewModel extends ViewModel {


    private MainRepository repository;
    private MutableLiveData<LocalPokemonInfo> pokemonDetailsInfo = new MutableLiveData<>();

    public MainViewModel(MainRepository repository) {
        this.repository = repository;
        repository.loadPokemonLists();
    }

    public LiveData<List<LocalPokemonInfo>> getPokemonInfos() {
        return repository.getPokemonInfos();
    }

    public LiveData<LocalPokemonInfo> getPokemonDetailsInfo() {
        return pokemonDetailsInfo;
    }

    public void onPokemonClick(LocalPokemonInfo pokemonInfo) {
        pokemonDetailsInfo.setValue(pokemonInfo);
    }

    public void onPokemonNavigationComplete() {
        pokemonDetailsInfo.setValue(null);
    }

    public void onFaveClick(LocalPokemonInfo info) {
        //Update our fave state
        LocalPokemonInfo newInfo = new LocalPokemonInfo(info.getId(), info.getName(), !info.isFaved());
        //Save to our database
        repository.savePokemon(newInfo);
    }
}
