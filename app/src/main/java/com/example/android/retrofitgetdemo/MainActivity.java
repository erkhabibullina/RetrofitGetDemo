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

        // A recycler view for our activity
        recyclerView = (RecyclerView) findViewById(R.id.todos_recycle_view);

        /*
         * A LayoutManager is responsible for measuring and positioning item views
         * within a RecyclerView as well as determining the policy for when to recycle item
         * views that are no longer visible to the user.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        /* setLayoutManager associates the LayoutManager we created above with out RecyclerView */
        recyclerView.setLayoutManager(layoutManager);

        /* The TodoAdapter is responsible for linking our to-do data with the Views that
         * will end up displaying our to-do data.
         */
        final TodoAdapter adapter = new TodoAdapter(new ArrayList<Todo>());

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        recyclerView.setAdapter(adapter);

        /*
         * Here we use enqueue instead of execute because we want to make our calls
         * asynchronously. When we get a Response object, we proceed with it to
         * onResponse method.
         */
        App.getApi().getData().enqueue(new Callback<List<Todo>>() {

            /* Here we get parsed response by using body method, and then add it to our adapter*/
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                List<Todo> todos = response.body();
                adapter.addTodos(todos);

                Log.d(TAG, "onResponse: response code: " + response.code());
            }
            /* If something went wrong, we handle this in onFailure method.*/
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
