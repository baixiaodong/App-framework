package com.baixd.app.framework.annotation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.baixd.app.framework.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_eannotation)
public class EAnnotationActivity extends ActionBarActivity {

    @ViewById
    TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_eannotation);

//        helloTextView.setText("hello one 豆！");
    }

    @AfterViews
    void updateViews(){
        helloTextView.setText("hello one 豆！");
    }


}
