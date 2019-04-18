package com.example.catsmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.Toast;
import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private static Context context = null;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView wordItemView;

        final WordListAdapter mAdapter;
            public WordViewHolder(View itemView, WordListAdapter adapter) {
                super(itemView);
                wordItemView = (TextView) itemView.findViewById(R.id.word);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
            }
        @Override
        public void onClick(View v) {
            context = v.getContext();
            final int mPosition = getLayoutPosition();
            String element = mWordList.get(mPosition);

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Is it done yet?");
            // alert.setMessage("Message");

            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    mWordList.remove(mPosition);
                    Preferences.setArrayPrefs("WordList",mWordList,context);
                    Toast toast=Toast.makeText(context,"This task has been removed",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            alert.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast toast=Toast.makeText(context,"Get it done fast!",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

            alert.show();
            mAdapter.notifyDataSetChanged();
        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}