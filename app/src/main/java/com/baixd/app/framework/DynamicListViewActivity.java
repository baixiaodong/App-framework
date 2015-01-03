package com.baixd.app.framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DynamicListViewActivity extends Activity implements SlideListView.RemoveListener {


    private SlideListView slideListView;
    private ArrayAdapter<String> adapter;
    private List<String> dataSourceList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_list_view);
        init();
    }

    private void init() {
        slideListView = (SlideListView) findViewById(R.id.slide_list_view);
        slideListView.setRemoveListener(this);

        for (int i = 0; i < 20; i++) {
            dataSourceList.add("滑动删除" + i);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.slide_list_view_item, R.id.list_item, dataSourceList);
        slideListView.setAdapter(adapter);

        slideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DynamicListViewActivity.this, dataSourceList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //滑动删除之后的回调方法
    @Override
    public void removeItem(SlideListView.RemoveDirection direction, int position) {
        adapter.remove(adapter.getItem(position));


        switch (direction) {
            case RIGHT:
                Toast.makeText(this, "向右删除  " + position, Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "向左删除  " + position, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

}
