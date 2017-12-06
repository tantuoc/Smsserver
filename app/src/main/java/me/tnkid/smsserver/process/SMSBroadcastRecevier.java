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
import me.tnkid.smsserver.R;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;



public class SMSBroadcastRecevier extends BroadcastReceiver {
    protected MainActivity context;
    ScoreDAO scoreDAO;
    Score score;
    private boolean state = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        scoreDAO = new ScoreDAO(context);

            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
            /* Get Messages */
                Object[] sms = (Object[]) intentExtras.get("pdus");
                SmsMessage smsMessage;
                String format = intentExtras.getString("format");
                String p;
                String m;
                for (Object so : sms) {
                    smsMessage = SmsMessage.createFromPdu((byte[]) so, format);
                    p = smsMessage.getOriginatingAddress().toString();
                    m = smsMessage.getMessageBody().toString();

                    if (isState())
                    sendMSG(p, m);

            }

        }

    }
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
            if (w.toString().trim() != null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase("diem") && w.toString().trim() != null) {
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
            if (w.trim() != null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase("diem") && w.trim() != null) {
                mhs = w;
            }
        }
        return mhs;
    }

    private void sendMSG(String p, String m) {

            if (p != null && m != null)
                if (!phanTichSms(m))
                    sendSms(p, context.getString(R.string.saicpsms).toString());
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


