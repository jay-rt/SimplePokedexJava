package com.example.simplepokedexjava.data;

import android.content.Context;

import androidx.room.Room;

import com.example.simplepokedexjava.data.database.PokemonDatabase;
import com.example.simplepokedexjava.data.network.ApiService;
import com.example.simplepokedexjava.utils.PokemonConst;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {

    private static PokemonDatabase DATABASE;
    private static ApiService SERVICE;

    private ServiceGenerator() {

    }

    public static ApiService getService() {
        return SERVICE;
    }

    public static PokemonDatabase getDatabase() {
        return DATABASE;
    }

    public static void initialize(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokemonConst.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        SERVICE = retrofit.create(ApiService.class);

        DATABASE = Room.databaseBuilder(context,
                PokemonDatabase.class,
                 "pokemon_database")
                .fallbackToDestructiveMigration()
                .build();

    }
}
