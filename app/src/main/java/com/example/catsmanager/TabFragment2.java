package com.example.catsmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment2 extends Fragment {
    public TabFragment2() {
        // Required empty public constructor
    }
    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
           mWordList.addLast("Word " + i);
       }

        View view = inflater.inflate(R.layout.tab_fragment2, container, false);
        // Create recycler view.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(getActivity(), mWordList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.d("Test", "Intent Called");
                    Intent openAddPage = new Intent(getActivity(),AddTask.class);
                    startActivity(openAddPage);
            }
//                int wordListSize = mWordList.size();
//                // Add a new word to the wordList.
//                mWordList.addLast("+ Word " + wordListSize);
//
//
//
//                // Notify the adapter, that the data has changed.
//                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
//                // Scroll to the bottom.
//                mRecyclerView.smoothScrollToPosition(wordListSize);

        });
        return view;
    }
}
