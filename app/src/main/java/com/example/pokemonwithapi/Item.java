package com.example.pokemonwithapi;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemonwithapi.model.Pokemon;
import com.example.pokemonwithapi.service.PokemonService;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Item extends AppCompatActivity {
int id;
private int currentProgress = 0;
private ProgressBar progressBar1;
private ProgressBar progressBar2;
private ProgressBar progressBar3;
    ImageView imageView;
    RelativeLayout relativeLayout;
    TextView textView;
    TextView textViewHeight;
    TextView textViewWeight;

    Retrofit retrofit;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        progressBar1=findViewById(R.id.progressBar);
        progressBar2=findViewById(R.id.progressBar2);
        progressBar3=findViewById(R.id.progressBar3);

        progressBar1.setProgress(80);
        progressBar2.setProgress(60);
        progressBar3.setProgress(20);

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int randomColor = Color.rgb(red, green, blue);
        relativeLayout= findViewById(R.id.layoutt);
        imageView = findViewById(R.id.photo);
        textView = findViewById(R.id.description);
        textViewHeight = findViewById(R.id.label2);
        textViewWeight = findViewById(R.id.label1);


        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service = retrofit.create(PokemonService.class);
        Call<Pokemon> pokemonRespuestaCall = service.poki(id);

        pokemonRespuestaCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokeDetail = response.body();
                    System.out.println(" text  :  "+pokeDetail.toString());
                    textView.setText(pokeDetail.getName());
                    textViewHeight.setText(pokeDetail.getHeight()+"M");
                    textViewWeight.setText(pokeDetail.getWeight()+"KG");

                } else {
                    Log.e("tag", " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("tag", " onFailure: " + t.getMessage());
            }
        });
    }

    private void startAnimationCounter(int start_no ,int end_no){
        ValueAnimator animator = ValueAnimator.ofInt(start_no, end_no);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // binding.tvcounter.setText(animation.getAnimatedValue().toString()+"");
                //binding.progressbar.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
                animator.start();
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //binding=null;
    }
}