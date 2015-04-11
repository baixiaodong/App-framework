package com.baixd.app.framework.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baixd.app.framework.R;
import com.baixd.app.framework.widget.WidgetActivity;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    private static final int NOTIFICATION_ID = R.layout.activity_notification_receive;

    private NotificationManager mNotificationManager;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i(TAG, msg);

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(R.drawable.sun, "接收消息" , System.currentTimeMillis());

        Intent intent2 = new Intent(context, WidgetActivity.class);

        //通过此处的设置来处理notification，以及点击notification后的处理动作,注意{@link #setLatestEventInfo}
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent2, 0);

        notification.setLatestEventInfo(context, "接受消息", msg, contentIntent);


        mNotificationManager.notify(NOTIFICATION_ID, notification);

    }
}
