package com.baixd.app.framework.annotation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.baixd.app.framework.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MyViewInjectActivity extends ActionBarActivity {

    private static final String TAG = "MyViewInjectActivity";

    @ViewInject(id = R.id.btn_myview, click = "clickBtn")
    Button mBtnMyInject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_inject);
        scanViewInjcet();
    }

    public void scanViewInjcet(){
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field:fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if(viewInject != null) {
                Log.i(TAG, viewInject.id() + "");
                Log.i(TAG, viewInject.click() + "");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_view_inject, menu);
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
