package com.example.android.retrofitgetdemo.api;

import com.example.android.retrofitgetdemo.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderAPI {

    @GET("/todos")
    Call<List<Todo>> getData();
}
