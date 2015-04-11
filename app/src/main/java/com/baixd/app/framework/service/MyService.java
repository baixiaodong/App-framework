package com.baixd.app.framework.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.baixd.app.framework.R;

public class MyService extends Service {

    private static final String TAG = "MyService";

    private static final int NOTIFICATION_ID = R.layout.activity_my_service;

    private NotificationManager mNotificationManager;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        showNotification(R.drawable.sun, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        showNotification(R.drawable.sun, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        showNotification(R.drawable.cloudy, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showNotification(R.drawable.rain, "onDestroy");
        Log.i(TAG, "onDestroy");
    }

    public class LocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

    private void showNotification(int drawable, String context){

        Notification notification = new Notification(drawable, context , System.currentTimeMillis());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MyServiceActivity.class), 0);
        notification.setLatestEventInfo(this, "服务消息", context, contentIntent);

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }
}
