package com.example.skuo.yuezhan.Base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Administrator on 16-12-2.
 */
public class SMS_Receiver extends BroadcastReceiver {
    private static final String TAG = "REC_TEST";

    private Context mContext;
    private Handler mHandler;//更新ui

    public SMS_Receiver(Context context, Handler handler) {
        mContext = context;
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = null;
        Object[] pdus = null;

        if (bundle != null) {
            pdus = (Object[]) bundle.get("pdus");
        }
        if (pdus != null) {
            smsMessages = new SmsMessage[pdus.length];
            String sender = null;
            String content = null;

            for (int i = 0; i < pdus.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                sender = smsMessages[i].getOriginatingAddress();
                content = smsMessages[i].getMessageBody();
                Log.v(TAG, "SMS:" + sender + content);
            }//for smsMessages

            //content.substring(11,15) 截取4位验证码
            Message msg = mHandler.obtainMessage(1, content.substring(11,15));
            mHandler.sendMessage(msg);
        }  //if pdus
    }
}