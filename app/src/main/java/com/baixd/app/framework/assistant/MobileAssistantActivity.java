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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 手机设备信息助手
 *
 * @author Baixd
 * @since 2015-3-31
 * @version 1.0
 */
public class MobileAssistantActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "MobileAssistantActivity";

    private ListView mListView;

    List<Map<String,Object>> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_assistant);

        initView();
        registenerListener();

        refreshListItems();
    }


    private void initView(){
        mListView = (ListView) findViewById(R.id.lv_assistant);
    }

    public void registenerListener(){

    }

    /**
     * 为listview加载数据
     */
    private void refreshListItems(){
        list = buildListForSimpleAdapter();
        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.item_row, new String[]{"name", "desc", "img"},
                new int[]{R.id.tv_assistant_item_name, R.id.tv_assistant_item_desc, R.id.tv_assistant_item_img});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setSelection(0);
    }

    /**
     * 填充ListView的数据
     * @return
     */
    private List<Map<String, Object>> buildListForSimpleAdapter(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "系统信息");
        map.put("desc", "查看设备系统版本，运营商及其系统信息.");
        map.put("img", R.drawable.system);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "硬件信息");
        map.put("desc", "查看包括CPU、硬盘、内存等硬件信息.");
        map.put("img", R.drawable.hardware);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "软件信息");
        map.put("desc", "查看已经安装的软件信息.");
        map.put("img", R.drawable.software);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "运行时信息");
        map.put("desc", "查看设备运行时的信息.");
        map.put("img", R.drawable.running);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "文件浏览器");
        map.put("desc", "浏览查看文件系统.");
        map.put("img", R.drawable.file_explorer);
        list.add(map);

        return list;
    }


    /**
     * ListView 中每一行点击后执行的方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        Log.i(TAG, "点击了第[" + position + "]行.");

        switch (position) {
            case 0:
                intent.setClass(MobileAssistantActivity.this, SystemActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent.setClass(MobileAssistantActivity.this, HardwareActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent.setClass(MobileAssistantActivity.this, SoftwareActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(MobileAssistantActivity.this, RunningActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent.setClass(MobileAssistantActivity.this, FSExplorerActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mobile_assistant, menu);
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
