package com.baixd.app.framework.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ThirdReceiver extends BroadcastReceiver {
    private static final String TAG = "ThirdReceiver";

    public ThirdReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("order_msg");

        Log.i(TAG, "third 接收 --> " + msg);


    }
}
