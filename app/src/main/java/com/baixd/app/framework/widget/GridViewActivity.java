package com.baixd.app.framework.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.baixd.app.framework.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class GridViewActivity extends ActionBarActivity {

    private static final String TAG = "GridViewActivity";

    private GridView mGridView;

    private ArrayList<SoftReference<Bitmap>> bitmapList = new ArrayList<SoftReference<Bitmap>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        mGridView = (GridView) findViewById(R.id.gv1);
        mGridView.setAdapter(new ImageAdapter(this));

        new LoadImageThread().start();
    }

    private int[] thumbs = {
            R.drawable.image_gridview1, R.drawable.image_gridview2,
            R.drawable.image_gridview3, R.drawable.image_gridview4,
            R.drawable.image_gridview5, R.drawable.image_gridview6,
            R.drawable.image_gridview7, R.drawable.image_gridview8,
            R.drawable.image_gridview9, R.drawable.image_gridview10,
            R.drawable.image_gridview11, R.drawable.image_gridview12,
            R.drawable.image_gridview13, R.drawable.image_gridview14,
            R.drawable.image_gridview15, R.drawable.image_gridview16
    };


    class LoadImageThread extends Thread{

        @Override
        public void run() {
            for (int i : thumbs) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), i);
                SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
                bitmapList.add(softReference);
            }
        }
    }

    /**
     * 实现BaseAdapter
     */
    public class ImageAdapter extends BaseAdapter{

        private Context mContext;

        public ImageAdapter(Context context){
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return thumbs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(450, 450));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView = (ImageView) convertView;
            }

//            imageView.setImageResource(thumbs[position]);
            Bitmap bm = bitmapList.get(position).get();
            imageView.setImageBitmap(bm);




            return imageView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid, menu);
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
