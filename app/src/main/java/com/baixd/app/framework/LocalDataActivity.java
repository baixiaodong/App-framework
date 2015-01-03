package com.baixd.app.framework;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class LocalDataActivity extends ActionBarActivity {

    private static final String TAG = "Pie3DActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);
        initView();
    }

    private void initView(){
        mWebView = (WebView) findViewById(R.id.wv_local_data);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);  //设置WebView支持javascript
        settings.setUseWideViewPort(true);//设置是当前html界面自适应屏幕
        settings.setSupportZoom(true); //设置支持缩放
        settings.setBuiltInZoomControls(true);//显示缩放控件
        settings.setLoadWithOverviewMode(true);
        mWebView.requestFocus();
        mWebView.loadUrl("file:///android_asset/www/local_data_chart.html"); //加载assert目录下的文件
    }

    private void registListener(){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_data, menu);
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
