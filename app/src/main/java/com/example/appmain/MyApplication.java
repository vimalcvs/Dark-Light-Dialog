package com.example.appmain;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static final String CHECKEDITEM = "checked_item";


    public void onCreate() {
        super.onCreate();

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
    }

    public static int getCheckedItem(){
        return  sharedPreferences.getInt(CHECKEDITEM,0);
    }

    public static void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();
    }
}
