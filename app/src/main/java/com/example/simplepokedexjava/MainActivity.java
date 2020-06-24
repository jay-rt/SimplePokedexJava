package com.example.simplepokedexjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplepokedexjava.network.ServiceGenerator;
import com.example.simplepokedexjava.network.model.PokemonListWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView pokemonRecyclerView = findViewById(R.id.pokemonRecyclerView);
        PokemonListAdapter adapter = new PokemonListAdapter(pokemonInfo -> {
            Toast.makeText(this, pokemonInfo.getName() + ", I choose you!", Toast.LENGTH_SHORT).show();
        });
        pokemonRecyclerView.setAdapter(adapter);

        ServiceGenerator.getService().getPokemons().enqueue(new Callback<PokemonListWrapper>() {
            @Override
            public void onResponse(Call<PokemonListWrapper> call, Response<PokemonListWrapper> response) {
                if(response.body() != null && response.isSuccessful()){
                    adapter.setPokemonInfos(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokemonListWrapper> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}