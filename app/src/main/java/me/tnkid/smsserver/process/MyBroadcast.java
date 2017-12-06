package me.tnkid.smsserver.process;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.tnkid.smsserver.MainActivity;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;
import me.tnkid.smsserver.mystring.MyString;

/**
 * Created by tantuoc on 12/4/2017.
 */

public class MyBroadcast extends BroadcastReceiver {
    protected MainActivity context;
    ScoreDAO scoreDAO;
    Score score;

    @Override
    public void onReceive(Context context, Intent intent) {
        scoreDAO = new ScoreDAO(context);
        if(MyString.IS_RUNNING){

            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
            /* Get Messages */
                Object[] sms = (Object[]) intentExtras.get("pdus");
                SmsMessage smsMessage;
                String format = intentExtras.getString("format");
                for (Object so:sms) {
                    smsMessage = SmsMessage.createFromPdu((byte[]) so, format);
                   String p = smsMessage.getOriginatingAddress().toString();
                   String m = smsMessage.getMessageBody().toString();
                    sendMSG(p,m);
                }
            }
        }


    }

    private void sendSms(String strPhone, String strMessage) {
        SmsManager sms = SmsManager.getDefault();

        try {
            ArrayList<String> messageParts = sms.divideMessage(strMessage);
            sms.sendMultipartTextMessage(strPhone, null, messageParts, null, null);


        } catch (Exception e) {
            Log.d("EXCP", e.getMessage());

        }


    }

    private boolean phanTichSms(String msg) {
        List<String> ls = new ArrayList<String>();
        String mhs = null;
        String[] ss = msg.trim().split("\\s");

        for (String w : ss) {
            if (w.trim()!=null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase(MyString.CU_PHAP_SMS) && w.trim() != null) {
                return true;
            }

        }


        return false;
    }

    private String getMhsFromMsg(String msg) {
        List<String> ls = new ArrayList<String>();
        String mhs = null;
        String[] ss = msg.trim().split("\\s");

        for (String w : ss) {
            if (w.trim()!=null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase(MyString.CU_PHAP_SMS) && w.trim() != null) {
                mhs = w;
            }
        }

        return mhs;
    }

    void sendMSG(String p, String m){
        if (p != null && m != null)
            if (!phanTichSms(m))

                sendSms(p, MyString.SAI_CP);
            else {
                score = scoreDAO.findScoreByID(getMhsFromMsg(m));
                if (score != null) {
                    String rs = "Mã học sinh : " + score.getMHS() + "\n";
                    rs += "Tên : " + score.getName() + "\n";
                    rs += "Toán : " + score.getdToan() + "\n";
                    rs += "Lý : " + score.getdLy() + "\n";
                    rs += "Hoá : " + score.getdHoa();
                    sendSms(p, rs);

                } else sendSms(p, "Mã học sinh không tồn tại!");

            }
    }
}


