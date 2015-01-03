package com.baixd.app.framework.anim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.baixd.app.framework.R;

public class AnimationActivity extends ActionBarActivity {

    private static final String TAG = "AnimationActivity";

    private TextView mTextView;

    private Button mButtonAlpha; //透明渐变
    private Button mButtonScale; //伸缩 尺寸变化
    private Button mButtonTranslate; //转换位置移动
    private Button mButtonRotate; //画面转移旋转

    private Button mButtonAnimSet; //组合动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        registListener();
    }

    private void initView(){
        mTextView = (TextView) findViewById(R.id.tv_anim);

        mButtonAlpha = (Button) findViewById(R.id.btn_anim_alpha);
        mButtonScale = (Button) findViewById(R.id.btn_anim_scale);
        mButtonTranslate = (Button) findViewById(R.id.btn_anim_translate);
        mButtonRotate = (Button) findViewById(R.id.btn_anim_rotate);

        mButtonAnimSet = (Button) findViewById(R.id.btn_animation_set);

    }

    private void registListener(){
        mButtonAlpha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.alpha_t1);
                mTextView.startAnimation(anim);
            }
        });

        mButtonScale.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.scale_t1);
                mTextView.startAnimation(anim);
            }
        });

        mButtonTranslate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.translate_t1);
                mTextView.startAnimation(anim);
            }
        });

        mButtonRotate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.rotate_t1);
                mTextView.startAnimation(anim);
            }
        });


        mButtonAnimSet.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Log.i(TAG, "exec 组合动画");
                Animation anim = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.anim_set_t1);
                mTextView.startAnimation(anim);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation, menu);
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
