package com.baixd.app.framework.my;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baixd.app.framework.R;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

public class Custom2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom2);
        RelativeLayout layoutMain = new RelativeLayout(this);
        layoutMain.setBackgroundColor(Color.GRAY);
        RelativeLayout.LayoutParams paramsMain = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutMain.setLayoutParams(paramsMain);

        TextView tv = new TextView(this);
        tv.setText("Hello World!");
        layoutMain.addView(tv);

        paramsMain.addRule(RelativeLayout.BELOW);

        ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btn = new Button(this);
        btn.setText("I'am a button!");
        btn.setLayoutParams(btnParams);
        layoutMain.addView(btn, btnParams);

        setContentView(layoutMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom2, menu);
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
