package com.baixd.app.framework.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.baixd.app.framework.R;

public class FragmentActivity extends ActionBarActivity {
    private static final String TAG = "FragmentActivity";

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_frag);

        registerListener();
    }

    private void registerListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_frag_first: {
                        Log.i(TAG, "first");

                        Intent intent = new Intent(FragmentActivity.this, FirstActivity.class);
                        startActivity(intent);

                        break;
                    }
                    case R.id.rb_frag_second:
                        Log.i(TAG, "second");

                        DynamicLoadFragment dynaFragment = new DynamicLoadFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                        //beginTransaction.add(R.id.fragment, dynaFragement);
                        //beginTransaction.addToBackStack(null);
                        beginTransaction.replace(R.id.fragment, dynaFragment);
                        beginTransaction.commit();

                        break;

                    case R.id.rb_frag_third: {
                        Log.i(TAG, "third");

                        Intent intent = new Intent(FragmentActivity.this, LifecycleFragmentActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.rb_frag_four:
                        Log.i(TAG, "four");

                        Intent intent = new Intent(FragmentActivity.this, CommunicationActivity.class );
                        startActivity(intent);
                        break;

                }

            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
