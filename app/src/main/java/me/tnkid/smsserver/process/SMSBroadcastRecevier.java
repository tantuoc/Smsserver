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

import me.tnkid.smsserver.dao.FilterDAO;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;


public class SMSBroadcastRecevier extends BroadcastReceiver {
    Score score;
    ScoreDAO scoreDAO;
    FilterDAO filterDAO;


    @Override
    public void onReceive(Context context, Intent intent) {
        Config config = new Config(context);
        if (config.isRunning()) {
            scoreDAO = new ScoreDAO(context);
            scoreDAO.open();
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get("pdus");
                SmsMessage smsMessage;
                String format = intentExtras.getString("format");
                String p;
                String m;
                for (Object so : sms) {
                    smsMessage = SmsMessage.createFromPdu((byte[]) so, format);
                    p = smsMessage.getOriginatingAddress().toString();
                    m = smsMessage.getMessageBody().toString();
                    if (config.isAllow())
                        sendMSG(p, m,context);
                    else {
                        filterDAO = new FilterDAO(context);
                        filterDAO.open();
                        if (filterDAO.findNum(p))
                            sendMSG(p, m,context);
                    }


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

    private boolean phanTichSms(String msg,Context context) {
        List<String> ls = new ArrayList<>();
        Config config = new Config(context);
        String syn = config.getSyntax();
        String[] ss = msg.trim().split("\\s");


        for (String w : ss) {
            if (w.toString().trim() != null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase(syn) && w.toString().trim() != null) {
                return true;
            }

        }
        return false;
    }

    private String getMhsFromMsg(String msg,Context context) {
        List<String> ls = new ArrayList<String>();
        String mhs = null;
        String[] ss = msg.trim().split("\\s");
        Config config = new Config(context);
        String syn = config.getSyntax();

        for (String w : ss) {
            if (w.trim() != null)
                ls.add(w);
            if (ls.get(0).equalsIgnoreCase(syn) && w.trim() != null) {
                mhs = w;
            }
        }
        return mhs;
    }



    private void sendMSG(String p, String m,Context context) {
        Config config = new Config(context);
        String syn = config.getSyntax();
        if (p != null && m != null)
            if (!phanTichSms(m,context))
                sendSms(p, "Sai cú pháp! bạn vui lòng gửi lại tin nhắn với cú pháp: "+syn.toUpperCase() +" [KHOẢNG TRẮNG] [MÃ HỌC SINH]");
            else {
                score = scoreDAO.findScoreByID(getMhsFromMsg(m,context));
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


