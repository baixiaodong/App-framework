package com.baixd.app.framework.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FirstReceiver extends BroadcastReceiver {

    private static final String TAG = "FirstReceiver";

    public FirstReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("order_msg");

        Log.i(TAG, "first 接收 --> " +  msg);


    }
}
