package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Result> arrayList;
    //private final Context context;
    public MyAdapter(ArrayList<Result> arrayList){

        this.arrayList = arrayList ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
          holder.textView.setText(arrayList.get(position).getTitle());

          holder.textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(holder.context,SecondActivity.class);
//                  Bundle bundle = new Bundle();
//                  bundle.putSerializable("LIST",arrayList);
//                  intent.putExtra("LIST1",arrayList);
//                  intent.putExtras(bundle);
                  holder.context.startActivity(intent);
              }
          });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Api api = retrofit.create(Api.class);  //can't create new -> it's interface
//        Call<Result> call = api.getResponse();
//
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                //Log.d("kok",""+response.body().getResults().get(position).getTitle());
//                holder.textView.setText(response.body().getResults().get(position).getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//
//            }
//        });



//        Call<JsonObject> call = api.result();
//
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                JsonObject jsonObject = response.body();
//                JsonArray jsonArray = jsonObject.getAsJsonArray("results");
//                holder.textView.setText(jsonArray.get(position).getAsJsonObject().get("title").getAsString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final Context context;

        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
