package com.baixd.app.framework;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class RemoteDataActivity extends ActionBarActivity {

    private static final String TAG = "RemoteDataActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_data);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wv_remote_data);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//设置是当前html界面自适应屏幕
        settings.setSupportZoom(true); //设置支持缩放
        settings.setBuiltInZoomControls(true);//显示缩放控件
        settings.setLoadWithOverviewMode(true);
        mWebView.requestFocus();
        mWebView.loadUrl("file:///android_asset/www/remote_data_chart.html"); //加载assert目录下的文件
    }

    private void registListener() {

    }

    public void refreshChart(View view) {
        mWebView.loadUrl("javascript:setContentInfo('" + getRemoteData() + "')");
    }

    private String getRemoteData() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", "北京");
            obj.put("color", "#1f7e92");

            Random random = new Random();
            JSONArray jaData = new JSONArray();
            for (int i = 0; i < 12; i++) {
                jaData.put(random.nextInt(40));
            }

            obj.put("value", jaData);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(obj);
            Log.i(TAG, jsonArray.toString());
            return jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }

        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remot_data, menu);
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
