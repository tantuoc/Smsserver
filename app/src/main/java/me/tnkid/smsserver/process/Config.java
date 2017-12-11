package me.tnkid.smsserver.process;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tom on 12/9/2017.
 */

public class Config {

    private Context context;
    public Config(Context context){
        this.context=context;
    }

    public boolean isRunning() {
        isFirstRun();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.IS_RUNNING,false);
    }

    public  void isFirstRun(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(MyConstant.IS_RUNNING)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(MyConstant.IS_RUNNING, false);
            editor.putBoolean(MyConstant.ALLOW_ALL, false);
            editor.commit();
        }
    }
    public void setState(boolean s){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.IS_RUNNING, s);
        editor.apply();
    }

    public void setFilter(boolean s){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MyConstant.ALLOW_ALL, s);
        editor.apply();
    }

}
