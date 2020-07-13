package com.example.simplepokedexjava.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LocalPokemonInfo.class}, version = 2)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao getPokemonDao();
}
