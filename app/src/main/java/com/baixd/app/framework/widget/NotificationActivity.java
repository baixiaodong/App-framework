package com.baixd.app.framework.widget;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baixd.app.framework.R;

public class NotificationActivity extends ActionBarActivity {

    private static int NOTIFICATION_ID = R.layout.activity_notification;

    private NotificationManager mNotificationManager;

    private Button mButtonSun;
    private Button mButtonCloudy;
    private Button mButtonRain;
    private Button mButtonSound;
    private Button mButtonVibrate;
    private Button mButtonSoundAndVibrate;
    private Button mButtonClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        initView();
        registerListener();
    }

    private void initView(){
        mButtonSun = (Button) findViewById(R.id.btn_notification_sun);
        mButtonCloudy = (Button) findViewById(R.id.btn_notification_cloudy);
        mButtonRain = (Button) findViewById(R.id.btn_notification_rain);
        mButtonSound = (Button) findViewById(R.id.btn_notification_sound);
        mButtonVibrate = (Button) findViewById(R.id.btn_notification_vibrate);
        mButtonSoundAndVibrate = (Button) findViewById(R.id.btn_notification_sound_vibrate);
        mButtonClear = (Button) findViewById(R.id.btn_notification_clear);
    }

    private void registerListener(){
        mButtonSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWeather("晴空万里","天气预报","晴空万里", R.drawable.sun);
            }
        });
        mButtonCloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWeather("阴云密布","天气预报","阴云密布", R.drawable.cloudy);
            }
        });
        mButtonRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWeather("大雨连绵","天气预报","大雨连绵", R.drawable.rain);
            }
        });
        mButtonSound.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setDefault(Notification.DEFAULT_SOUND);
            }
        });
        mButtonVibrate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setDefault(Notification.DEFAULT_VIBRATE);
            }
        });
        mButtonSoundAndVibrate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setDefault(Notification.DEFAULT_ALL);
            }
        });
        mButtonClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(NOTIFICATION_ID);
            }
        });
    }

    /**
     * 设置天气
     * @param tickerText
     * @param title
     * @param content
     * @param drawable
     */
    private void setWeather(String tickerText, String title, String content, int drawable){
        Notification notification = new Notification(drawable, tickerText,
                System.currentTimeMillis());

        //通过此处的设置来处理notification，以及点击notification后的处理动作,注意{@link #setLatestEventInfo}
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, WidgetActivity.class), 0);

        notification.setLatestEventInfo(this, title, content, contentIntent);

        mNotificationManager.notify(NOTIFICATION_ID, notification);

    }


    private void setDefault(int defaults){
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, WidgetActivity.class), 0);

        String title = "天气预报";
        String content = "晴空万里";

        final Notification notification = new Notification(R.drawable.sun, content, System.currentTimeMillis());

        notification.setLatestEventInfo(this, title, content, contentIntent);

        notification.defaults = defaults;

        mNotificationManager.notify(NOTIFICATION_ID, notification);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification, menu);
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
