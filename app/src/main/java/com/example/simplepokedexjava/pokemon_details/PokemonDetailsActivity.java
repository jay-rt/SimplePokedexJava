package com.example.simplepokedexjava.pokemon_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.simplepokedexjava.utils.PokemonConst;
import com.example.simplepokedexjava.R;

import com.example.simplepokedexjava.databinding.ActivityPokemonDetailsBinding;
import com.example.simplepokedexjava.data.network.model.PokemonDetails;
import com.example.simplepokedexjava.data.network.model.PokemonTypeWrapper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonDetailsActivity extends AppCompatActivity {

    private ActivityPokemonDetailsBinding binding;
    private PokemonDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String pokemonName = getIntent().getStringExtra(PokemonConst.POKEMON_NAME);
        viewModel = new ViewModelProvider(this, new PokemonDetailsViewModelFactory(pokemonName))
                .get(PokemonDetailsViewModel.class);

        viewModel.getPokemonDetails().observe(this, this::populateDetails);

        viewModel.IsLoading().observe(this, isLoading -> {
            if(isLoading != null && isLoading) {
                binding.loading.setVisibility(View.VISIBLE);
                binding.pokemonDetailsContainer.setVisibility(View.GONE);
            }else {
                binding.loading.setVisibility(View.GONE);
                binding.pokemonDetailsContainer.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getSharePokemon().observe(this, name -> {
            if (name != null) {
                shareUrl(name);
                viewModel.onShareComplete();
            }
        });

        viewModel.getBrowsePokemon().observe(this, name -> {
            if (name != null) {
                openInBrowser(name);
                viewModel.onBrowseComplete();
            }
        });


    }

    private void populateDetails(PokemonDetails pokemonDetails) {
        if(pokemonDetails == null) {
            return;
        }

        //title
        binding.pokemonTitle.setText(pokemonDetails.getTitle());
        //base exp
        binding.baseExpValue.setText(pokemonDetails.getBaseExp());
        //weight
        binding.weightVal.setText(pokemonDetails.getWeightString());
        //height
        binding.heightVal.setText(pokemonDetails.getHeightString());
        //types
        List<PokemonTypeWrapper> types = pokemonDetails.getTypes();
        if(types.size() == 1) {
            binding.typeVal1.setText(types.get(0).getTypeName());
            binding.typeVal2.setVisibility(View.GONE);
        }else if (types.size() >=2) {
            binding.typeVal1.setText(types.get(0).getTypeName());
            binding.typeVal2.setText(types.get(1).getTypeName());
        }

        //sprite
        Picasso.get().load(pokemonDetails.getSpriteUrl())
                .fit()
                .centerInside()
                .into(binding.pokemonImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.share:
                viewModel.onShareClick();
                return true;
            case R.id.open_in_browser:
                viewModel.onBrowseClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openInBrowser(String pokemonName) {
        Uri uri = Uri.parse(PokemonConst.POKEMON_WIKI + pokemonName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void shareUrl(String pokemonName) {
        Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, PokemonConst.POKEMON_WIKI + pokemonName);

        Intent chooser = Intent.createChooser(intent, getString(R.string.share_pokemon));
        startActivity(chooser);
    }
}