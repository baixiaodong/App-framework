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
                "http://lx.cdn.baidupcs.com/file/5885c1621b14a7852d2f7f383a47db33?bkt=p-f2d98a525e3a77db2cb4c6f3c89361ff&xcode=62414892cd6afedc2cb8f966f51f1b9c7d971ffc8eeb0cedae97ca166f54709c&fid=2533653075-250528-3347951802&time=1429434852&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-3vvCxui21I192e46NkGvk%2B9CESM%3D&to=cb&fm=Qin,B,U,ny&sta_dx=10&sta_cs=62&sta_ft=mp3&sta_ct=7&newver=1&newfm=1&flow_ver=3&sl=75628623&expires=8h&rt=pr&r=663044551&mlogid=1260042379&vuk=2533653075&vbdid=4213482117&fin=02%20%E6%9B%B4%E5%A5%BD%E7%9A%84%E6%88%91.mp3&fn=02%20%E6%9B%B4%E5%A5%BD%E7%9A%84%E6%88%91.mp3&slt=pm&uta=0",
                "更好的我.mp3", 0, 0);

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
