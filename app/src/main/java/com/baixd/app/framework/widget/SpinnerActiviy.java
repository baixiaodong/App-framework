package com.baixd.app.framework.widget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.baixd.app.framework.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpinnerActiviy extends ActionBarActivity {

    private static final String TAG = "SpinnerActivity";

    private static final String[] mCountries = new String[]{"China", "Russia", "Germany", "Ukraine", "Belarus", "USA"};
    private static final String mlabelValueJsonStr = "[{\"id\":\"1\", \"name\":\"name1\"},{\"id\":\"2\", \"name\":\"name2\"},{\"id\":\"3\", \"name\":\"name3\"}]";

    private Spinner mSpinner1;
    private Spinner mSpinner2;

    private ArrayList<String> allCountries;
    private ArrayList<JSONObject> allLabelValue;
    private ArrayList<LabelValueBean> labelValueBeans;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<JSONObject> labelValueAdapter;
    private ArrayAdapter<LabelValueBean> labelValueBeanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        setTitle("下拉列表Spinner");

        initView();
        initData();
        registerListener();
    }

    void initView() {
        mSpinner1 = (Spinner) findViewById(R.id.spinner1);
        mSpinner2 = (Spinner) findViewById(R.id.spinner2);
    }

    void initData() {
        initSpinnerData();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCountries);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(arrayAdapter);

//        labelValueAdapter = new ArrayAdapter<JSONObject>(this, android.R.layout.simple_spinner_item, allLabelValue);
//        labelValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner2.setAdapter(labelValueAdapter);

        labelValueBeanAdapter = new ArrayAdapter<LabelValueBean>(this, android.R.layout.simple_spinner_item, labelValueBeans);
        labelValueBeanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(labelValueBeanAdapter);
    }

    void initSpinnerData() {
        allCountries = new ArrayList<String>();

        for (int i = 0; i < mCountries.length; i++) {
            allCountries.add(mCountries[i]);
        }

        allLabelValue = new ArrayList<JSONObject>();
        try {
            JSONArray ja = new JSONArray(mlabelValueJsonStr);
            for(int i = 0; i < ja.length(); i++){
                allLabelValue.add(ja.getJSONObject(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        labelValueBeans = new ArrayList<LabelValueBean>();
        LabelValueBean lvb = new LabelValueBean("1", "label1");
        labelValueBeans.add(lvb);
        lvb = new LabelValueBean("2", "label2");
        labelValueBeans.add(lvb);
        lvb = new LabelValueBean("3", "label3");
        labelValueBeans.add(lvb);
    }

    void registerListener() {

        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SpinnerActiviy.this, mSpinner1.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(SpinnerActiviy.this, mSpinner2.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SpinnerActiviy.this, ((LabelValueBean)(mSpinner2.getSelectedItem())).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class LabelValueBean{
        private String value;
        private String label;

        public LabelValueBean(String value, String label){
            this.value = value;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString(){
            return label;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spinner_activiy, menu);
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
