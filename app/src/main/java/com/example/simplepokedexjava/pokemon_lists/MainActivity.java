package com.example.simplepokedexjava.pokemon_lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;

import com.example.simplepokedexjava.data.MainRepository;
import com.example.simplepokedexjava.data.database.LocalPokemonInfo;
import com.example.simplepokedexjava.utils.PokemonConst;
import com.example.simplepokedexjava.pokemon_details.PokemonDetailsActivity;
import com.example.simplepokedexjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainRepository repository = new MainRepository();
        MainViewModelFactory factory = new MainViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        
        PokemonListAdapter adapter = new PokemonListAdapter(new PokemonListAdapter.OnPokemonClickListener() {
            @Override
            public void onPokemonClick(LocalPokemonInfo pokemonInfo) {
                viewModel.onPokemonClick(pokemonInfo);
            }

            @Override
            public void onFaveClick(LocalPokemonInfo pokemonInfo) {
                viewModel.onFaveClick(pokemonInfo);
            }
        });
        binding.pokemonRecyclerView.setAdapter(adapter);

        viewModel.getPokemonInfos().observe(this, adapter::submitList);

        viewModel.getPokemonDetailsInfo().observe(this, pokemonInfo -> {
            if(pokemonInfo != null) {
                Intent intent = new Intent(this, PokemonDetailsActivity.class);
                intent.putExtra(PokemonConst.POKEMON_NAME, pokemonInfo.getName());
                startActivity(intent);
                viewModel.onPokemonNavigationComplete();
            }
        });
    }
}