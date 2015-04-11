package com.baixd.app.framework.assistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baixd.app.framework.R;
import com.baixd.app.framework.assistant.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardwareActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "HardwareActivity";

    private ListView mListView;

    private List<Map<String, Object>> mList;

    public final static String SYSTEM_BUNDLE = "android.intent.extra.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);

        initView();
        refreshListItems();

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_hardware);
    }

    private void refreshListItems() {
        mList = buildListForSimpleAdapter();
        SimpleAdapter adapter = new SimpleAdapter(this, mList, R.layout.item_row_hardware,
                new String[]{"name", "desc"}, new int[]{R.id.tv_hardware_name, R.id.tv_hardware_desc});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setSelection(0);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.CPU_INFO);
        map.put("name", "CPU信息");
        map.put("desc", "获取设备的CPU信息");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.MEM_INFO);
        map.put("name", "内存信息");
        map.put("desc", "手机设备的内存信息");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.DISK_INFO);
        map.put("name", "硬盘信息");
        map.put("desc", "获取设备的硬盘信息");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.NET_CONFIG);
        map.put("name", "网络信息");
        map.put("desc", "获取设备的网络信息");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.DisplayMetrics);
        map.put("name", "显示屏信息");
        map.put("desc", "获取设备的显示屏信息");
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = mList.get(position);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("id", (Integer) map.get("id"));
        bundle.putString("name", (String) map.get("name"));
        bundle.putString("desc", (String) map.get("desc"));
        intent.putExtra(SYSTEM_BUNDLE, bundle);

        intent.setClass(HardwareActivity.this, ShowInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hardware, menu);
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
