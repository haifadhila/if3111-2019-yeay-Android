package com.example.catsmanager;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<CatData> mCatsData;
    private CatsAdapter mAdapter;
    private FirebaseDatabase mDatabase;

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
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


        // Initialize the ArrayList that will contain the data.
        mCatsData = new ArrayList<CatData>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new CatsAdapter(getActivity(),mCatsData);
        mRecyclerView.setAdapter(mAdapter);
        mDatabase = FirebaseDatabase.getInstance();
        Log.d("databasecat", "get instance database ok");
        // Get the data.
//        initializeData();
        DatabaseReference ref = mDatabase.getReference().child("cat");
        Log.d("databasecat", "get reference ok");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   fetchData((Map<String, Object>) dataSnapshot.getValue());
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           }
        );
        return view;
    }

    /**
     * Initialize the cats data from resources.
     */

    private void fetchData(Map<String, Object> cat)  {
        ArrayList<String> catsInfo = new ArrayList<>();
        ArrayList<String> catsDate = new ArrayList<>();
        TypedArray catsImageResources =
                getResources().obtainTypedArray(R.array.cats_images);
        String[] catName = getResources().getStringArray(R.array.cats_titles);
        for (Map.Entry<String, Object> entry : cat.entrySet()) {
            Map singleCat = (Map) entry.getValue();
            Number focustime =  (Number) singleCat.get("focustime");
            String date = (String) singleCat.get("date");
            Number pic = (Number) singleCat.get("pic");
            LocationPosition longLat = new LocationPosition((Number) ((Map) singleCat.get("location")).get("longitude"), (Number) ((Map) singleCat.get("location")).get("latitude") );
            CatData objectCat = new CatData(catName[pic.intValue()], (Number) focustime, date, (Number) longLat.getLongitude(), (Number) longLat.getLatitude(), (Number) catsImageResources.getResourceId(pic.intValue(),0));
            mCatsData.add((objectCat));
        }

        catsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
//    private void initializeData() {
//        // Get the resources from the XML file.
//        String[] catsList = getResources()
//                .getStringArray(R.array.cats_titles);
//        String[] catsInfo = getResources()
//                .getStringArray(R.array.cats_info);
//        String[] catsDate = getResources()
//                .getStringArray(R.array.cats_dates);
//        TypedArray catsImageResources =
//                getResources().obtainTypedArray(R.array.cats_images);
//
//        // Clear the existing data (to avoid duplication).
//        mCatsData.clear();
//
//         Create the ArrayList of Cats objects with titles and
//         information about each C.
//        for(int i=0;i<catsList.length;i++){
//            mCatsData.add(new Cat(catsList[i], "Spent "+catsInfo[i]+" of focused time"
//                    ,catsDate[i], -6, 120, catsImageResources.getResourceId(i,0)));
//        }
//
//        catsImageResources.recycle();
//
//        // Notify the adapter of the change.
//        mAdapter.notifyDataSetChanged();
//    }


}
