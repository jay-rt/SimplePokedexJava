package com.example.simplepokedexjava.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<LocalPokemonInfo> pokemonInfos);

    @Query("SELECT * FROM pokemon_infos")
    LiveData<List<LocalPokemonInfo>> getPokemonInfos();

    @Update
    void update(LocalPokemonInfo pokemonInfo);
}
