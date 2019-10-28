package com.nagycsongor.planningpoker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String problem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Bundle args = getArguments();
        problem = args != null ? args.getString("voteTo") : "";

        if(!problem.isEmpty()) {

            recyclerView = view.findViewById(R.id.votes_recyclerView);
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new ListFragmentAdapter(view.getContext(), problem);
            recyclerView.setAdapter(mAdapter);
        }
        
        return view;
    }
}
