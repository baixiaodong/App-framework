package com.baixd.app.framework.assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baixd.app.framework.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FSExplorerActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;

    private String path = "/";

    private List<Map<String, Object>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsexplorer);

        mListView = (ListView) findViewById(R.id.lv_fsexplorer);
        refreshListItems(path);
    }

    private void refreshListItems(String path){
        List<Map<String, Object>> list = buildListForSimpleAdapter(path);

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_row_fsexplorer,
                new String[]{"img", "name", "path"}, new int[]{R.id.iv_fs_explorer,
            R.id.tv_explorer_name, R.id.tv_explorer_path});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setSelection(0);

    }

    private List<Map<String,Object>> buildListForSimpleAdapter(String path){

        File[] files = new File(path).listFiles();
        mList = new ArrayList<Map<String,Object>>(files.length);

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("name", "/");
        root.put("img", R.drawable.file_root);
        root.put("path", "go to root directory");
        mList.add(root);

        Map<String, Object> pMap = new HashMap<String, Object>();
        pMap.put("name", "..");
        pMap.put("img", R.drawable.file_paranet);
        pMap.put("path", "go to parent directory");
        mList.add(pMap);

        for(File f:files){

            Map<String, Object> map = new HashMap<String, Object>();
            if(f.isDirectory()){
                map.put("img", R.drawable.directory);
            }else{
                map.put("img", R.drawable.file_doc);
            }

            map.put("name", f.getName());
            map.put("path", f.getPath());
            mList.add(map);
        }

        return mList;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            path = "/";
            refreshListItems(path);
        } else if (position == 1) {
            goToParent();
        } else {
            path = (String) mList.get(position).get("path");
            File file = new File(path);
            if(file.isDirectory()){
                refreshListItems(path);
            }else{
                Toast.makeText(this, getString(R.string.is_file), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToParent(){
        File file = new File(path);
        File parent = file.getParentFile();
        if(parent == null){
            Toast.makeText(this, getString(R.string.is_root_dir), Toast.LENGTH_SHORT).show();
        }else{
            path = parent.getAbsolutePath();
            refreshListItems(path);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fsexplorer, menu);
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
