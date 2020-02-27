package com.example.android.retrofitgetdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoApapter extends RecyclerView.Adapter<TodoApapter.TodoViewHolder> {

    private static final String TAG = "TodoAdapter";

    private List<Todo> todoList;

    public TodoApapter(List<Todo> todoList) {
        //
        this.todoList = todoList;

        Log.d(TAG, "create new TodoAdapter");
    }

    /**
     *
     * @param newTodos
     */
    public void addTodos(List<Todo> newTodos) {
        todoList.addAll(newTodos);
        notifyDataSetChanged();

        Log.d(TAG, "addTodos: add new todos");
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_item_list, parent, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);

        //
        String title = todo.getTitle();
        //
        Boolean isCompleted = todo.isCompleted();

        CheckedTextView todoTextView = holder.getTodo();
        todoTextView.setText(title);
        //
        if (isCompleted) {
            todoTextView.setChecked(true);
        }

        Log.d(TAG, "onBindViewHolder: bind holder number " + (position + 1));
    }

    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        }

        return todoList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView mTodo;

        /**
         *
         * @return
         */
        public CheckedTextView getTodo() {
            return mTodo;
        }

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            //
            mTodo = (CheckedTextView) itemView.findViewById(R.id.tv_todo_title);
        }
    }
}
