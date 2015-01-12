package com.baixd.app.framework.widget;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.baixd.app.framework.R;

public class WidgetActivity extends ActionBarActivity {

    private Button mBtnCheckBox;
    private Button mBtnRadioGroup;
    private Button mBtnSpinner;
    private Button mBtnAutoComplete;
    private Button mBtnDateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        initView();
        registerListener();
    }

    private void initView() {
        mBtnCheckBox = (Button) findViewById(R.id.btn_check_box);
        mBtnRadioGroup = (Button) findViewById(R.id.btn_radio_group);
        mBtnSpinner = (Button) findViewById(R.id.btn_spinner);
        mBtnAutoComplete = (Button)findViewById(R.id.btn_autocomplete);
        mBtnDateTimePicker = (Button)findViewById(R.id.btn_date_time_picker);
    }

    private void registerListener() {
        mBtnCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetActivity.this, CheckBoxActivity.class);
                startActivity(intent);
            }
        });

        mBtnRadioGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetActivity.this, RadioGroupActiviy.class);
                startActivity(intent);
            }
        });

        mBtnSpinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetActivity.this, SpinnerActiviy.class);
                startActivity(intent);
            }
        });

        mBtnAutoComplete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetActivity.this, AutoCompleteActiviy.class);
                startActivity(intent);
            }
        });

        mBtnDateTimePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WidgetActivity.this, DateTimeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_widget, menu);
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
