package com.example.simplepokedexjava;

import android.app.Application;

import com.example.simplepokedexjava.data.ServiceGenerator;
import com.facebook.stetho.Stetho;

public class PokemonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceGenerator.initialize(getApplicationContext());
        Stetho.initializeWithDefaults(this);
    }
}
