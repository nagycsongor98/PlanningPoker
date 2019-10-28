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
    private MyDatabase database;

    private static final String TAG = "VoteFragment";

    public VoteFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNumbers();
        final View view = inflater.inflate(R.layout.vote_fragment_layout, container, false);

        Bundle args = getArguments();
        final String userName = args != null ? args.getString("userName") : null;

        database = new MyDatabase(context);
        Log.i(TAG, "VoteFragment onCreate userName:" + userName);
        database.connectUser(userName);
        voteButton = view.findViewById(R.id.voteButton);
        problem = view.findViewById(R.id.voteProblem);
        recyclerView = view.findViewById(R.id.gridRecyclerView);
        adapter = new VoteAdapter(getContext(), numbers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
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

    @Override
    public void onResume() {
        super.onResume();
        final String voteTo = database.getNewVote();
        voteNumber = -1;
        if (voteTo.isEmpty()) {
            problem.setText("Nincs tobb task");
            voteButton.setClickable(false);
        } else {
            problem.setText(voteTo);

            voteButton.setClickable(true);
            voteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity.class);
                    int voteNumber = adapter.getVoteNumber();

                    switch (voteNumber) {
                        case 0: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "One", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 1);
                            break;
                        }
                        case 1: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Two", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 2);
                            break;
                        }
                        case 2: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Three", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 3);
                            break;
                        }
                        case 3: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Five", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 5);
                            break;
                        }
                        case 4: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Seven", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 7);
                            break;
                        }
                        case 5: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Ten", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 10);
                            break;
                        }
                        case 6: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Twenty", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 20);
                            break;
                        }
                        case 7: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Fifty", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 50);

                            break;
                        }
                        case 8: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "One Hundred", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, 100);

                            break;
                        }
                        case 9: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "?", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, -1);
                            break;
                        }
                        case 10: {
                            intent.putExtra("voteTo", voteTo);
                            startActivity(intent);
                            Toast.makeText(context, "Coffee Time!", Toast.LENGTH_SHORT).show();
                            database.votedTo(voteTo, -2);
                            break;
                        }
                        default: {
                            Toast.makeText(context, "Please click a value!", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
        }
    }
}
