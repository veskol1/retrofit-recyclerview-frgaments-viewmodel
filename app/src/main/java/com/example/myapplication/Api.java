package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //@GET("3/movie/top_rated?api_key=5b609fa354e920b753e95dc5f803bbdc&language=en-US&page=1")
    @GET("3/movie/top_rated?api_key=5b609fa354e920b753e95dc5f803bbdc&language=en-US")
    Call<Pojo> getResults( @Query("page") int currentPage);
}
