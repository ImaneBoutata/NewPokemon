package com.example.pokemonwithapi.model;

import com.google.gson.annotations.SerializedName;

public class Pokemon {

     private int id;
     private int height;
     private int weight;
     private String name;

     private int number;
     private String url;
    @SerializedName("base_experience")
    private int baseExperience;

    public int getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }


    public Pokemon(int id, int height, int weight, String name, int baseExperience) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.baseExperience = baseExperience;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", baseExperience=" + baseExperience +
                '}';
    }
}
