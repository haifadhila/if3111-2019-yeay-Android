package com.example.catsmanager;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
/***
 * The adapter class for the RecyclerView, contains the Cats data.
 */
class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<CatData> mCatsData = new ArrayList<>();
    private Context mContext;
    private ImageView mCatsImage;
    private DatabaseReference mDatabase;
    private static final String TAG = "CatDatabase:";

    /**
     * Constructor that passes in the Cats data and the context.
     */
    CatsAdapter(Context context, ArrayList<CatData> catsData) {
        this.mCatsData = catsData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public CatsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(CatsAdapter.ViewHolder holder,
                                 int position) {
        // Get current Cat.
        CatData currentCat = mCatsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentCat);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mCatsData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mDateText;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mDateText = itemView.findViewById(R.id.newsTitle);
            mCatsImage = itemView.findViewById(R.id.catsImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(CatData currentCat){
            // Populate the textviews with data.
//            mTitleText.setText(currentCat.getTitle());
            Log.d("STRINGFOCUSTIME", String.valueOf(currentCat.getFocustime().intValue()));
            mInfoText.setText(String.valueOf(currentCat.getFocustime().intValue()));
            mDateText.setText(currentCat.getDate());
            Glide.with(mContext).load(currentCat.getPic()).into(mCatsImage);
        }

        @Override
        public void onClick(View v) {
            CatData currentCat = mCatsData.get(getAdapterPosition());
            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+currentCat.getLatitude()
                    +","+currentCat.getLongitude()+"(You found the "+currentCat.getPic()+" Cat here!)");
            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            mContext.startActivity(mapIntent);
        }
    }

//    private void jsonParse(){
//
//    }
}
