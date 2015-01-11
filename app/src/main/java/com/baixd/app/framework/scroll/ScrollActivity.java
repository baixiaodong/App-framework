package com.baixd.app.framework.scroll;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baixd.app.framework.R;


public class ScrollActivity extends ActionBarActivity {

    private LinearLayout mHorizontalLinearLayout;

    private ImageView imageView;

    private int[] images = new int[]{
            R.drawable.s1,R.drawable.s2, R.drawable.s3
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        mHorizontalLinearLayout = (LinearLayout) findViewById(R.id.horizontal_ll);

        for(int i = 0; i < images.length; i++){
            mHorizontalLinearLayout.addView(getImageView(images[i]));
        }

    }

    private View getImageView(int id){
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
        linearLayout.setGravity(Gravity.CENTER);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(id);
        imageView.setTag("图"+id);
        linearLayout.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollActivity.this, "点击了图" + v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 0.1f, 0, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(800);
                imageView.startAnimation(scaleAnimation);

                return false;
            }
        });

        return linearLayout;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scroll, menu);
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
