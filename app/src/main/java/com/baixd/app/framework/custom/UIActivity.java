package com.baixd.app.framework.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.baixd.app.framework.R;
import com.baixd.app.framework.base.IBaseActivity;


public class UIActivity extends Activity implements IBaseActivity{

    private UITitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ui);

        initView();
        registerListener();
    }


    @Override
    public void init() {

    }

    @Override
    public void initView() {
        mTitleBar = (UITitleBar) findViewById(R.id.titlebar);
    }

    @Override
    public void registerListener() {
        mTitleBar.setUITitleBarClickListener(new UITitleBar.UITitleBarClickListener() {
            @Override
            public void onLeftClick() {
                    Toast.makeText(UIActivity.this, "left", Toast.LENGTH_SHORT).show();
                    onBackPressed();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(UIActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
