package com.example.android.retrofitgetdemo;

import android.app.Application;
import android.util.Log;

import com.example.android.retrofitgetdemo.api.JSONPlaceholderAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String TAG = "App";

    // An URL which we use to take fake data
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static JSONPlaceholderAPI jsonPlaceholderAPI;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        /* This is simple HTTP client, where we have set connection and reading timeout to
         * 60 seconds, and writing timeout to 120 seconds.
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        /* Here we build a new Retrofit using client and build method, set the base URL
         * of the remote service, add converter factory for serialization and
         * deserialization of objects.
         */
        retrofit = new Retrofit.Builder()
                //
                .baseUrl(BASE_URL)
                //
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        /* Generates an implementation of the jsonPlaceholderAPI interface */
        jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI.class);

        Log.d(TAG, "onCreate: create JSONPlaceholderAPI");
    }

    /**
     * This method simply returns API from JSONPlaceholderAPI
     * @return API from JSONPlaceholderAPI
     */
    public static JSONPlaceholderAPI getApi() {
        return jsonPlaceholderAPI;
    }
}
