package com.example.myapplication;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.FeatureGroupInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner spinner;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Result> arrayMovies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pageSelect));
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(View.VISIBLE);
                arrayMovies.clear();
                retrieveData(position+1);
                Log.d("kok","im hereree"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //retrieveData(1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }



    public void retrieveData(int page){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/")
                .build();

        Api api = retrofit.create(Api.class);
        Call<Pojo> call = api.getResults(page);

        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                for(int i = 0; i<response.body().getResults().size(); i++){
                    arrayMovies.add(response.body().getResults().get(i));
                }
                mAdapter = new MyAdapter(arrayMovies);
                recyclerView.setAdapter(mAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.d("kok","failll");
            }
        });



    }


}
