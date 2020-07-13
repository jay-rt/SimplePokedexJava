package com.example.simplepokedexjava.pokemon_lists;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplepokedexjava.data.MainRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private MainRepository repository;

    public MainViewModelFactory(MainRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }

        throw new IllegalArgumentException("No valid view model");
    }
}
