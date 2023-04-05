
package com.example.pokemonwithapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private ArrayList<Pokemon> results= new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
