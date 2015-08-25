package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mischa on 2015/8/5.
 */
public class MyPreference {
    private  static MyPreference ins;
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences mSettinsSP;

    private MyPreference(Context context) {
        this.context = context;
        mSettinsSP = context.getSharedPreferences("data", Activity.MODE_PRIVATE);
        editor = mSettinsSP.edit();
    }

    public static MyPreference getInstance(Context context) {
        if (ins == null) {
            ins = new MyPreference(context);
        }
        return ins;
    }

    public void setData(String day, String content) {
        editor.putString(day, content);
        editor.commit();
    }

    public String getData(String day) {
        return mSettinsSP.getString(day, "");
    }

}
