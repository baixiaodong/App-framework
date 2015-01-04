package com.baixd.app.framework.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.baixd.app.framework.R;

public class DialogActivity extends ActionBarActivity {

    private static final int DIALOG1 = 1;
    private static final int DIALOG2 = 2;
    private static final int DIALOG3 = 3;
    private static final int DIALOG4 = 4;

    private Button mButtonDialg1;
    private Button mButtonDialg2;
    private Button mButtonDialg3;
    private Button mButtonDialg4;


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DIALOG1:
                return buildDialog1(DialogActivity.this);
            case DIALOG2:
                return buildDialog2(DialogActivity.this);
            case DIALOG3:
                return buildDialog3(DialogActivity.this);
            case DIALOG4:
                return buildDialog4(DialogActivity.this);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if(id == DIALOG1){
            setTitle("测试");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initView();
        registListener();
    }

    private void initView(){
        mButtonDialg1 = (Button) findViewById(R.id.btn_dialog1);
        mButtonDialg2 = (Button) findViewById(R.id.btn_dialog2);
        mButtonDialg3 = (Button) findViewById(R.id.btn_dialog3);
        mButtonDialg4 = (Button) findViewById(R.id.btn_dialog4);
    }

    private void registListener(){
        mButtonDialg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG1);
            }
        });

        mButtonDialg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG2);
            }
        });

        mButtonDialg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG3);
            }
        });

        mButtonDialg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG4);
            }
        });
    }

    private Dialog buildDialog1(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setTitle("两个按钮的对话框");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的确定按钮");
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的取消按钮");
            }
        });

        return builder.create();
    }

    private Dialog buildDialog2(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setMessage("弹出框两个按钮的消息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的确定按钮");
            }
        });
        builder.setNeutralButton("详情", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的详情按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的取消按钮");
            }
        });

        return builder.create();
    }

    private Dialog buildDialog3(Context context){

        Dialog dialoga = null;

        LayoutInflater inflater = LayoutInflater.from(this);
        final View textEntryView = inflater.inflate(R.layout.alert_dialog_text_entry, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setTitle("对话框文本输入");
        builder.setView(textEntryView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的确定按钮");
//                Animation anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 100.0f, 0.0f);
//                anim.setFillAfter(true);
//                textEntryView.setAnimation(anim);
//                anim.setDuration(700);
//                anim.startNow();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了对话框上的取消按钮");
            }
        });

        dialoga = builder.create();
        return dialoga;
    }

    private Dialog buildDialog4(Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("正在下载歌曲");
        dialog.setMessage("请稍侯...");
        return dialog;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
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
