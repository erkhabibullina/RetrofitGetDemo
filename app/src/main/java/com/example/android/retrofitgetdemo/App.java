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

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static JSONPlaceholderAPI jsonPlaceholderAPI;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                //
                .baseUrl(BASE_URL)
                //
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI.class);

        Log.d(TAG, "onCreate: create JSONPlaceholderAPI");
    }

    /**
     *
     * @return
     */
    public static JSONPlaceholderAPI getApi() {
        return jsonPlaceholderAPI;
    }
}
