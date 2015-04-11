package com.baixd.app.framework.my;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baixd.app.framework.R;

public class LayoutActivity extends ActionBarActivity {

    private Button mButtonLinearAndRelativeLayout;
    private Button mButtonTableLayout;
    private Button mButtonCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        initView();
        registerListener();
    }

    private void initView() {
        mButtonLinearAndRelativeLayout = (Button) findViewById(R.id.btn_linear_rel_layout);
        mButtonTableLayout = (Button) findViewById(R.id.btn_tablelayout);
        mButtonCustom = (Button) findViewById(R.id.btn_custom);
    }

    private void registerListener() {
        mButtonLinearAndRelativeLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LayoutActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });

        mButtonTableLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LayoutActivity.this, TableActivity.class);
                startActivity(intent);
            }
        });

        mButtonCustom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LayoutActivity.this, Custom2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
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
