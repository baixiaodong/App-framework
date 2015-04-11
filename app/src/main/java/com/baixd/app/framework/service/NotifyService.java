package com.baixd.app.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NotifyService extends Service {
    public NotifyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        MyAlarmServiceActivity app = MyAlarmServiceActivity.getApp();
        app.setMyTitle("from NotifyService");
        Toast.makeText(this, "hello my iceskysl ", Toast.LENGTH_SHORT).show();
    }
}
