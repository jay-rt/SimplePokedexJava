package com.example.simplepokedexjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplepokedexjava.network.model.PokemonInfo;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder> {

    private List<PokemonInfo> pokemonInfos = new ArrayList<>();

    public void setPokemonInfos(List<PokemonInfo> pokemonInfos) {
        this.pokemonInfos = pokemonInfos;
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pokemon_list_item, parent, false);
        PokemonListViewHolder viewHolder = new PokemonListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.itemTitle);
        textView.setText("Title" + position);
    }

    @Override
    public int getItemCount() {
        return pokemonInfos.size();
    }

    public static class PokemonListViewHolder extends RecyclerView.ViewHolder{
        public PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
