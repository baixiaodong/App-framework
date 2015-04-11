package com.baixd.app.framework.assistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

/**
 * 查看系统
 *
 * @author Baixd
 * @since 2015-3-31
 * @version 1.0
 */
public class SystemActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "SystemActivity";

    private ListView mListView;

    private List<Map<String, Object>> list;

    public final static String SYSTEM_BUNDLE = "android.intent.extra.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        initView();
        refreshListItems();

        Log.i(TAG, "初始化了SystemActivity");
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.lv_system);
    }

    public void registenerListener(){

    }

    private void refreshListItems(){
        list = buildListForSimpleAdapter();
        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.item_row_system, new String[]{"name", "desc"},
                new int[]{R.id.tv_system_name, R.id.tv_system_desc});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setSelection(mListView.getBottom());

    }

    private List<Map<String, Object>> buildListForSimpleAdapter(){

        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", PreferencesUtils.VER_INFO);
        map.put("name","操作系统版本");
        map.put("desc","读取/proc/version信息");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("id", PreferencesUtils.SystemProperty);
        map.put("name","系统信息");
        map.put("desc","手机设备的系统信息");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("id", PreferencesUtils.TEL_STATUS);
        map.put("name","运营商信息");
        map.put("desc","手机网络的运营商信息");
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "点击了第[" + position + "]行");

        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        Map<String, Object> map = list.get(position);
        bundle.putInt("id", (Integer) map.get("id"));
        bundle.putString("name", (String) map.get("name"));
        bundle.putString("desc", (String) map.get("desc"));
        bundle.putInt("position", position);
        intent.putExtra(SYSTEM_BUNDLE, bundle);

        intent.setClass(SystemActivity.this, ShowInfoActivity.class);
//        startActivityForResult(intent, 0);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_system, menu);
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
