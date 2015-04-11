package com.baixd.app.framework.service;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baixd.app.framework.R;

public class MyServiceActivity extends ActionBarActivity {

    private static final String TAG = "MyServiceActivity";

    private boolean isBound;

    private Button mButtonStartService;
    private Button mButtonStopService;
    private Button mButtonBindService;
    private Button mButtonUnBindService;

    private Button mButtonExampleService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        initView();
        registerListener();
    }


    private void initView(){
        mButtonStartService = (Button) findViewById(R.id.btn_service_start);
        mButtonStopService = (Button) findViewById(R.id.btn_service_stop);
        mButtonBindService = (Button) findViewById(R.id.btn_service_bind);
        mButtonUnBindService = (Button) findViewById(R.id.btn_service_unbind);
        mButtonExampleService = (Button) findViewById(R.id.btn_service_example);
    }


    private void registerListener(){
        mButtonStartService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                startService(intent);
            }
        });

        mButtonStopService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });

        mButtonBindService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                isBound = true;
            }
        });

        mButtonUnBindService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isBound){
                    unbindService(mServiceConnection);
                    isBound = false;
                }

            }
        });

        mButtonExampleService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MyServiceActivity.this, MyAlarmServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
