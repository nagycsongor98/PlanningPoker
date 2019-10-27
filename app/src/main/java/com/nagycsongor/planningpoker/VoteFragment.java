package com.nagycsongor.planningpoker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteFragment extends Fragment {

    private RecyclerView recyclerView;
    public VoteAdapter adapter;
    private ArrayList<Integer> numbers = new ArrayList<>();
    private TextView problem;
    private Button voteButton;
    public Context context;
    public Integer voteNumber;

    private static final String TAG = "VoteFragment";

    public VoteFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNumbers();
        View view = inflater.inflate(R.layout.vote_fragment_layout, container, false);

        Bundle args = getArguments();
        String userName = args != null ? args.getString("userName") : null;

        MyDatabase database = new MyDatabase(context);
        Log.i(TAG,  "VoteFragment onCreate userName:" + userName);
        database.connectUser(userName);

        String voteTo = database.getNewVote();
        if (voteTo.isEmpty()) {
            Log.i(TAG, "Don't have vote");
        } else {
            Log.i(TAG, "Next vote: " + voteTo);

        }
        voteNumber = -1;
        voteButton = view.findViewById(R.id.voteButton);
        problem = view.findViewById(R.id.voteProblem);
        recyclerView = view.findViewById(R.id.gridRecyclerView);


        problem.setText(voteTo);
        adapter = new VoteAdapter(getContext(),numbers);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ListFragment.class);
                int voteNumber = adapter.getVoteNumber();
                Toast.makeText(context,"Number " + voteNumber, Toast.LENGTH_LONG).show();
                switch (voteNumber) {
                    case 0 : {
                        intent.putExtra("voteNumber","1");
                        Toast.makeText(context,"One", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 1 : {
                        intent.putExtra("voteNumber","2");
                        Toast.makeText(context,"Two", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2 : {
                        intent.putExtra("voteNumber","3");
                        Toast.makeText(context,"Three", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 3 : {
                        intent.putExtra("voteNumber","5");
                        Toast.makeText(context,"Five", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 4 : {
                        intent.putExtra("voteNumber","7");
                        Toast.makeText(context,"Seven", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 5 : {
                        intent.putExtra("voteNumber","10");
                        Toast.makeText(context,"Ten", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 6 : {
                        intent.putExtra("voteNumber","20");
                        Toast.makeText(context,"Twenty", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 7 : {
                        intent.putExtra("voteNumber","50");
                        Toast.makeText(context,"Fifty", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 8 : {
                        intent.putExtra("voteNumber","100");
                        Toast.makeText(context,"100", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 9 : {
                        intent.putExtra("voteNumber","?");
                        Toast.makeText(context,"Question Mark", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 10 : {
                        intent.putExtra("voteNumber","Coffee");
                        Toast.makeText(context,"Coffee Time!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default : {
                        Toast.makeText(context,"Please click a value!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        return view;
    }

    private void addNumbers() {

        numbers.add(R.drawable.ic_one);
        numbers.add(R.drawable.ic_two);
        numbers.add(R.drawable.ic_three);
        numbers.add(R.drawable.ic_five);
        numbers.add(R.drawable.ic_seven);
        numbers.add(R.drawable.ic_ten);
        numbers.add(R.drawable.ic_twenty);
        numbers.add(R.drawable.ic_fifty);
        numbers.add(R.drawable.ic_one_hundred);
        numbers.add(R.drawable.ic_question);
        numbers.add(R.drawable.ic_coffee_cup);


    }


}
