package com.example.simplepokedexjava.pokemon_lists;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplepokedexjava.R;
import com.example.simplepokedexjava.data.database.LocalPokemonInfo;
import com.example.simplepokedexjava.databinding.ViewPokemonListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends ListAdapter<LocalPokemonInfo, PokemonListAdapter.PokemonListViewHolder> {

    private static DiffUtil.ItemCallback<LocalPokemonInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<LocalPokemonInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull LocalPokemonInfo oldItem, @NonNull LocalPokemonInfo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull LocalPokemonInfo oldItem, @NonNull LocalPokemonInfo newItem) {
            return oldItem.isFaved().equals(newItem.isFaved());
        }
    };

    private OnPokemonClickListener listener;

    public PokemonListAdapter(OnPokemonClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return PokemonListViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public interface OnPokemonClickListener {
        void onPokemonClick (LocalPokemonInfo pokemonInfo);
        void onFaveClick (LocalPokemonInfo pokemonInfo);
    }

    public static class PokemonListViewHolder extends RecyclerView.ViewHolder{

        private ViewPokemonListItemBinding binding;

        private PokemonListViewHolder(@NonNull ViewPokemonListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LocalPokemonInfo pokemonInfo, OnPokemonClickListener listener){
            binding.itemTitle.setText(pokemonInfo.getTitle());
            binding.getRoot().setOnClickListener(v -> {
                listener.onPokemonClick(pokemonInfo);
            });

            if (pokemonInfo.isFaved()) {
                binding.faveButton.setImageResource(R.drawable.ic_baseline_favorite_24);
            }else {
                binding.faveButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            }

            binding.faveButton.setOnClickListener(v -> {
                listener.onFaveClick(pokemonInfo);
            });
        }

        public static PokemonListViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewPokemonListItemBinding binding = ViewPokemonListItemBinding.inflate(inflater, parent, false);
            return new PokemonListViewHolder(binding);
        }
    }
}
