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
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.IS_RUNNING,false);
    }

    public  void isFirstRun(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(MyConstant.IS_RUNNING)||!sharedPreferences.contains(MyConstant.SYN_TAX)||!sharedPreferences.contains(MyConstant.ALLOW_ALL)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(MyConstant.IS_RUNNING, false);
            editor.putBoolean(MyConstant.ALLOW_ALL, false);
            editor.putString(MyConstant.SYN_TAX,"diem");
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
    public boolean isAllow(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.ALLOW_ALL,false);
    }


    public String getSyntax(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
       String s = sharedPreferences.getString(MyConstant.SYN_TAX,null);
        return s;
    }
    public void updateSyntax(String s){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_SHARED_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MyConstant.SYN_TAX,s);
        editor.apply();
    }

}
