package com.baixd.app.framework.storage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baixd.app.framework.R;
import com.baixd.app.framework.base.IBaseActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

public class StorageActivity extends ActionBarActivity implements IBaseActivity{

    public static final String TAG = "StorageActivity";

    public static final String SETTING_INFOS = "setting_infos";
    private static final String NAME = "KYE_NAME";
    private static final String VALUE = "KYE_VALUE";

    private static final String FILE_NAME = "myapp.tmp";

    private EditText mEditTextName;
    private EditText mEditTextValue;

    private Button mBtnSharedPreferences;
    private Button mBtnFile;
    private Button mBtnSQLite;
    private Button mBtnContentProvider;


    private Button mBtnFSharedPreferences;
    private Button mBtnFFile;
    private Button mBtnFSQLite;
    private Button mBtnFContentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        initView();
        registerListener();
        init();
    }


    @Override
    public void init() {
        //SharedPreferences pref = this.getSharedPreferences(SETTING_INFOS, MODE_PRIVATE);
    }

    @Override
    public void initView() {
        mEditTextName = (EditText) findViewById(R.id.tv_storage_name);
        mEditTextValue = (EditText) findViewById(R.id.tv_storage_value);


        mBtnSharedPreferences = (Button) findViewById(R.id.btn_shared_preferences);
        mBtnFile = (Button) findViewById(R.id.btn_file);
        mBtnSQLite = (Button) findViewById(R.id.btn_sqlite);
        mBtnContentProvider = (Button) findViewById(R.id.btn_content_provider);

        mBtnFSharedPreferences = (Button) findViewById(R.id.btn_f_shared_preferences);
        mBtnFFile = (Button) findViewById(R.id.btn_f_file);
        mBtnFSQLite = (Button) findViewById(R.id.btn_f_sqlite);
        mBtnFContentProvider = (Button) findViewById(R.id.btn_f_content_provider);
    }

    @Override
    public void registerListener() {
        //向存储中存数据
        mBtnSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SETTING_INFOS, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Log.i(TAG, "name=" + mEditTextName.getText().toString());
                Log.i(TAG, "value=" + mEditTextValue.getText().toString());

                editor.putString(NAME, mEditTextName.getText().toString());
                editor.putString(VALUE, mEditTextValue.getText().toString());
                editor.commit();

                showDialog().show();

            }
        });

        mBtnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.write(mEditTextName.getText().toString() + "\n");
                    pw.write(mEditTextValue.getText().toString() + "\n");
                    pw.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(StorageActivity.this);

                DBEntity entity = new DBEntity();
                entity.setName(mEditTextName.getText().toString());
                entity.setValue(mEditTextValue.getText().toString());

                dbHelper.insert(entity);

                showDialog().show();

            }
        });

        mBtnContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //从存储中取出数据
        mBtnFSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(SETTING_INFOS, MODE_PRIVATE);
                mEditTextName.setText(preferences.getString(NAME, "未知"));
                mEditTextValue.setText(preferences.getString(VALUE, "未知"));
            }
        });

        mBtnFFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(FILE_NAME);

                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String s1 = br.readLine();
                    String s2 = br.readLine();
                    mEditTextName.setText(s1);
                    mEditTextValue.setText(s2);

//                    FileChannel channel = fis.getChannel();

                    br.close();
                    fis.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnFSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(StorageActivity.this);
                DBEntity entity = dbHelper.query();
                if(entity != null){
                    mEditTextName.setText(entity.getName());
                    mEditTextValue.setText(entity.getValue());
                }
            }
        });

        mBtnFContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private AlertDialog showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setMessage("存储数据成功！");
        builder.setTitle("消息提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEditTextName.setText("");
                mEditTextValue.setText("");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storage, menu);
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
