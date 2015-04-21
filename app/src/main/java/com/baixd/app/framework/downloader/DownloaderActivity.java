package com.baixd.app.framework.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baixd.app.framework.MainActivity;
import com.baixd.app.framework.R;
import com.baixd.app.framework.base.IBaseActivity;

public class DownloaderActivity extends ActionBarActivity implements IBaseActivity{


    private TextView mTextViewFileName;
    private TextView mTextViewFinished;

    private Button mBtnStart;
    private Button mBtnStop;

    private ProgressBar mProgressBar;

    private FileInfo mFileInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);

        //创建文件对象
        mFileInfo = new FileInfo(0,
                "http://www.imooc.com/mobile/imooc.apk",
                "imooc.apk", 0, 0);

        init();
        initView();
        registerListener();

        //注册广播监听器
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(mReceiver, filter);

    }


    @Override
    public void init() {

    }

    @Override
    public void initView() {
        mBtnStart = (Button) findViewById(R.id.btn_download_start);
        mBtnStop = (Button) findViewById(R.id.btn_download_stop);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextViewFileName = (TextView) findViewById(R.id.tv_downloader_file_name);
        mTextViewFinished = (TextView) findViewById(R.id.tv_finished_pb);
        mProgressBar.setMax(100);
    }

    @Override
    public void registerListener() {

        mBtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloaderActivity.this, DownloadService.class);
                intent.setAction(DownloadService.ACTION_START);
                mTextViewFileName.setText(mFileInfo.getFileName());
                intent.putExtra("fileInfo", mFileInfo);
                startService(intent);
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloaderActivity.this, DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra("fileInfo", mFileInfo);
                startService(intent);
            }
        });

    }

    BroadcastReceiver mReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            if(DownloadService.ACTION_UPDATE.equals(intent.getAction())){
                int finished = intent.getIntExtra("finished", 0);
                mProgressBar.setProgress(finished);
                mTextViewFinished.setText("    (" + finished + "%" + ")");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_downloader, menu);
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
