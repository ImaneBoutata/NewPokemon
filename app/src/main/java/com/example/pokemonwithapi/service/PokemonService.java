package com.example.pokemonwithapi.service;


import com.example.pokemonwithapi.model.Pokemon;
import com.example.pokemonwithapi.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {

    @GET("pokemon/")
    public Call<PokemonResponse> pokiListe(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    public Call<Pokemon> poki(@Path("id") int id);

}
