package com.nagycsongor.planningpoker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> items;
    private Integer voteNumber;

    public VoteAdapter(Context context, ArrayList<Integer> items) {
        this.context = context;
        this.items = items;
        this.voteNumber = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imageButton.setBackgroundResource(items.get(position));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteNumber = position;
//                Toast.makeText(context,"Card is clicked " + position, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public int getVoteNumber() {
//        Toast.makeText(context,"VoteNumber " + this.voteNumber, Toast.LENGTH_LONG).show();
        return this.voteNumber;

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageButton imageButton;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageButton = itemView.findViewById(R.id.cardImageButton);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }


    }
    
}
