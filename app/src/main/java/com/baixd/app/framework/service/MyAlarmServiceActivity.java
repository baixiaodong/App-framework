package com.baixd.app.framework.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baixd.app.framework.R;

import java.util.Calendar;

public class MyAlarmServiceActivity extends ActionBarActivity {

    private Button mButtonCallAlarm;
    private Button mButtonExitAlarm;

    private static MyAlarmServiceActivity appRef = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarm_service);

        appRef = this;

        initView();
        registerListener();
    }

    private void initView(){
        mButtonCallAlarm = (Button) findViewById(R.id.btn_alarm_call_service);
        mButtonExitAlarm = (Button) findViewById(R.id.btn_alarm_exit);
    }

    private void registerListener(){
        mButtonCallAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setMyTitle("Waiting... Alarm = 5");
                Intent intent = new Intent(MyAlarmServiceActivity.this, AlarmReceiver.class);

                PendingIntent contentIntent = PendingIntent.getBroadcast(MyAlarmServiceActivity.this,
                        0, intent , 0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.SECOND, 5);

                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), contentIntent);

            }
        });

        mButtonExitAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MyAlarmServiceActivity.this, AlarmReceiver.class);

                PendingIntent contentIntent = PendingIntent.getBroadcast(MyAlarmServiceActivity.this,
                        0, intent , 0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(contentIntent);

                Intent intent2 = new Intent(MyAlarmServiceActivity.this, NotifyService.class);
                stopService(intent2);

                finish();
            }
        });
    }

    public static MyAlarmServiceActivity getApp(){
        return appRef;
    }

    public void setMyTitle(String data){
        setTitle(data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
