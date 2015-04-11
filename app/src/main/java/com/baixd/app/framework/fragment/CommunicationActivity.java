package com.baixd.app.framework.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baixd.app.framework.R;

public class CommunicationActivity extends ActionBarActivity implements CommunicationFragment.OnFragmentInteractionListener{

    public static final String MSG_KEY = "msg";

    public static final CharSequence MESSAGE = "向Fragment传递消息";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction transaction = fragmentMgr.beginTransaction();

        Fragment fragment = new CommunicationFragment();
        Bundle bundle = new Bundle();
        bundle.putCharSequence(MSG_KEY , MESSAGE);
        fragment.setArguments(bundle);

        transaction.replace(R.id.ll_communication, fragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_communication, menu);
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

    @Override
    public void onFragmentInteraction(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
