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
    String weight;
    String height;
    String imageUrl;

    @Ignore
    public LocalPokemonInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public LocalPokemonInfo(Integer id, String name, Boolean faved) {
        this.id = id;
        this.name = name;
        this.faved = faved;
    }

    public LocalPokemonInfo(Integer id, String name, Boolean faved, String weight, String height, String imageUrl) {
        this.id = id;
        this.name = name;
        this.faved = faved;
        this.weight = weight;
        this.height = height;
        this.imageUrl = imageUrl;
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

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
