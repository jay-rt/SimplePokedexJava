package com.example.simplepokedexjava.utils;

public class PokemonTextUtils {
    private PokemonTextUtils() {
        //private
    }

    public static String capitalize(String text){
        if(text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0,1).toUpperCase() + text.substring(1);
    }
}
