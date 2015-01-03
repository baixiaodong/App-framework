package com.baixd.app.framework;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TestListViewActivity extends Activity {

    private ArrayList<Map<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareData();
        ListView listView = new ListView(this);
        //使用自定义的元素方式展示数据
//        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item,
//                new String[]{"姓名", "性别"}, new int[]{R.id.list_item_tv1, R.id.list_item_tv2});
        //使用系统默认的单元素显示方式
//        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1,
//                new String[]{"姓名", "性别"}, new int[]{android.R.id.text1);
        //使用系统提供的两个元素显示，上面为正常大字体，下方为小字体
        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                new String[]{"姓名", "性别"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        setContentView(listView);
    }


    private void prepareData() {
        data = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("姓名", "张三小朋友");
        item.put("性别", "男");
        data.add(item);

        item = new HashMap<String, Object>();
        item.put("姓名", "王五同学");
        item.put("性别", "男");
        data.add(item);

        item = new HashMap<String, Object>();
        item.put("姓名", "小李师傅");
        item.put("性别", "女");
        data.add(item);
    }
}
