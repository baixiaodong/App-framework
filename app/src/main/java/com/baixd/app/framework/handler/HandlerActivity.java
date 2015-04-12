package com.baixd.app.framework.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baixd.app.framework.R;
import com.baixd.app.framework.base.IBaseActivity;

public class HandlerActivity extends ActionBarActivity implements IBaseActivity{

    private final static String TAG = "HandlerActivity";

    private TextView mTextView;
    private TextView mTextViewReceiver;
    private Button mButtonFetch;
    private Button mButtonSend;

    private Handler mHandler;
    private Handler mWorkerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mHandler = new MyHandler();

        init();
        initView();
        registerListener();

        new WorkerThread().start();

    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        mTextView = (TextView) findViewById(R.id.tv_handler_content);
        mTextViewReceiver = (TextView) findViewById(R.id.tv_handler_receive_main);
        mButtonFetch= (Button) findViewById(R.id.btn_handler_fetch);
        mButtonSend= (Button) findViewById(R.id.btn_handler_send);
    }

    @Override
    public void registerListener() {
        mButtonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new NetworkThread();
                thread.start();
            }
        });

        //从主线程向子线程发送数据
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "从主线程向子线程发送数据");

                Message msg =  mWorkerHandler.obtainMessage();
                msg.obj = "从主线程向子线程发送数据";
                mWorkerHandler.sendMessage(msg);
            }
        });
    }

    //MyHandler运行在主线程上
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handler message---" + Thread.currentThread().getName() + " ==" + msg.what);
            String s = (String) msg.obj;
            mTextView.setText(s);
        }
    }

    class NetworkThread extends Thread{
        @Override
        public void run(){
            Log.i(TAG, "network---" + Thread.currentThread().getName());
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String s = "网络来源的数据";

            Message msg = mHandler.obtainMessage();
//            Message msg = new Message();
            msg.what = 1;
            msg.obj = s;
            mHandler.sendMessage(msg);
        }
    }

    /**
     * WorkerThread接收来自MainThread发送的数据
     */
    class WorkerThread extends Thread{

        @Override
        public void run() {
            //1.准备Looper对象
            Looper.prepare();
            //2.在WorkThread中生成一个Handler对象
            mWorkerHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG, "WorkThread接收消息 " + msg.obj);
//                    mTextViewReceiver.setText((CharSequence) msg.obj);//WorkerThread中不能更新UI
                }
            };
            //3.调用loop方法，Looper对象将不断从消息队列当中取出消息对象；然后调用handler的handleMessage方法，处理该消息对象
            //如果消息队列中没有消息对象，该线程阻塞
            Looper.loop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_handler, menu);
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
