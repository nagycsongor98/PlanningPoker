package com.nagycsongor.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteFragment extends Fragment {

    private RecyclerView recyclerView;
    private VoteAdapter adapter;
    private ArrayList<Integer> numbers = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNumbers();
        View view = inflater.inflate(R.layout.vote_fragment_layout, container, false);


        recyclerView = view.findViewById(R.id.gridRecyclerView);
        adapter = new VoteAdapter(getContext(), numbers);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        return view;
    }

    private void addNumbers()
    {

//        numbers.add(R.drawable.ic_one);
//        numbers.add(R.drawable.ic_two);
//        numbers.add(R.drawable.ic_three);
//        numbers.add(R.drawable.ic_five);
//        numbers.add(R.drawable.ic_seven);
//        numbers.add(R.drawable.ic_one_hundred);
//        numbers.add(R.drawable.ic_question_mark);
//        numbers.add(R.drawable.ic_coffee_cup);


    }

}
