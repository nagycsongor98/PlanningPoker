package com.nagycsongor.planningpoker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> items;

    public VoteAdapter(Context context, ArrayList<Integer> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
//        Card card = new Card(view);
        ViewHolder viewHolder = new ViewHolder(view);
//        return card;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//      holder.number.setText(items.get(position));
//        holder.button.setBackground();
        holder.imageButton.setBackgroundResource(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //    public static class Card extends RecyclerView.ViewHolder {
    public class ViewHolder extends RecyclerView.ViewHolder {

//        TextView number;
//        Button button;
        ImageButton imageButton;
        ConstraintLayout parentLayout;

        //        public Card(@NonNull View itemView) {
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
//            number = itemView.findViewById(R.id.cardNumber);
//            button = itemView.findViewById(R.id.cardButton);
            imageButton = itemView.findViewById(R.id.cardImageButton);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
