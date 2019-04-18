package com.example.catsmanager;


import android.content.Context;
import android.content.SharedPreferences;
import java.util.LinkedList;

public class Preferences {

  public static void setArrayPrefs(String arrayName, LinkedList<String> array, Context mContext) {
    SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putInt(arrayName +"_size", array.size());
    for(int i=0;i<array.size();i++)
      editor.putString(arrayName + "_" + i, array.get(i));
    editor.apply();
  }

  public static LinkedList<String> getArrayPrefs(String arrayName, Context mContext) {
    SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
    int size = prefs.getInt(arrayName + "_size", 0);
    LinkedList<String> array = new LinkedList<>();
    for(int i=0;i<size;i++)
      array.addLast(prefs.getString(arrayName + "_" + i, null));
    return array;
  }
}