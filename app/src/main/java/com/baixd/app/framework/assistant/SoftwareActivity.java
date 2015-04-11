package com.baixd.app.framework.assistant;

import android.app.ApplicationErrorReport;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baixd.app.framework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftwareActivity extends ActionBarActivity implements  Runnable{

    private List<Map<String, Object>> mList = null;
    private ListView mListView = null;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);

        mListView = (ListView) findViewById(R.id.lv_software);
        mProgressDialog = ProgressDialog.show(this, "请稍后...", "正在收集你安装的软件信息...", true,false);

        Thread thread = new Thread(this);
        thread.start();
    }

    private void refreshListItems(){
        //fetchInstalledApps();
        SimpleAdapter adapter = new SimpleAdapter(this, mList, R.layout.item_row_system,
                new String[]{"name","desc"}, new int[]{R.id.tv_system_name, R.id.tv_system_desc});
        mListView.setAdapter(adapter);

    }

    /**
     * 获取已经安装的软件列表
     * @return
     */
    public List fetchInstalledApps(){
        List<ApplicationInfo> packages = this.getPackageManager().getInstalledApplications(0);
        mList = new ArrayList<Map<String, Object>>(packages.size());

        for(ApplicationInfo app:packages){

            Map<String,Object> map = new HashMap<String, Object>();
            String packageName = app.packageName;
            String label = getPackageManager().getApplicationLabel(app).toString();
            map.put("name", label);
            map.put("desc", packageName);

            mList.add(map);
        }

        return mList;
    }

    @Override
    public void run(){
        fetchInstalledApps();
        mHandler.sendEmptyMessage(0);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            refreshListItems();
            mProgressDialog.dismiss();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_software, menu);
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
