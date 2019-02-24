package com.example.catsmanager;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Cat> mCatsData;
    private CatsAdapter mAdapter;

    public TabFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment3, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Initialize the ArrayList that will contain the data.
        mCatsData = new ArrayList<Cat>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new CatsAdapter(getActivity(),mCatsData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        return view;
    }

    /**
     * Initialize the cats data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] catsList = getResources()
                .getStringArray(R.array.cats_titles);
        String[] catsInfo = getResources()
                .getStringArray(R.array.cats_info);
        String[] catsDate = getResources()
                .getStringArray(R.array.cats_dates);
        TypedArray catsImageResources =
                getResources().obtainTypedArray(R.array.cats_images);

        // Clear the existing data (to avoid duplication).
        mCatsData.clear();

        // Create the ArrayList of Cats objects with titles and
        // information about each C.
        for(int i=0;i<catsList.length;i++){
            mCatsData.add(new Cat(catsList[i], "Spent "+catsInfo[i]+" of focused time"
                    ,catsDate[i], -6, 120, catsImageResources.getResourceId(i,0)));
        }

        catsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }


}
