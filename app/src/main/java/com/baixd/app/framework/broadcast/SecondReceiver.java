package com.baixd.app.framework.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SecondReceiver extends BroadcastReceiver {
    private static final String TAG = "SecondReceiver";

    public SecondReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("order_msg");

        Log.i(TAG, "second 接收 --> " + msg);


    }
}
