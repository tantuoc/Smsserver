package me.tnkid.smsserver.process;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tom on 12/6/2017.
 */

public class SMSIntentService extends IntentService {

    public SMSIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences sharedPreferences = getSharedPreferences(MyConstant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

}
