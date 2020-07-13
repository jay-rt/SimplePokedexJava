package com.example.simplepokedexjava.data.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_infos")
public class LocalPokemonInfo {

    @PrimaryKey
    Integer id;
    String name;
    Boolean faved = false;

    @Ignore
    public LocalPokemonInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public LocalPokemonInfo(Integer id, String name, Boolean faved) {
        this.id = id;
        this.name = name;
        this.faved = faved;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return id + " " + name;
    }

    public Boolean isFaved() {
        return faved;
    }

    public void setFaved(Boolean faved) {
        this.faved = faved;
    }
}
