package com.example.android.retrofitgetdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        recyclerView = (RecyclerView) findViewById(R.id.todos_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final TodoApapter adapter = new TodoApapter(new ArrayList<Todo>());
        recyclerView.setAdapter(adapter);

        App.getApi().getData().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                //
                List<Todo> todos = response.body();
                adapter.addTodos(todos);

                Log.d(TAG, "onResponse: response code: " + response.code());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: An error occurred during networking", t);
                Toast.makeText(MainActivity.this,
                        "An error occurred during networking",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
