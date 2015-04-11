package com.baixd.app.framework.assistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.baixd.app.framework.R;
import com.baixd.app.framework.assistant.utils.FetchData;
import com.baixd.app.framework.assistant.utils.PreferencesUtils;


public class ShowInfoActivity extends ActionBarActivity implements Runnable{

    private static final String TAG = "ShowInfoActivity";

    private TextView mTextViewShowName;
    private TextView mTextViewShowDesc;

    private ProgressDialog mProgressDialog;

    private String mInfoData;

    private int mId;
    private String mName;
    private String mDesc;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);


        mTextViewShowName = (TextView) findViewById(R.id.tv_show_name);
        mTextViewShowDesc = (TextView) findViewById(R.id.tv_show_desc);

        Log.i(TAG, "进入showInfoActivity");
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getBundleExtra(SystemActivity.SYSTEM_BUNDLE);
            if(bundle != null){

                mId = bundle.getInt("id");
                mName = bundle.getString("name");
                mDesc = bundle.getString("desc");
                mPosition = bundle.getInt("position");
                Log.i(TAG, "id:" + mId + ",name:" + mName + ",desc:" + mDesc + ",position:" + mPosition);

                mTextViewShowName.setText(mName);
            }

        }

        loadData();
    }

    private void loadData(){
        mProgressDialog = ProgressDialog.show(this, "Please waiting a moment...", "fetch info datas...", true, false);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        switch (mId){

            case PreferencesUtils.VER_INFO:
                mInfoData = FetchData.fetchVersionInfo();
                break;
            case PreferencesUtils.SystemProperty:
                mInfoData = FetchData.getSystemProperty();
                break;
            case PreferencesUtils.TEL_STATUS:
                mInfoData = FetchData.fetchTelStatus(this);
                break;
            case PreferencesUtils.CPU_INFO:
                mInfoData = FetchData.fetchCpuInfo();
                break;
            case PreferencesUtils.MEM_INFO:
                mInfoData = FetchData.getMemoryInfo(this);
                break;
            case PreferencesUtils.DISK_INFO:
                mInfoData = FetchData.fetchDiskInfo();
                break;
            case PreferencesUtils.NET_CONFIG:
                mInfoData = FetchData.fetchNetCfgInfo();
                break;
            case PreferencesUtils.DisplayMetrics:
                mInfoData = FetchData.getDisplayMetrics(this);
                break;
            case PreferencesUtils.RunningService:
                mInfoData = FetchData.getRunningServiceInfo(this);
                break;
            case PreferencesUtils.RunningTasks:
                mInfoData = FetchData.getRunningTaskInfo(this);
                break;
            case PreferencesUtils.RunningProcesses:
                mInfoData = FetchData.fetchProcessInfo();
                break;
            default:
                mInfoData = "信息获取失败";
                break;
        }

        mHandler.sendEmptyMessage(0);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();
            mTextViewShowDesc.setText(mInfoData);
        }
    };

    private void showInfo(){

        switch (mId) {
            case PreferencesUtils.VER_INFO:
                String versionInfo = FetchData.fetchVersionInfo();
                Log.i(TAG, "version: " + versionInfo);
                mTextViewShowName.setText(mName);
                mTextViewShowDesc.setText(versionInfo);

                break;

            case PreferencesUtils.SystemProperty:
                String systemProperty = FetchData.getSystemProperty();
                mTextViewShowName.setText(mName);
                mTextViewShowDesc.setText(systemProperty);
                break;

            case PreferencesUtils.TEL_STATUS:
                String telStatus = FetchData.fetchTelStatus(this);
                mTextViewShowName.setText(mName);
                mTextViewShowDesc.setText(telStatus);
                break;
            default:
                mTextViewShowName.setText(mName);
                mTextViewShowDesc.setText("信息获取失败");
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_info, menu);
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
