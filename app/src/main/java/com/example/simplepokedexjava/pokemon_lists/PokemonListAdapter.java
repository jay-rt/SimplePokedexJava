package com.example.simplepokedexjava.pokemon_lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplepokedexjava.R;
import com.example.simplepokedexjava.data.database.LocalPokemonInfo;
import com.example.simplepokedexjava.databinding.ViewPokemonListItemBinding;
import com.example.simplepokedexjava.databinding.ViewPokemonListItemFavedBinding;
import com.squareup.picasso.Picasso;

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

    public enum PokemonListType {
        FAVED, NOT_FAVED
    }

    private OnPokemonClickListener listener;

    public PokemonListAdapter(OnPokemonClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        LocalPokemonInfo pokemonInfo = getItem(position);
        return pokemonInfo.isFaved() ?
                PokemonListType.FAVED.ordinal() :
                PokemonListType.NOT_FAVED.ordinal();
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return PokemonListViewHolder.from(parent, PokemonListType.values()[viewType]);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public interface OnPokemonClickListener {
        void onPokemonClick (LocalPokemonInfo pokemonInfo);
        void onFaveClick (LocalPokemonInfo pokemonInfo);
    }

    public static abstract class PokemonListViewHolder extends RecyclerView.ViewHolder{

        private PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(LocalPokemonInfo pokemonInfo, OnPokemonClickListener listener);

        public static PokemonListViewHolder from(ViewGroup parent, PokemonListType type) {
            switch (type) {
                case FAVED:
                    return FavedPokemonViewHolder.from(parent);
                case NOT_FAVED:
                    return NotFavedPokemonViewHolder.from(parent);
                default:
                    throw new IllegalArgumentException("Not Supported");
            }
        }
    }

    public static class NotFavedPokemonViewHolder extends PokemonListViewHolder {

        private ViewPokemonListItemBinding binding;

        private NotFavedPokemonViewHolder(@NonNull ViewPokemonListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LocalPokemonInfo pokemonInfo, OnPokemonClickListener listener){
            binding.itemTitle.setText(pokemonInfo.getTitle());
            binding.getRoot().setOnClickListener(v -> listener.onPokemonClick(pokemonInfo));

            binding.faveButton.setOnClickListener(v -> listener.onFaveClick(pokemonInfo));
        }

        public static NotFavedPokemonViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewPokemonListItemBinding binding = ViewPokemonListItemBinding.inflate(inflater, parent, false);
            return new NotFavedPokemonViewHolder(binding);
        }

    }

    public static class FavedPokemonViewHolder extends PokemonListViewHolder {

        private ViewPokemonListItemFavedBinding binding;

        public FavedPokemonViewHolder(@NonNull ViewPokemonListItemFavedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(LocalPokemonInfo pokemonInfo, OnPokemonClickListener listener) {
            binding.itemTitle.setText(pokemonInfo.getTitle());
            binding.getRoot().setOnClickListener(v -> listener.onPokemonClick(pokemonInfo));

            binding.faveButton.setOnClickListener(v -> listener.onFaveClick(pokemonInfo));

            Picasso.get().load(pokemonInfo.getImageUrl())
                    .fit()
                    .centerInside()
                    .into(binding.pokemonImage);

            String description = itemView.getContext().getString(
                    R.string.pokemon_description,
                    pokemonInfo.getHeight(),
                    pokemonInfo.getWeight());
            binding.description.setText(description);
        }

        public static FavedPokemonViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewPokemonListItemFavedBinding binding = ViewPokemonListItemFavedBinding
                    .inflate(inflater, parent, false);
            return new FavedPokemonViewHolder(binding);
        }
    }


}
