package com.example.simplepokedexjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.simplepokedexjava.databinding.ActivityPokemonDetailsBinding;
import com.example.simplepokedexjava.network.ServiceGenerator;
import com.example.simplepokedexjava.network.model.PokemonDetails;
import com.example.simplepokedexjava.network.model.PokemonTypeWrapper;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailsActivity extends AppCompatActivity {

    private ActivityPokemonDetailsBinding binding;
    private PokemonDetails pokemonDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String pokemonName = getIntent().getStringExtra(PokemonConst.POKEMON_NAME);
        binding.loading.setVisibility(View.VISIBLE);
        binding.pokemonDetailsContainer.setVisibility(View.GONE);

        ServiceGenerator.getService().getPokemonDetails(pokemonName.toLowerCase()).enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                if(response.isSuccessful()) {
                    pokemonDetails = response.body();
                    populateDetails();
                }
            }

            private void populateDetails() {

                binding.loading.setVisibility(View.GONE);
                binding.pokemonDetailsContainer.setVisibility(View.VISIBLE);

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
            public void onFailure(Call<PokemonDetails> call, Throwable t) {

            }
        });
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
                shareUrl();
                return true;
            case R.id.open_in_browser:
                openInBrowser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openInBrowser() {
        Uri uri = Uri.parse(PokemonConst.POKEMON_WIKI + pokemonDetails.getName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void shareUrl() {
        Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, PokemonConst.POKEMON_WIKI + pokemonDetails.getName());

        Intent chooser = Intent.createChooser(intent, getString(R.string.share_pokemon));
        startActivity(chooser);
    }
}