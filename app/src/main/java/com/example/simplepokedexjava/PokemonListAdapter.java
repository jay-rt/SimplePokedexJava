package com.example.simplepokedexjava;

import android.content.Intent;
import android.graphics.Color;
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
    private OnPokemonClickListener listener;

    public PokemonListAdapter(OnPokemonClickListener listener) {
        this.listener = listener;
    }

    public void setPokemonInfos(List<PokemonInfo> pokemonInfos) {
        this.pokemonInfos = pokemonInfos;
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return PokemonListViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        holder.bind(pokemonInfos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return pokemonInfos.size();
    }

    public interface OnPokemonClickListener {
        void onPokemonClick (PokemonInfo pokemonInfo);
    }

    public static class PokemonListViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        private PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemTitle);
        }

        public void bind(PokemonInfo pokemonInfo, OnPokemonClickListener listener){
            textView.setText(pokemonInfo.getTitle());
            if(Integer.parseInt(pokemonInfo.getId()) % 10 == 0){
                textView.setTextColor(Color.RED);
            }else{
                textView.setTextColor(Color.parseColor("#3D3D3D"));
            }
            textView.setOnClickListener(v -> {
                listener.onPokemonClick(pokemonInfo);
            });
        }

        public static PokemonListViewHolder from(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pokemon_list_item, parent, false);
            return new PokemonListViewHolder(view);
        }
    }
}
