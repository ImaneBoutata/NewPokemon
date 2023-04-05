package com.example.pokemonwithapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonwithapi.model.Pokemon;
import com.example.pokemonwithapi.model.PokemonResponse;
import com.example.pokemonwithapi.service.PokemonService;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

private static final String tag = "POKI";
    private Retrofit retrofit;

    ///private RecyclerViewInterface recyclerViewInterface;
    private RecyclerView recyclerView;
    private ListePokiAdapter listePokiAdapter;
    private ArrayList<Pokemon> listaPokemon = new ArrayList<>();

    private int offset;

    private boolean aptoParaCargar;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        listePokiAdapter = new ListePokiAdapter(this);

        recyclerView.setAdapter(listePokiAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                            aptoParaCargar = false;
                            offset += 20;
                            data(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;
        data(offset);
        recyclerView.addOnItemTouchListener(new Listner(this, recyclerView, new Listner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Pokemon pokemon1 = listaPokemon.get(position);
                Intent intent = new Intent(MainActivity.this, Item.class);
                intent.putExtra("id", position+1);

                Log.d(tag, "Selected Pokemon: " + pokemon1.getName());


                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    private  void data(int offset){PokemonService service = retrofit.create(PokemonService.class);
        Call<PokemonResponse> pokemonRespuestaCall = service.pokiListe(20, offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {
                    PokemonResponse pokemonRespuesta = response.body();
                    listaPokemon = pokemonRespuesta.getResults();

                    listePokiAdapter.adicionarListaPokemon(listaPokemon);
                } else {
                    Log.e(tag, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(tag, " onFailure: " + t.getMessage());
            }
        });
    }

}