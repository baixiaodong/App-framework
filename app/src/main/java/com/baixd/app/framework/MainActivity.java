package com.baixd.app.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baixd.app.framework.anim.AnimationActivity;
import com.baixd.app.framework.assistant.MobileAssistantActivity;
import com.baixd.app.framework.broadcast.MyBroadcastReceiverActivity;
import com.baixd.app.framework.coverflow.CoverFlowActivity;
import com.baixd.app.framework.custom.UIActivity;
import com.baixd.app.framework.dialog.DialogActivity;
import com.baixd.app.framework.fragment.FragmentActivity;
import com.baixd.app.framework.handler.HandlerActivity;
import com.baixd.app.framework.my.LayoutActivity;
import com.baixd.app.framework.scroll.ScrollActivity;
import com.baixd.app.framework.service.MyServiceActivity;
import com.baixd.app.framework.widget.GridViewActivity;
import com.baixd.app.framework.widget.WidgetActivity;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private Button mButtonPie3D;
    private Button mButtonLocalData;
    private Button mButtonRemoteData;
    private Button mButtonListView;
    private Button mButtonSlideListView;
    private Button mButtonSwipeGesture;
    private Button mButtonAnimation;
    private Button mButtonDialog;
    private Button mButtonScrollView;
    private Button mButtonCoverFlow;
    private Button mButtonActivityLayout;
    private Button mButtonWidget;
    private Button mButtonFragment;
    private Button mButtonGrid;
    private Button mButtonBroadcast;
    private Button mButtonService;
    private Button mButtonAssistant;
    private Button mButtonCustomUI;
    private Button mButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerListener();

    }


    private void initView() {

//        mButtonPie3D = (Button) findViewById(R.id.btn_h5_pie3d);
        mButtonLocalData = (Button) findViewById(R.id.btn_h5_local);
        mButtonRemoteData = (Button) findViewById(R.id.btn_h5_remote);
        mButtonListView = (Button) findViewById(R.id.btn_listview);
        mButtonSlideListView = (Button) findViewById(R.id.btn_slide_listview);
        mButtonSwipeGesture = (Button) findViewById(R.id.btn_swipe_gesture);
        mButtonAnimation = (Button) findViewById(R.id.btn_animation);
        mButtonDialog = (Button) findViewById(R.id.btn_dialog);
        mButtonScrollView = (Button) findViewById(R.id.btn_scroll_view);
        mButtonCoverFlow = (Button) findViewById(R.id.btn_cover_flow);
        mButtonActivityLayout = (Button) findViewById(R.id.btn_activity_layout);
        mButtonWidget = (Button) findViewById(R.id.btn_widget);
        mButtonFragment = (Button) findViewById(R.id.btn_fragment);
        mButtonGrid = (Button) findViewById(R.id.btn_grid);
        mButtonBroadcast = (Button) findViewById(R.id.btn_broadcast);
        mButtonService = (Button) findViewById(R.id.btn_service);
        mButtonAssistant = (Button) findViewById(R.id.btn_assistant);
        mButtonCustomUI = (Button) findViewById(R.id.btn_custom_ui);
        mButtonHandler = (Button) findViewById(R.id.btn_handler);
    }


    private void registerListener() {
//        mButtonPie3D.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Pie3DActivity.class);
//                startActivity(intent);
//
//            }
//        });

        mButtonLocalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocalDataActivity.class);
                startActivity(intent);
            }
        });

        mButtonRemoteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RemoteDataActivity.class);
                startActivity(intent);
            }
        });

        mButtonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestListViewActivity.class);
                startActivity(intent);
            }
        });

        mButtonSlideListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicListViewActivity.class);
                startActivity(intent);
            }
        });

        mButtonSwipeGesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SwipeActivity.class);
                startActivity(intent);
            }
        });

        mButtonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

        mButtonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        mButtonScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollActivity.class);
                startActivity(intent);
            }
        });

        mButtonCoverFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CoverFlowActivity.class);
                startActivity(intent);
            }
        });

        mButtonActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LayoutActivity.class);
                startActivity(intent);
            }
        });

        mButtonWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WidgetActivity.class);
                startActivity(intent);
            }
        });

        mButtonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        mButtonGrid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });

        mButtonBroadcast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyBroadcastReceiverActivity.class);
                startActivity(intent);
            }
        });

        mButtonService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyServiceActivity.class);
                startActivity(intent);
            }
        });

        mButtonAssistant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MobileAssistantActivity.class);
                startActivity(intent);
            }
        });

        mButtonCustomUI.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });

        mButtonHandler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
