package com.example.android.retrofitgetdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private static final String TAG = "TodoAdapter";

    // List of to-do tasks
    private List<Todo> todoList;

    /**
     * Creates a TodoAdapter.
     *
     * @param todoList Data that we will use in our app.
     */
    public TodoAdapter(List<Todo> todoList) {
        //
        this.todoList = todoList;

        Log.d(TAG, "create new TodoAdapter");
    }

    /**
     * This method just adds new to-do tasks to our List of tasks.
     *
     * @param newTodos new tasks
     */
    public void addTodos(List<Todo> newTodos) {
        todoList.addAll(newTodos);
        notifyDataSetChanged();

        Log.d(TAG, "addTodos: add new todos");
    }


    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType If your RecyclerView has more than one type of item (which our doesn't) you
     *                 can use this viewType integer to provide a different layout. See
     *                 {@link androidx.recyclerview.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                 for more details.
     * @return A new TodoViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_item_list, parent, false);

        return new TodoViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the tasks
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder The ViewHolder which should be updated to represent the
     *               contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);

        // Getting a tasks' title
        String title = todo.getTitle();
        // Checking whether task is completed or not
        Boolean isCompleted = todo.isCompleted();

        CheckedTextView todoTextView = holder.getTodo();
        todoTextView.setText(title);
        // Setting "checked" on our CheckedTextView if task is completed.
        if (isCompleted) {
            todoTextView.setChecked(true);
        }

        Log.d(TAG, "onBindViewHolder: bind holder number " + (position + 1));
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our list of tasks.
     */
    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        }

        return todoList.size();
    }

    /**
     *  A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     *  a cache of the child views for a forecast item.
     */

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView mTodo;

        /**
         * This method is used to get particular to-do task.
         * @return to-do task
         */
        public CheckedTextView getTodo() {
            return mTodo;
        }

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            // A CheckedTextView for our activity
            mTodo = (CheckedTextView) itemView.findViewById(R.id.tv_todo_title);
        }
    }
}
