package com.baixd.app.framework.broadcast;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baixd.app.framework.R;

public class MyBroadcastReceiverActivity extends ActionBarActivity {

    private static final String TAG = "MyBroadcastReceiver";

    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast_receiver);


        mButtonSend = (Button) findViewById(R.id.btn_broadcast_send);

        mButtonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i(TAG, "发送消息");
                Intent intent = new Intent("android.intent.action.MY_BROADCAST");
                intent.putExtra("msg", "hello receiver");
                sendBroadcast(intent);

                intent = new Intent("android.intent.action.MY_BROADCAST2");
                intent.putExtra("msg2", "hello receiver notification");
                sendBroadcast(intent);

                intent = new Intent("android.intent.action.MY_ORDER_BROADCAST");
                intent.putExtra("order_msg", "order receiver");
                sendBroadcast(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_broadcast_receiver, menu);
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
