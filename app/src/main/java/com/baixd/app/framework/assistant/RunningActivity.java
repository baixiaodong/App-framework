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

public class RunningActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{


    private static final String RUNNING_BUNDLE = "android.intent.extra.info";

    private ListView mListView;

    private List<Map<String, Object>> mList;



    public RunningActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        mListView = (ListView) findViewById(R.id.lv_running);
        refreshListItems();

    }

    private void refreshListItems(){
        buildListForSimpleAdapter();
        SimpleAdapter adapter = new SimpleAdapter(this, mList, R.layout.item_row_system,
                new String[]{"name", "desc"}, new int[]{R.id.tv_system_name, R.id.tv_system_desc});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setSelection(adapter.getCount() - 1);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter(){

        mList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.RunningService);
        map.put("name", "运行的service");
        map.put("desc", "获取正在运行的后台服务");
        mList.add(map);


        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.RunningTasks);
        map.put("name", "运行的Task");
        map.put("desc", "获取正在运行的任务");
        mList.add(map);

        map = new HashMap<String, Object>();
        map.put("id", PreferencesUtils.RunningProcesses);
        map.put("name", "运行的进程");
        map.put("desc", "获取正在运行的进程");
        mList.add(map);

        return mList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        Map<String, Object> map = mList.get(position);
        bundle.putInt("id", (Integer) map.get("id"));
        bundle.putString("name", (String) map.get("name"));
        bundle.putString("desc", (String) map.get("desc"));
        intent.putExtra(RUNNING_BUNDLE, bundle);

        intent.setClass(RunningActivity.this, ShowInfoActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_running, menu);
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
