package com.baixd.app.framework.widget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.baixd.app.framework.R;

public class RadioGroupActiviy extends ActionBarActivity {

    private RadioGroup radioGroup;

    private Button mBtnClearRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group_activiy);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mBtnClearRadio = (Button) findViewById(R.id.btn_clear_radio);
        mBtnClearRadio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                radioGroup.clearCheck();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_radio_group_activiy, menu);
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
