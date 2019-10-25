package com.nagycsongor.planningpoker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.MyViewHolder> {
    private ArrayList<String> data;
    private Context context;
    private String problem;

    public ListFragmentAdapter(Context context, String problem) {
        this.context = context;
        this.problem = problem;
        MyDatabase myDatabase = new MyDatabase(context);
        data = myDatabase.getVotes(problem);
    }

    @NonNull
    @Override
    public ListFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return  new ListFragmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFragmentAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
