package com.baixd.app.framework.lifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.baixd.app.framework.R;

/**
 * Created by kk on 2015/5/24 024.
 */
public class ActivityLifeCycleActivity extends Activity{

    private static final String TAG = "ActivityLifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycle);

        Log.i(TAG, "onCreate...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy...");
    }

    public void newActivity(View view){
        Intent intent = new Intent(ActivityLifeCycleActivity.this, NewLifecycleActivity.class);
        startActivity(intent);
    }

    public void newAletDialog(View view){
        showDialog(0);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setTitle("两个按钮的对话框");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的确定按钮");
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的取消按钮");
            }
        });

        return builder.create();
    }

    PopupWindow window;

    public void newPopupWindow(View view){
        Log.i(TAG, "newPopupWindow...");
        initPopupWindow();

//        window.showAsDropDown(view); //相对某个控件的位置（正左下方），无偏移
//        window.showAsDropDown(view, 50, 50);//相对某个控件的位置（正左下方），有偏移
        window.showAtLocation(view, Gravity.CENTER, 0, 0);//相对于父控件的位置，无偏移
//        window.showAtLocation(view, Gravity.BOTTOM, 0, 50);// 相对于父控件的位置，有偏移
    }


    public void initPopupWindow(){
        LayoutInflater inflater = LayoutInflater.from(this);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupWindow = inflater.inflate(R.layout.activity_popupwindow_lifecycle, null);

        window = new PopupWindow(popupWindow, 200, 300);
    }
}
