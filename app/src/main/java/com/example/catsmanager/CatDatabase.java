package com.example.catsmanager;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CatDatabase extends AppCompatActivity {
    private static final String TAG = "CatDatabase:";
    private ArrayList<Cat> mCats = new ArrayList<>();
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catfound);
    }
    private void jsonParse(){
        String url = "https://if3111-2019-yeay-android.herokuapp.com/api/cat";
        Log.d(TAG, "jsonParse: called");

        final TypedArray catsImageResources =
                getResources().obtainTypedArray(R.array.cats_images);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cats");
                            Log.d(TAG, "Length: " + jsonArray.length());
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject cat = jsonArray.getJSONObject(i);
                                String title= cat.getString("title");
                                String info = cat.getString("info");
                                String dateAcquired = cat.getString("dateAcquired");
                                Double latitude = cat.getDouble("latitude");
                                Double longitude = cat.getDouble("longitude");
                                int imageResource = cat.getInt("imageResource");
                                Cat newCat = new Cat(title, info, dateAcquired, latitude, longitude, catsImageResources.getResourceId(imageResource,0));
                                mCats.add(newCat);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
