package com.baixd.app.framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class Pie3DActivity extends Activity {

    private static final String TAG = "Pie3DActivity";

    private WebView mWebViewPie3D;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_3d);

        initView();

    }

    private void initView() {

        mWebViewPie3D = (WebView) findViewById(R.id.webViewPie3D);

        mWebViewPie3D.setHorizontalScrollBarEnabled(true);
        mWebViewPie3D.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        WebSettings settings = mWebViewPie3D.getSettings();

        //设置字符集
        settings.setDefaultTextEncodingName("UTF-8");
//        settings.setPluginsEnabled(true);
        //开启javascript支持
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);//显示缩放控件
        settings.setUseWideViewPort(true);//设置html页面自适应屏幕
        settings.setLoadWithOverviewMode(true);
        mWebViewPie3D.requestFocus();
        mWebViewPie3D.addJavascriptInterface(new JSInterface(this, handler, mWebViewPie3D), "myObject");

        //加载assets目录下的问题件
        String url = "file:///android_asset/www/index.html";
        mWebViewPie3D.loadUrl(url);
    }


    private void registerListener() {
    }

}

class JSInterface {

    private Context mContext = null;
    private Handler mHandler = null;
    private WebView mWebView;

    private JSONArray jsonArray = new JSONArray();
    private Random random = new Random();

    public JSInterface(Context context, Handler handler, WebView webView) {
        mContext = context;
        mHandler = handler;
        mWebView = webView;
    }

    public void init() {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                mWebView.loadUrl("javascript:setContactInfo('" + getJsonStr() + "')");
            }
        });
    }

    public int getW() {
        return px2dip(mContext.getResources().getDisplayMetrics().widthPixels);
    }

    public int getH() {
        return px2dip(mContext.getResources().getDisplayMetrics().heightPixels);
    }

    public int px2dip(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setValue(String name, String value) {
        Toast.makeText(mContext, name + " " + value + "%", Toast.LENGTH_SHORT).show();
    }

    public String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }

    public String getJsonStr() {
        try {
            for (int i = 0; i < 5; i++) {
                JSONObject obj = new JSONObject();
                obj.put("name", "name" + i);
                obj.put("value", random.nextInt(30));
                obj.put("color", getRandColorCode());
                jsonArray.put(obj);

            }

            Log.i("", jsonArray.toString());
            return jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
