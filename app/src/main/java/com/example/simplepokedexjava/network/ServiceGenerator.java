package com.example.simplepokedexjava.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private static ApiService SERVICE;

    private ServiceGenerator() {

    }

    public static ApiService getService() {
        if(SERVICE == null){
            initialize();
        }
        return SERVICE;
    }

    private static void initialize() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        SERVICE = retrofit.create(ApiService.class);

    }
}
