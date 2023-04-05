package com.example.pokemonwithapi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemonwithapi.model.Pokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel Alvarez on 28/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class ListePokiAdapter extends RecyclerView.Adapter<ListePokiAdapter.ViewHolder>{

    private ArrayList<Pokemon> dataset;
    private Context context;


    public ListePokiAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private ImageView fotoImageView;
        private TextView nombreTextView;
        Random random = new Random();

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int randomColor = Color.rgb(red, green, blue);
        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = itemView.findViewById(R.id.photo);
            nombreTextView = itemView.findViewById(R.id.title);
            linearLayout = itemView.findViewById(R.id.linearlayoutt);
            linearLayout.setBackgroundColor(randomColor);

        }
    }
}
