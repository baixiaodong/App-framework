package com.baixd.app.framework.widget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.baixd.app.framework.R;

public class CheckBoxActivity extends ActionBarActivity {

    private CheckBox mCbPlain;
    private CheckBox mCbSerif;
    private CheckBox mCbBold;
    private CheckBox mCbItalic;

    private Button mBtnGetCheckBoxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        mCbPlain = (CheckBox) findViewById(R.id.cb_plain);
        mCbSerif = (CheckBox) findViewById(R.id.cb_serif);
        mCbBold = (CheckBox) findViewById(R.id.cb_bold);
        mCbItalic = (CheckBox) findViewById(R.id.cb_italic);

        mBtnGetCheckBoxValue = (Button) findViewById(R.id.btn_get_value);

        mBtnGetCheckBoxValue.setOnClickListener(new View.OnClickListener() {
            String s = "";
            @Override
            public void onClick(View view) {
                if(mCbPlain.isChecked()){
                    s = s + "," + mCbPlain.getText();
                }
                if(mCbSerif.isChecked()){
                    s = s + "," + mCbSerif.getText();
                }
                if(mCbBold.isChecked()){
                    s = s + "," + mCbBold.getText();
                }
                if(mCbItalic.isChecked()){
                    s = s + "," + mCbItalic.getText();
                }

                setTitle(s);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_box, menu);
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
